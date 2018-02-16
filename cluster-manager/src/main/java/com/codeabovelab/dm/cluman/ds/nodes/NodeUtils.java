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

package com.codeabovelab.dm.cluman.ds.nodes;

import com.codeabovelab.dm.cluman.cluster.docker.management.DockerService;
import com.codeabovelab.dm.cluman.ds.container.ContainerRegistration;
import com.codeabovelab.dm.cluman.ds.container.ContainerStorage;
import com.codeabovelab.dm.cluman.validate.ExtendedAssert;
import com.google.common.net.InternetDomainName;

/**
 */
public class NodeUtils {
    private NodeUtils() {
    }

    /**
     * Node name must be valid host name. In this method we check it.
     * @param name
     */
    public static void checkName(String name) {
        InternetDomainName.from(name);
    }

    public static DockerService getDockerByContainer(ContainerStorage containers, NodeStorage nodes, String containerId) {
        ContainerRegistration container = containers.getContainer(containerId);
        ExtendedAssert.notFound(container, "Can't find container by id " + containerId);
        return nodes.getNodeService(container.getNode());
    }

}
