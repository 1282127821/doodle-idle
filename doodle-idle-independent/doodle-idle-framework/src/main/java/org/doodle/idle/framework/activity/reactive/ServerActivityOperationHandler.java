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
package org.doodle.idle.framework.activity.reactive;

import java.util.List;
import org.doodle.design.messaging.operation.reactive.OperationMessageHandler;
import org.doodle.idle.framework.activity.annotation.ServerActivity;
import org.doodle.idle.framework.lifecycle.annotation.*;
import org.doodle.idle.framework.timer.annotation.*;
import org.springframework.messaging.handler.invocation.AbstractExceptionHandlerMethodResolver;

/**
 * 服务模块操作处理器
 *
 * @author tingyanshen
 */
public class ServerActivityOperationHandler extends OperationMessageHandler {

  public ServerActivityOperationHandler() {
    super(
        ServerActivity.class,
        List.of(
            OnPrepare.class,
            OnPatch.class,
            OnOneIteration.class,
            OnStart.class,
            OnStop.class,
            OnSave.class,
            OnDayElapse.class,
            OnWeekElapse.class,
            OnMonthElapse.class,
            OnYearElapse.class));
  }

  @Override
  protected AbstractExceptionHandlerMethodResolver createExceptionMethodResolverFor(
      Class<?> beanType) {
    return new ActivityExceptionHandlerMethodResolver(beanType);
  }
}
