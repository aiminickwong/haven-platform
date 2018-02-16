/*
 * Copyright 2016 Code Above Lab LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codeabovelab.dm.cluman.ds.clusters;

import com.codeabovelab.dm.cluman.cluster.docker.ClusterConfigImpl;
import com.codeabovelab.dm.cluman.model.NodesGroup;
import com.codeabovelab.dm.cluman.security.TempAuth;
import com.codeabovelab.dm.common.kv.mapping.KvMapperFactory;
import com.codeabovelab.dm.common.validate.ValidationUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.util.Assert;

import javax.validation.Validator;

/**
 */
@Data
@Slf4j
class ClusterFactory {
    private final DiscoveryStorageImpl storage;
    private AbstractNodesGroupConfig<?> config;
    private String type;
    private ClusterConfigFactory configFactory;
    private KvMapperFactory kvmf;
    private final AutowireCapableBeanFactory beanFactory;
    private final Validator validator;

    public ClusterFactory config(AbstractNodesGroupConfig<?> config) {
        setConfig(config);
        return this;
    }

    public ClusterFactory configFactory(ClusterConfigFactory consumer) {
        setConfigFactory(consumer);
        return this;
    }

    NodesGroup build(String clusterId) {
        ClusterCreationContext ccc = new ClusterCreationContext(this, clusterId);
        processConfig(ccc);
        AbstractNodesGroup<?> cluster;
        if(config instanceof DefaultNodesGroupConfig) {
            cluster = NodesGroupImpl.builder()
              .config((DefaultNodesGroupConfig) config)
              .storage(storage)
              .build();
        } else if(config instanceof SwarmNodesGroupConfig) {
            SwarmNodesGroupConfig localConfig = (SwarmNodesGroupConfig) config;
            cluster = new SwarmCluster(storage, localConfig);
        } else if(config instanceof DockerClusterConfig) {
            DockerClusterConfig localConfig = (DockerClusterConfig) config;
            cluster = new DockerCluster(storage, localConfig);
        } else {
            throw new IllegalArgumentException("Unsupported type of cluster config: " + config.getClass());
        }
        beanFactory.autowireBean(cluster);
        ccc.beforeClusterInit(cluster);
        storage.getExecutor().execute(() -> {
            try(TempAuth ta = TempAuth.asSystem()) {
                cluster.init();
            }
        });
        return cluster;
    }

    private void fixConfig(DockerBasedClusterConfig localConfig) {
        if(localConfig.getConfig() != null) {
            return;
        }
        log.warn("Configuration of cluster '{}' contain null value of {}, set default instance. ",
          localConfig.getName(), ClusterConfigImpl.class);
        initDefaultConfig(localConfig);
    }

    void initDefaultConfig(DockerBasedClusterConfig localConfig) {
        localConfig.setConfig(ClusterConfigImpl.builder().cluster(localConfig.getName()).build());
    }

    private void processConfig(ClusterCreationContext ccc) {
        Assert.isTrue(type != null || config != null || configFactory != null,
          "Both 'type' and 'config' is null, we can not resolve type of created cluster.");
        if (config == null) {
            if(configFactory != null) {
                config = configFactory.create(ccc);
                Assert.notNull(config, "Config factory: " + configFactory + " return null.");
            } else {
                config = ccc.createConfig(getType());
            }
        }
        if(config instanceof DockerBasedClusterConfig) {
            fixConfig((DockerBasedClusterConfig)config);
        }
        if(ccc.isMustValidated()) {
            String clusterName = config.getName();
            ValidationUtils.assertValid(validator, config, clusterName, "Config of " + clusterName + " is invalid.");
        }
    }
}
