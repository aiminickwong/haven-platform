/*
 * Copyright 2016 Code Above Lab LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codeabovelab.dm.common.kv;

import lombok.Data;

/**
 */
@Data
public class KvNode {
    /**
     * Index of node, usually used for CAS operations.
     */
    private final long index;
    private final String value;
    private final boolean directory;

    private KvNode(long index, String val, boolean dir) {
        this.index = index;
        this.value = val;
        this.directory = dir;
    }

    public static KvNode dir(long index) {
        return new KvNode(index, null, true);
    }

    public static KvNode leaf(long index, String val) {
        return new KvNode(index, val, false);
    }
}
