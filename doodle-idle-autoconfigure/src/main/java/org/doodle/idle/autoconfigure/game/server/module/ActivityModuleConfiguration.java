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
package org.doodle.idle.autoconfigure.game.server.module;

import org.doodle.design.messaging.operation.reactive.OperationRequester;
import org.doodle.idle.framework.activity.reactive.ServerActivityOperationHandler;
import org.doodle.idle.game.server.bootstrap.RoleBootstrapModule;
import org.doodle.idle.game.server.bootstrap.ServerBootstrapModule;
import org.doodle.idle.game.server.module.activity.ActivityController;
import org.doodle.idle.game.server.module.activity.ActivityRoleModule;
import org.doodle.idle.game.server.module.activity.ActivityServerModule;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class ActivityModuleConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public ServerActivityOperationHandler serverActivityOperationHandler() {
    return new ServerActivityOperationHandler();
  }

  @Bean
  @ConditionalOnMissingBean
  public ActivityController activityController() {
    return new ActivityController();
  }

  @Bean
  @ConditionalOnMissingBean
  public ActivityServerModule activityServerModule(
      ServerActivityOperationHandler operationHandler, ServerBootstrapModule registry) {
    return registry.add(new ActivityServerModule(new OperationRequester(operationHandler)));
  }

  @Bean
  @ConditionalOnMissingBean
  public ActivityRoleModule activityRoleModule(RoleBootstrapModule registry) {
    return registry.add(new ActivityRoleModule());
  }
}
