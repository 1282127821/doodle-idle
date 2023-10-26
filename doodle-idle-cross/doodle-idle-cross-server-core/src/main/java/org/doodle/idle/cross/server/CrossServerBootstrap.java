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
package org.doodle.idle.cross.server;

import java.util.concurrent.atomic.AtomicBoolean;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.SmartLifecycle;
import reactor.core.publisher.Mono;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CrossServerBootstrap implements SmartLifecycle {
  CrossServerContext serverContext;

  AtomicBoolean running = new AtomicBoolean(false);

  @Override
  public void start() {
    if (running.compareAndSet(false, true)) {
      Mono.when(serverContext.prepare(), serverContext.patch(), serverContext.start())
          .doFirst(() -> log.info("启动跨服"))
          .block();
    }
  }

  @Override
  public void stop() {
    Mono.when(serverContext.stop(), serverContext.save()).doFirst(() -> log.info("关闭跨服")).block();
  }

  @Override
  public boolean isRunning() {
    return running.get();
  }
}
