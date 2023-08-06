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
package org.doodle.idle.game.server.module;

/**
 * 模块ID
 *
 * @author tingyanshen
 */
public interface ModuleId {

  /** 登录 */
  short LOGIN = 1;

  /** 角色 */
  short ROLE = 2;

  /** 战斗 */
  short FIGHT = 3;

  /** 背包 */
  short BAG = 4;

  /** 邮件 */
  short MAIL = 5;

  /** 任务 */
  short TASK = 6;

  /** 支付 */
  short PAYMENT = 7;

  /** 活动 */
  short ACTIVITY = 100;
}
