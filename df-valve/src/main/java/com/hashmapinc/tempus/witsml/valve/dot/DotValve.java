/**
 * Copyright © 2018-2018 Hashmap, Inc
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
package com.hashmapinc.tempus.witsml.valve.dot;

import java.util.Map;

import com.hashmapinc.tempus.WitsmlObjects.AbstractWitsmlObject;
import com.hashmapinc.tempus.witsml.QueryContext;
import com.hashmapinc.tempus.witsml.valve.AbstractValve;
import com.hashmapinc.tempus.witsml.valve.AbstractDelegator;
import com.hashmapinc.tempus.witsml.valve.AbstractTranslator;
import com.hashmapinc.tempus.witsml.valve.AbstractAuth;
import com.hashmapinc.tempus.witsml.valve.dot.DotDelegator;
import com.hashmapinc.tempus.witsml.valve.dot.DotTranslator;
import com.hashmapinc.tempus.witsml.valve.dot.DotAuth;

public class DotValve extends AbstractValve {
    final String NAME = "DoT"; // DoT = Drillops Town
    final String DESCRIPTION = "Valve for interaction with Drillops Town"; // DoT = Drillops Town
    AbstractDelegator delegator;
    AbstractTranslator translator;
    AbstractAuth auth;

    public DotValve() {
        this.delegator = new DotDelegator();
        this.translator = new DotTranslator();
        this.auth = new DotAuth();
    }

    /**
     * Retrieve the name of the valve
     * @return The name of the valve
     */
    @Override
    public String getName() {
        return this.NAME;
    }

    /**
     * Retrieve the description of the valve
     * @return The description of the valve
     */
    @Override
    public String getDescription() {
        return this.DESCRIPTION;
    }

    /**
     * Gets the object based on the query from the WITSML STORE API
     * 
     * @param qc - QueryContext needed to execute the getObject querying
     * @return The resultant object from the query
     */
    @Override
    public AbstractWitsmlObject getObject(QueryContext qc) {
        return null;
    }

    /**
     * Creates an object
     * @param query POJO representing the object that was received
     * @return the UID of the newly created object
     */
    @Override
    public String createObject(AbstractWitsmlObject query) {
        return null;
    }

    /**
     * Deletes an object
     * @param query POJO representing the object that was received
     */
    @Override
    public void deleteObject(AbstractWitsmlObject query) {
    }

    /**
     * Updates an already existing object
     * @param query POJO representing the object that was received
     */
    @Override
    public void updateObject(AbstractWitsmlObject query) {
    }
}