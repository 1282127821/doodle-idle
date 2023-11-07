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
package org.doodle.idle.framework.context;

import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.doodle.design.messaging.operation.reactive.OperationRequester;
import org.doodle.idle.framework.lifecycle.annotation.*;
import org.doodle.idle.framework.timer.annotation.*;
import org.springframework.messaging.support.MessageHeaderAccessor;
import reactor.core.publisher.Mono;

@Slf4j
@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
@RequiredArgsConstructor
public abstract class BaseContext {
  OperationRequester requester;
  List<Object> handlers;

  protected abstract void initHeaders(MessageHeaderAccessor headerAccessor);

  public Mono<Void> prepare() {
    return this.requester
        .annotation(OnPrepare.class)
        .handlers(handlers)
        .header(this::initHeaders)
        .naturalOrder();
  }

  public Mono<Void> patch() {
    return this.requester
        .annotation(OnPatch.class)
        .handlers(handlers)
        .header(this::initHeaders)
        .naturalOrder();
  }

  public Mono<Void> oneIteration() {
    return this.requester
        .annotation(OnOneIteration.class)
        .handlers(handlers)
        .header(this::initHeaders)
        .naturalOrder();
  }

  public Mono<Void> start() {
    return this.requester
        .annotation(OnStart.class)
        .handlers(handlers)
        .header(this::initHeaders)
        .reverseOrder();
  }

  public Mono<Void> stop() {
    return this.requester
        .annotation(OnStop.class)
        .handlers(handlers)
        .header(this::initHeaders)
        .naturalOrder();
  }

  public Mono<Void> save() {
    return this.requester
        .annotation(OnSave.class)
        .handlers(handlers)
        .header(this::initHeaders)
        .reverseOrder();
  }

  public Mono<Void> dayElapse() {
    return this.requester
        .annotation(OnDayElapse.class)
        .handlers(handlers)
        .header(this::initHeaders)
        .naturalOrder();
  }

  public Mono<Void> weekElapse() {
    return this.requester
        .annotation(OnWeekElapse.class)
        .handlers(handlers)
        .header(this::initHeaders)
        .naturalOrder();
  }

  public Mono<Void> monthElapse() {
    return this.requester
        .annotation(OnMonthElapse.class)
        .handlers(handlers)
        .header(this::initHeaders)
        .naturalOrder();
  }

  public Mono<Void> yearElapse() {
    return this.requester
        .annotation(OnYearElapse.class)
        .handlers(handlers)
        .header(this::initHeaders)
        .naturalOrder();
  }
}
