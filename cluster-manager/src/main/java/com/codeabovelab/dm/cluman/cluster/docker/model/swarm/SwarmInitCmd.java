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

package com.codeabovelab.dm.cluman.cluster.docker.model.swarm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * {
     "ListenAddr": "0.0.0.0:2377",
     "AdvertiseAddr": "192.168.1.1:2377",
     "ForceNewCluster": false,
     "Spec": {
         "Orchestration": {},
         "Raft": {},
         "Dispatcher": {},
         "CAConfig": {}
     }
 }
 */
@Data
public class SwarmInitCmd {
    @JsonProperty("ListenAddr")
    private String listenAddr;
    @JsonProperty("AdvertiseAddr")
    private String advertiseAddr;
    @JsonProperty("ForceNewCluster")
    private String forceNewCluster;
    @JsonProperty("Spec")
    private SwarmSpec spec;
}
