/**
 * Copyright (C) 2014-2016 LinkedIn Corp. (pinot-core@linkedin.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.linkedin.pinot.tools.pacelab.benchmark;

import java.util.Properties;

public class AdClickQueryExecutor extends QueryExecutor{
    private static AdClickQueryExecutor instance;
    private final String CONFIG_FILE = "AdClickConfig.properties";
    private static final String[] QUERIES = getQueries();

    private static String[] getQueries() {
        String[] queries = {
                "SELECT COUNT(*) FROM AdClick" +
                        " WHERE ClickTime > %d AND ClickTime < %d",
                "SELECT COUNT(*) FROM AdClick" +
                        " WHERE ClickTime > %d AND ClickTime < %d AND AdID = '%s'" ,
                "SELECT AdCampaign, COUNT(*), AVG(ViewerStrength), MIN(ViewerStrength), MAX(ViewerStrength) FROM AdClick" +
                        " WHERE ClickTime > %d AND ClickTime < %d" +
                        " GROUP BY AdCampaign TOP %d",
                "SELECT AdCompany, ViewerPosition, ViewerStrength, COUNT(*) FROM AdClick" +
                        " WHERE ClickTime > %d AND ClickTime < %d" +
                        " GROUP BY AdCompany, ViewerPosition, ViewerStrength TOP %d"
        };
        return queries;
    }

    public String getConfigFile() {
        return CONFIG_FILE;
    }

    public static AdClickQueryExecutor getInstance() {
        if (instance == null)
            instance = new AdClickQueryExecutor();
        return instance;
    }

    public AdClickQueryTask getTask(Properties config) {
        return new AdClickQueryTask(config, QUERIES, _dataDir, _testDuration);
    }
}