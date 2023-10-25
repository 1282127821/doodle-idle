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
package org.doodle.idle.autoconfigure.console.vaadin;

import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.sidenav.SideNavItem;
import org.doodle.boot.vaadin.EnableVaadin;
import org.doodle.boot.vaadin.views.VaadinSideNavItemSupplier;
import org.doodle.idle.console.vaadin.ConsoleVaadinProperties;
import org.doodle.idle.console.vaadin.views.*;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnClass(ConsoleVaadinProperties.class)
@EnableConfigurationProperties(ConsoleVaadinProperties.class)
@EnableVaadin(ConsoleVaadinProperties.PREFIX_VIEWS)
public class ConsoleVaadinAutoConfiguration {

  @Bean
  public VaadinSideNavItemSupplier consoleVaadinSideNavItemSupplier() {
    return authenticationContext -> {
      SideNavItem item = new SideNavItem("运维组件");
      item.setPrefixComponent(VaadinIcon.SERVER.create());
      item.addItem(
          new SideNavItem("ECS服务器", ConsoleVaadinEcsView.class, VaadinIcon.SERVER.create()));
      item.addItem(new SideNavItem("数据库", ConsoleVaadinDbView.class, VaadinIcon.DATABASE.create()));
      item.addItem(
          new SideNavItem("组件", ConsoleVaadinComponentView.class, VaadinIcon.SERVER.create()));
      item.addItem(
          new SideNavItem("游戏服", ConsoleVaadinGameView.class, VaadinIcon.GAMEPAD.create()));
      item.addItem(
          new SideNavItem("跨服", ConsoleVaadinCrossView.class, VaadinIcon.CROSSHAIRS.create()));
      return item;
    };
  }
}
