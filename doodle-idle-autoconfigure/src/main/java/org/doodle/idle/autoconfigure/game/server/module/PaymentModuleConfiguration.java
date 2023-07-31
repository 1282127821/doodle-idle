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
package org.doodle.idle.autoconfigure.game.server.module;

import org.doodle.idle.game.server.module.payment.PaymentController;
import org.doodle.idle.game.server.module.payment.PaymentModule;
import org.doodle.payment.autoconfigure.client.PaymentClientAutoConfiguration;
import org.doodle.payment.client.PaymentClientDeliverHandler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration(after = PaymentClientAutoConfiguration.class)
public class PaymentModuleConfiguration {

  /**
   * 为支付组件客户端提供 rsocket 支付回调
   *
   * @return 支付组件客户端订单兑现处理器
   */
  @Bean
  public PaymentClientDeliverHandler paymentClientDeliverHandler() {
    return deliverRequest -> null;
  }

  @Bean
  @ConditionalOnMissingBean
  public PaymentController paymentController() {
    return new PaymentController();
  }

  @Bean
  @ConditionalOnMissingBean
  public PaymentModule paymentModule() {
    return new PaymentModule();
  }
}
