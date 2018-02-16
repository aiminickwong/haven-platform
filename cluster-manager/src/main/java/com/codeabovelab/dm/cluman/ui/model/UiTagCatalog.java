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

package com.codeabovelab.dm.cluman.ui.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 */
@Data
@AllArgsConstructor(onConstructor = @__(@JsonCreator))
public class UiTagCatalog implements Comparable<UiTagCatalog> {
    private final String registry;
    private final String name;
    private final String node;
    private final String tag;
    private final String image;

    private final Date created;
    private final Map<String, String> labels;

    @Override
    public int compareTo(UiTagCatalog o) {
        return tag.compareTo(o.tag) ;
    }
}
