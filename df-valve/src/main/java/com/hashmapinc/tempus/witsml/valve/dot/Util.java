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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Set;

public class Util {

    /**
     * This function merges the fields from src that
     * are missing from dest. Only fields that exist in dest
     * but have an empty value are merged.
     *
     * @param dest - JSONObject to merge into. Existing values here are unchanged
     * @param src - src to merge from for blank values in dest
     * @return dest - fully merged dest
     */
    public static JSONObject merge (
        JSONObject dest,
        JSONObject src
    ) {
        // track keys that should be removed in cleanup
        ArrayList<String> keysToRemove = new ArrayList<>();

        // iterate through keys and merge in place
        Set<String> keyset = dest.keySet();
        for (String key : keyset) {
            // check that the response has a value for this key.
            if (!src.has(key)) {
                if (isEmpty(dest.get(key)))
                    keysToRemove.add(key); // remove this key if it's empty
                continue;
            }

            // get values in src and dest as objects for this key
            Object destObj = dest.get(key);
            Object srcObj = src.get(key);

            // do merging below for each possible type
            if (destObj instanceof JSONObject && srcObj instanceof JSONObject ) {
                merge((JSONObject) destObj, (JSONObject) srcObj); // recursively copy into destObj
                dest.put(key, destObj); // update dest with the updated value for this key

            } else if (destObj instanceof JSONArray && srcObj instanceof JSONArray ) {
                if (!isEmpty(destObj) && !isEmpty(srcObj)) {
                    dest.put(key, srcObj); // TODO: deep merging on sub objects
                }
            } else { // handle all basic values (non array, non nested objects)
                dest.put(key, srcObj);
            }

            // if after all the merging the dest value is still empty, add the key to list of removable fields
            if (isEmpty(dest.get(key)))
                keysToRemove.add(key);
        }

        // cleanup fields
        for (String removableKey : keysToRemove) dest.remove(removableKey);

        // return the dest
        return dest;
    }

    /**
     * Checks if either a string, JSONArray, or JSONObject are empty
     * @param obj - object to examine
     * @return boolean - true if emptiness is confirmed, else false
     */
    private static boolean isEmpty(Object obj) {
        // handle nulls
        if (JSONObject.NULL.equals(obj)) return true;

        // handle strings
        if (obj instanceof String) return ((String) obj).isEmpty();

        // handle json array
        if (obj instanceof JSONArray) return ((JSONArray) obj).length() == 0;

        // handle json objects
        if (obj instanceof JSONObject) {
            // get json obj for easy inspection
            JSONObject jsonObj = (JSONObject) obj;

            // recurse over all children. 1 false results in false for overall check
            boolean jsonObjIsEmpty = true; // true until proven false
            Set<String> keyset = jsonObj.keySet();
            for(String key: keyset)
                jsonObjIsEmpty = jsonObjIsEmpty && isEmpty(jsonObj.get(key));

            return jsonObjIsEmpty;
        }

        return false;
    }
}
