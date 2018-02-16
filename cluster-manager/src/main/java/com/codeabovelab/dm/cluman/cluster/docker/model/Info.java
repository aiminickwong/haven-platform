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

package com.codeabovelab.dm.cluman.cluster.docker.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class Info {

    @JsonProperty("Id")
    private String id;

    @JsonProperty("SystemTime")
    private ZonedDateTime systemTime;

    @JsonProperty("Containers")
    private Integer containers;
    @JsonProperty("Images")
    private Integer images;
    @JsonProperty("NCPU")
    private Integer ncpu;
    @JsonProperty("MemTotal")
    private long memory;
    @JsonProperty("Name")
    private String name;

    @JsonProperty("DriverStatus")
    private List<List<String>> driverStatus;
    @JsonProperty("SystemStatus")
    private List<List<String>> systemStatus;

    @JsonProperty("Labels")
    private List<String> labels;

    @JsonProperty("Swarm")
    private InfoSwarm swarm;
}
