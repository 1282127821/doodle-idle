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
package org.doodle.idle.game.server.cross.rank;

import lombok.extern.slf4j.Slf4j;
import org.doodle.idle.framework.cross.annotation.RoleCross;
import org.doodle.idle.framework.lifecycle.annotation.OnOnline;
import org.doodle.idle.framework.lifecycle.annotation.OnPatch;
import org.doodle.idle.game.server.GameRoleContext;

@Slf4j
@RoleCross
public class RankRoleCross<R extends GameRoleContext> {

  @OnPatch
  public void onPatch(R role) {
    log.info("跨服排行榜 [角色]");
  }

  @OnOnline
  public void onOnline(R role) {
    log.info("跨服排行榜 [角色]");
  }
}
