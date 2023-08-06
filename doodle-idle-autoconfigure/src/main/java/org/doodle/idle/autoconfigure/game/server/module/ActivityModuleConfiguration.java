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
import org.doodle.idle.framework.activity.reactive.RoleActivityOperationHandler;
import org.doodle.idle.framework.activity.reactive.ServerActivityOperationHandler;
import org.doodle.idle.game.server.GameRoleContext;
import org.doodle.idle.game.server.GameServerContext;
import org.doodle.idle.game.server.bootstrap.RoleBootstrapModule;
import org.doodle.idle.game.server.bootstrap.ServerBootstrapModule;
import org.doodle.idle.game.server.module.activity.ActivityController;
import org.doodle.idle.game.server.module.activity.ActivityRoleModule;
import org.doodle.idle.game.server.module.activity.ActivityServerModule;
import org.doodle.idle.game.server.support.GameRoleContextMethodArgumentResolver;
import org.doodle.idle.game.server.support.GameServerContextMethodArgumentResolver;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class ActivityModuleConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public ServerActivityOperationHandler serverActivityOperationHandler() {
    ServerActivityOperationHandler operationHandler = new ServerActivityOperationHandler();
    operationHandler
        .getArgumentResolverConfigurer()
        .addCustomResolver(new GameServerContextMethodArgumentResolver());
    return operationHandler;
  }

  @Bean
  @ConditionalOnMissingBean
  public RoleActivityOperationHandler roleActivityOperationHandler() {
    RoleActivityOperationHandler operationHandler = new RoleActivityOperationHandler();
    operationHandler
        .getArgumentResolverConfigurer()
        .addCustomResolver(new GameRoleContextMethodArgumentResolver());
    return operationHandler;
  }

  @Bean
  @ConditionalOnMissingBean
  public ActivityController activityController() {
    return new ActivityController();
  }

  @Bean
  @ConditionalOnMissingBean
  public ActivityServerModule<? extends GameServerContext> activityServerModule(
      ServerActivityOperationHandler operationHandler, ServerBootstrapModule registry) {
    return registry.add(new ActivityServerModule<>(new OperationRequester(operationHandler)));
  }

  @Bean
  @ConditionalOnMissingBean
  public ActivityRoleModule<? extends GameRoleContext> activityRoleModule(
      RoleActivityOperationHandler operationHandler, RoleBootstrapModule registry) {
    return registry.add(new ActivityRoleModule<>(new OperationRequester(operationHandler)));
  }
}
