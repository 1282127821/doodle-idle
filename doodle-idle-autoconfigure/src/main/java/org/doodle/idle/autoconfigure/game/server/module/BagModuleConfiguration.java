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

import org.doodle.idle.framework.module.RoleModuleRegistry;
import org.doodle.idle.framework.module.ServerModuleRegistry;
import org.doodle.idle.game.server.module.bag.BagController;
import org.doodle.idle.game.server.module.bag.BagRoleModule;
import org.doodle.idle.game.server.module.bag.BagServerModule;
import org.doodle.idle.game.server.module.fight.FightServerModule;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class BagModuleConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public BagController bagController() {
    return new BagController();
  }

  @Bean
  @ConditionalOnMissingBean
  public BagServerModule bagServerModule(
      ServerModuleRegistry<Object> registry, FightServerModule fightServerModule) {
    return registry.add(new BagServerModule(fightServerModule));
  }

  @Bean
  @ConditionalOnMissingBean
  public BagRoleModule bagRoleModule(RoleModuleRegistry<Object> registry) {
    return registry.add(new BagRoleModule());
  }
}
