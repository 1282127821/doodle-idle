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
package org.doodle.idle.autoconfigure.cross.server.module;

import org.doodle.design.messaging.operation.reactive.OperationRequester;
import org.doodle.idle.cross.server.CrossServerContext;
import org.doodle.idle.cross.server.bootstrap.ServerBootstrapModule;
import org.doodle.idle.cross.server.module.CrossServerModule;
import org.doodle.idle.cross.server.support.CrossServerContextMethodArgumentResolver;
import org.doodle.idle.framework.cross.reactive.ServerCrossOperationHandler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class CrossModuleConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public ServerCrossOperationHandler serverCrossOperationHandler() {
    ServerCrossOperationHandler operationHandler = new ServerCrossOperationHandler();
    operationHandler
        .getArgumentResolverConfigurer()
        .addCustomResolver(new CrossServerContextMethodArgumentResolver());
    return operationHandler;
  }

  @Bean
  @ConditionalOnMissingBean
  public CrossServerModule<? extends CrossServerContext> crossServerModule(
      ServerCrossOperationHandler operationHandler,
      ServerBootstrapModule<? extends CrossServerContext> registry) {
    return registry.add(new CrossServerModule<>(new OperationRequester(operationHandler)));
  }
}
