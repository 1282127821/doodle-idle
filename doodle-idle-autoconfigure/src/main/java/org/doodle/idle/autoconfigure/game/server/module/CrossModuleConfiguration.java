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
import org.doodle.idle.framework.cross.reactive.RoleCrossOperationHandler;
import org.doodle.idle.framework.cross.reactive.ServerCrossOperationHandler;
import org.doodle.idle.game.server.GameRoleContext;
import org.doodle.idle.game.server.GameServerContext;
import org.doodle.idle.game.server.bootstrap.RoleBootstrapModule;
import org.doodle.idle.game.server.bootstrap.ServerBootstrapModule;
import org.doodle.idle.game.server.module.cross.CrossController;
import org.doodle.idle.game.server.module.cross.CrossRoleModule;
import org.doodle.idle.game.server.module.cross.CrossServerModule;
import org.doodle.idle.game.server.support.GameRoleContextMethodArgumentResolver;
import org.doodle.idle.game.server.support.GameServerContextMethodArgumentResolver;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class CrossModuleConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public ServerCrossOperationHandler serverCrossOperationHandler() {
    ServerCrossOperationHandler operationHandler = new ServerCrossOperationHandler();
    operationHandler
        .getArgumentResolverConfigurer()
        .addCustomResolver(new GameServerContextMethodArgumentResolver());
    return operationHandler;
  }

  @Bean
  @ConditionalOnMissingBean
  public RoleCrossOperationHandler roleCrossOperationHandler() {
    RoleCrossOperationHandler operationHandler = new RoleCrossOperationHandler();
    operationHandler
        .getArgumentResolverConfigurer()
        .addCustomResolver(new GameRoleContextMethodArgumentResolver());
    return operationHandler;
  }

  @Bean
  @ConditionalOnMissingBean
  public CrossController<? extends GameRoleContext> crossController() {
    return new CrossController<>();
  }

  @Bean
  @ConditionalOnMissingBean
  public CrossServerModule<? extends GameServerContext> crossServerModule(
      ServerCrossOperationHandler operationHandler,
      ServerBootstrapModule<? extends GameServerContext> registry) {
    return registry.add(new CrossServerModule<>(new OperationRequester(operationHandler)));
  }

  @Bean
  @ConditionalOnMissingBean
  public CrossRoleModule<? extends GameRoleContext> crossRoleModule(
      RoleCrossOperationHandler operationHandler,
      RoleBootstrapModule<? extends GameRoleContext> registry) {
    return registry.add(new CrossRoleModule<>(new OperationRequester(operationHandler)));
  }
}
