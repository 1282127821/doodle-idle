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

import java.util.Objects;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

/**
 * 模块代理
 *
 * @author tingyanshen
 */
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class DelegatingModule implements ModuleLifecycle {
  ModuleRefresh refresh;
  ModuleShutdown shutdown;

  /**
   * 创建无状态模块代理
   *
   * @param module 无状态模块
   * @return 模块代理
   */
  public static DelegatingModule stateless(StatelessModule module) {
    return new DelegatingModule(module, null);
  }

  /**
   * 创建有状态模块代理
   *
   * @param module 有状态模块
   * @return 模块代理
   */
  public static DelegatingModule stateful(StatefulModule module) {
    return new DelegatingModule(module, module);
  }

  @Override
  public void onRefresh() {
    if (Objects.nonNull(refresh)) {
      refresh.onRefresh();
    }
  }

  @Override
  public void onShutdown() {
    if (Objects.nonNull(shutdown)) {
      shutdown.onShutdown();
    }
  }
}
