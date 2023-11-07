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
package org.doodle.idle.cross.rank;

import lombok.extern.slf4j.Slf4j;
import org.doodle.idle.cross.server.CrossServerContext;
import org.doodle.idle.framework.cross.annotation.ServerCross;
import org.doodle.idle.framework.lifecycle.annotation.OnPrepare;
import org.doodle.idle.framework.lifecycle.annotation.OnSave;
import org.doodle.idle.framework.lifecycle.annotation.OnStart;
import org.doodle.idle.framework.lifecycle.annotation.OnStop;

@Slf4j
@ServerCross
public class RankServerCross<S extends CrossServerContext> {

  @OnPrepare
  public void onPrepare(S server) {
    log.info("跨服排行榜");
  }

  @OnStart
  public void onStart(S server) {
    log.info("跨服排行榜");
  }

  @OnStop
  public void onStop(S server) {
    log.info("跨服排行榜");
  }

  @OnSave
  public void onSave(S server) {
    log.info("跨服排行榜");
  }
}
