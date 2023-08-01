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
package org.doodle.idle.framework.module;

import java.lang.reflect.AnnotatedElement;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import org.doodle.design.messaging.operation.reactive.OrderedOperationMessageHandler;
import org.doodle.idle.framework.module.annotation.OnStart;
import org.doodle.idle.framework.module.annotation.OnStop;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.messaging.handler.CompositeMessageCondition;
import org.springframework.messaging.handler.DestinationPatternsMessageCondition;

public class ModuleOperationHandler extends OrderedOperationMessageHandler {

  public ModuleOperationHandler(Supplier<List<Object>> handlerSupplier) {
    super(handlerSupplier);
    setHandlerPredicate(type -> AnnotatedElementUtils.hasAnnotation(type, Module.class));
    setAnnotations(List.of(OnStart.class, OnStop.class));
  }

  @Override
  protected CompositeMessageCondition getOperationCondition(AnnotatedElement element) {
    Module module = AnnotatedElementUtils.findMergedAnnotation(element, Module.class);
    if (Objects.nonNull(module)) {
      return new CompositeMessageCondition(
          new DestinationPatternsMessageCondition(((Class<?>) element).getName()));
    }
    return null;
  }
}
