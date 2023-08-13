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
import lombok.extern.slf4j.Slf4j;
import org.doodle.design.messaging.operation.reactive.OperationRequester;
import org.doodle.idle.framework.context.BaseContext;
import org.springframework.messaging.support.MessageHeaderAccessor;

/**
 * 玩家角色上下文
 *
 * @author tingyanshen
 */
@Slf4j
public class GameRoleContext extends BaseContext {
  public static final String GAME_ROLE_CONTEXT = "GAME_ROLE_CONTEXT";

  public GameRoleContext(OperationRequester requester, List<Object> handlers) {
    super(requester, handlers);
  }

  @Override
  protected void initHeaders(MessageHeaderAccessor headerAccessor) {
    headerAccessor.setHeader(GAME_ROLE_CONTEXT, this);
  }
}
