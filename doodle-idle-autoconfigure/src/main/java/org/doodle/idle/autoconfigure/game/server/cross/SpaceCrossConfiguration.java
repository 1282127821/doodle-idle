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
package org.doodle.idle.autoconfigure.game.server.cross;

import org.doodle.idle.game.server.GameRoleContext;
import org.doodle.idle.game.server.GameServerContext;
import org.doodle.idle.game.server.cross.space.SpaceController;
import org.doodle.idle.game.server.cross.space.SpaceServerCross;
import org.doodle.idle.game.server.module.cross.CrossServerModule;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class SpaceCrossConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public SpaceController<? extends GameRoleContext> spaceController() {
    return new SpaceController<>();
  }

  @Bean
  @ConditionalOnMissingBean
  public SpaceServerCross<? extends GameServerContext> spaceServerCross(
      CrossServerModule<? extends GameServerContext> registry) {
    return registry.add(new SpaceServerCross<>());
  }
}
