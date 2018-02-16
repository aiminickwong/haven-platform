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

package com.codeabovelab.dm.common.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JacksonUtils {

    private JacksonUtils() {}

    /**
     * Build Jackson ObjectMapper with default for our platform settings
     * see javadoc of properties
     * @return
     */
    public static ObjectMapper objectMapperBuilder() {
        ObjectMapper objectMapper = new ObjectMapper()
          .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
          .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
          .configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false)
          .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        registerModules(objectMapper);
        objectMapper.setSerializationInclusion(JsonInclude.Include.USE_DEFAULTS);
        return objectMapper;
    }

    public static void registerModules(ObjectMapper objectMapper) {
        objectMapper.registerModules(new DmJacksonModule(), new Jdk8Module(), new JavaTimeModule(), new JtModule());
    }

}
