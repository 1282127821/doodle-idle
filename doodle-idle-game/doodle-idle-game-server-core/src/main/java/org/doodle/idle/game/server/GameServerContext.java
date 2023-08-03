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

import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.doodle.design.messaging.operation.reactive.OperationRequester;
import org.doodle.idle.framework.module.annotation.*;
import org.springframework.lang.NonNull;
import org.springframework.messaging.support.MessageHeaderAccessor;

/**
 * 游戏服上下文
 *
 * @author tingyanshen
 */
@Slf4j
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class GameServerContext {
  public static final String GAME_SERVER_CONTEXT = "GAME_SERVER_CONTEXT";

  OperationRequester requester;
  List<Class<?>> handlers;

  public void initHeaders(@NonNull MessageHeaderAccessor headerAccessor) {
    headerAccessor.setHeader(GAME_SERVER_CONTEXT, this);
  }

  public void prepare() {
    requester
        .annotation(OnPrepare.class)
        .handlers(handlers)
        .header(this::initHeaders)
        .natureOrder()
        .block();
  }

  public void oneIteration() {
    requester
        .annotation(OnOneIteration.class)
        .handlers(handlers)
        .header(this::initHeaders)
        .natureOrder()
        .block();
  }

  public void start() {
    requester
        .annotation(OnStart.class)
        .handlers(handlers)
        .header(this::initHeaders)
        .reversedOrder()
        .block();
  }

  public void stop() {
    requester
        .annotation(OnStop.class)
        .handlers(handlers)
        .header(this::initHeaders)
        .reversedOrder()
        .block();
  }

  public void save() {
    requester
        .annotation(OnSave.class)
        .handlers(handlers)
        .header(this::initHeaders)
        .natureOrder()
        .block();
  }

  public void dayElapse() {
    requester
        .annotation(OnDayElapse.class)
        .handlers(handlers)
        .header(this::initHeaders)
        .natureOrder()
        .block();
  }

  public void weekElapse() {
    requester
        .annotation(OnWeekElapse.class)
        .handlers(handlers)
        .header(this::initHeaders)
        .natureOrder()
        .block();
  }

  public void monthElapse() {
    requester
        .annotation(OnMonthElapse.class)
        .handlers(handlers)
        .header(this::initHeaders)
        .natureOrder()
        .block();
  }

  public void yearElapse() {
    requester
        .annotation(OnYearElapse.class)
        .handlers(handlers)
        .header(this::initHeaders)
        .natureOrder()
        .block();
  }
}
