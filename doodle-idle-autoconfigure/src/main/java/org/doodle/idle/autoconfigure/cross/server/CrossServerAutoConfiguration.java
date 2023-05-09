/*
 * Copyright (c) 2022-present Doodle. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.doodle.idle.autoconfigure.cross.server;

import org.doodle.admin.autoconfigure.client.AdminClientAutoConfiguration;
import org.doodle.broker.autoconfigure.client.BrokerClientAutoConfiguration;
import org.doodle.config.autoconfigure.client.ConfigClientAutoConfiguration;
import org.doodle.console.autoconfigure.client.ConsoleClientAutoConfiguration;
import org.doodle.excel.autoconfigure.client.ExcelClientAutoConfiguration;
import org.doodle.idle.cross.server.CrossServerProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@AutoConfiguration(
    after = {
      BrokerClientAutoConfiguration.class,
      ConfigClientAutoConfiguration.class,
      ConsoleClientAutoConfiguration.class,
      AdminClientAutoConfiguration.class,
      ExcelClientAutoConfiguration.class
    })
@ConditionalOnClass(CrossServerProperties.class)
@EnableConfigurationProperties(CrossServerProperties.class)
public class CrossServerAutoConfiguration {}
