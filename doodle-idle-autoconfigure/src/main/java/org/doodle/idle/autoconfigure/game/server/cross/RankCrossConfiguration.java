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
import org.doodle.idle.game.server.cross.rank.RankController;
import org.doodle.idle.game.server.cross.rank.RankRoleCross;
import org.doodle.idle.game.server.cross.rank.RankServerCross;
import org.doodle.idle.game.server.module.cross.CrossRoleModule;
import org.doodle.idle.game.server.module.cross.CrossServerModule;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class RankCrossConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public RankController<? extends GameRoleContext> rankController() {
    return new RankController<>();
  }

  @Bean
  @ConditionalOnMissingBean
  public RankServerCross<? extends GameServerContext> rankServerCross(
      CrossServerModule<? extends GameServerContext> registry) {
    return registry.add(new RankServerCross<>());
  }

  @Bean
  @ConditionalOnMissingBean
  public RankRoleCross<? extends GameRoleContext> rankRoleCross(
      CrossRoleModule<? extends GameRoleContext> registry) {
    return registry.add(new RankRoleCross<>());
  }
}
