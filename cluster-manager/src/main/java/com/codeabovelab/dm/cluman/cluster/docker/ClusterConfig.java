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

package com.codeabovelab.dm.cluman.cluster.docker;

import com.codeabovelab.dm.cluman.ds.swarm.Strategies;

import java.util.List;

/**
 */
public interface ClusterConfig {
    String getHost();

    int getMaxCountOfInstances();

    String getDockerRestart();

    String getCluster();

    /**
     * Time in seconds, which data was cached after last write.
     * @return seconds
     */
    long getCacheTimeAfterWrite();

    /**
     * Maximal timeout for docker api access. In seconds. <p/>
     * For some readonly ops, like getinfo, system use small hardcoded timeout.
     * @return timeout in seconds
     */
    int getDockerTimeout();

    List<String> getRegistries();

    Strategies getStrategy();
}
