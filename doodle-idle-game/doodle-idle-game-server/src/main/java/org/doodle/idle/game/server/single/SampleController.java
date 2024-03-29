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
package org.doodle.idle.game.server.single;

import io.rsocket.Socket;
import lombok.extern.slf4j.Slf4j;
import org.doodle.design.messaging.packet.PacketExceptionHandler;
import org.doodle.design.messaging.packet.PacketMapping;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@PacketMapping(1)
public class SampleController {

  @PacketMapping(1)
  public void handleMsg(Socket socket, String payload) {
    log.info("接收到客户端请求: {}", payload);
  }

  @PacketExceptionHandler(Exception.class)
  public void onException(Exception e) {
    log.error("异常", e);
  }
}
