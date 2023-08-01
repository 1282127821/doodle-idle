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
import org.doodle.idle.game.server.module.login.LoginController;
import org.doodle.idle.game.server.module.login.LoginRoleModule;
import org.doodle.idle.game.server.module.login.LoginServerModule;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class LoginModuleConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public LoginController loginController() {
    return new LoginController();
  }

  @Bean
  @ConditionalOnMissingBean
  public LoginServerModule loginServerModule(ServerModuleRegistry<Object> registry) {
    return registry.add(new LoginServerModule());
  }

  @Bean
  @ConditionalOnMissingBean
  public LoginRoleModule loginRoleModule(RoleModuleRegistry<Object> registry) {
    return registry.add(new LoginRoleModule());
  }
}
