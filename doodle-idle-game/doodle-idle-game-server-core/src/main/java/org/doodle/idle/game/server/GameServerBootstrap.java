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
package org.doodle.idle.game.server;

import java.util.concurrent.atomic.AtomicBoolean;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.SmartLifecycle;

/**
 * 游戏服启动
 *
 * @author tingyanshen
 */
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class GameServerBootstrap implements SmartLifecycle {
  GameServerContext serverContext;
  AtomicBoolean running = new AtomicBoolean(false);

  @Override
  public void start() {
    if (running.compareAndSet(false, true)) {
      serverContext.prepare();
      serverContext.patch();
      serverContext.start();
    }
  }

  @Override
  public void stop() {
    serverContext.stop();
    serverContext.save();
  }

  @Override
  public boolean isRunning() {
    return running.get();
  }
}
