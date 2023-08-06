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
package org.doodle.idle.game.server.module.fight;

import org.doodle.design.messaging.packet.PacketMapping;
import org.doodle.idle.game.server.GameRoleContext;
import org.doodle.idle.game.server.module.ModuleId;

/**
 * 玩家战斗模块消息处理
 *
 * @param <R> 玩家角色
 * @author tingyanshen
 */
@PacketMapping(ModuleId.FIGHT)
public class FightController<R extends GameRoleContext> {}
