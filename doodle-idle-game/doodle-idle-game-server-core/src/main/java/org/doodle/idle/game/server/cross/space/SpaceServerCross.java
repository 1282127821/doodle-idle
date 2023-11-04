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
package org.doodle.idle.game.server.cross.space;

import lombok.extern.slf4j.Slf4j;
import org.doodle.idle.framework.cross.annotation.ServerCross;
import org.doodle.idle.framework.lifecycle.annotation.OnStart;
import org.doodle.idle.framework.lifecycle.annotation.OnStop;
import org.doodle.idle.game.server.GameServerContext;

@Slf4j
@ServerCross
public class SpaceServerCross<S extends GameServerContext> {

  @OnStart
  public void onStart(S server) {
    log.info("启动: 跨服时空服务模块");
  }

  @OnStop
  public void onStop(S server) {
    log.info("关闭: 跨服时空服务模块");
  }
}
