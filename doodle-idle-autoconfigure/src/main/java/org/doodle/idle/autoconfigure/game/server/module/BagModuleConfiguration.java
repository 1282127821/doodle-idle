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

import org.doodle.idle.game.server.GameRoleContext;
import org.doodle.idle.game.server.GameServerContext;
import org.doodle.idle.game.server.bootstrap.RoleBootstrapModule;
import org.doodle.idle.game.server.bootstrap.ServerBootstrapModule;
import org.doodle.idle.game.server.module.bag.BagController;
import org.doodle.idle.game.server.module.bag.BagRoleModule;
import org.doodle.idle.game.server.module.bag.BagServerModule;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class BagModuleConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public BagController<? extends GameRoleContext> bagController() {
    return new BagController<>();
  }

  @Bean
  @ConditionalOnMissingBean
  public BagServerModule<? extends GameServerContext> bagServerModule(
      ServerBootstrapModule<? extends GameServerContext> registry) {
    return registry.add(new BagServerModule<>());
  }

  @Bean
  @ConditionalOnMissingBean
  public BagRoleModule<? extends GameRoleContext> bagRoleModule(
      RoleBootstrapModule<? extends GameRoleContext> registry) {
    return registry.add(new BagRoleModule<>());
  }
}
