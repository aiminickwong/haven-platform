/*
 * Copyright 2017 Code Above Lab LLC
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

package com.codeabovelab.dm.cluman.model;


import com.codeabovelab.dm.cluman.cluster.docker.model.swarm.Endpoint;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Docker container port
 */
@Data
@AllArgsConstructor
public class Port {

    private final int privatePort;
    private final int publicPort;
    private final ProtocolType type;
    private final Endpoint.PortConfigPublishMode mode;

    public Port(int privatePort, int publicPort, ProtocolType type) {
        this.privatePort = privatePort;
        this.publicPort = publicPort;
        this.type = type;
        this.mode = Endpoint.PortConfigPublishMode.HOST;
    }
}
