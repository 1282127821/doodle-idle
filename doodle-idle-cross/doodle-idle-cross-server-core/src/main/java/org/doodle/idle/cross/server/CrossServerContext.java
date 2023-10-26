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

import java.util.List;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.doodle.design.messaging.operation.reactive.OperationRequester;
import org.doodle.idle.framework.context.BaseContext;
import org.springframework.messaging.support.MessageHeaderAccessor;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class CrossServerContext extends BaseContext {
  public static final String CROSS_SERVER_CONTEXT = "CROSS_SERVER_CONTEXT";

  public CrossServerContext(OperationRequester requester, List<Object> handlers) {
    super(requester, handlers);
  }

  @Override
  protected void initHeaders(MessageHeaderAccessor headerAccessor) {
    headerAccessor.setHeader(CROSS_SERVER_CONTEXT, this);
  }
}
