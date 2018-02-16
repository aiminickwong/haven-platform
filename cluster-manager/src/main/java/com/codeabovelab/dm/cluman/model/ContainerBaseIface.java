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

package com.codeabovelab.dm.cluman.model;


import com.codeabovelab.dm.cluman.ds.SwarmUtils;

/**
 * Basic iface for container
 */
public interface ContainerBaseIface extends Named, Labels {

    String getId();

    String getImage();

    String getImageId();

    /**
     * Id of service, which own this container, or null. <p/>
     * @see SwarmUtils#LABEL_SERVICE_ID
     * @return service id or null
     */
    default String getService() {
        return getLabels().get(SwarmUtils.LABEL_SERVICE_ID);
    }
}
