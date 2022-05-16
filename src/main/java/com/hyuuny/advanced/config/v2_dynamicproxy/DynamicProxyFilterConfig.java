package com.hyuuny.advanced.config.v2_dynamicproxy;

import com.hyuuny.advanced.app.v1.OrderControllerV1;
import com.hyuuny.advanced.app.v1.OrderControllerV1Impl;
import com.hyuuny.advanced.app.v1.OrderRepositoryV1;
import com.hyuuny.advanced.app.v1.OrderRepositoryV1Impl;
import com.hyuuny.advanced.app.v1.OrderServiceV1;
import com.hyuuny.advanced.app.v1.OrderServiceV1Impl;
import com.hyuuny.advanced.trace.logtrace.LogTrace;
import java.lang.reflect.Proxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamicProxyFilterConfig {

  private static final String[] PATTERNS = {"request*", "order*", "save*"};

  @Bean
  public OrderControllerV1 orderControllerV1(LogTrace logTrace) {
    OrderControllerV1Impl orderController = new OrderControllerV1Impl(orderServiceV1(logTrace));

    OrderControllerV1 proxy = (OrderControllerV1) Proxy.newProxyInstance(
        OrderControllerV1.class.getClassLoader(),
        new Class[]{OrderControllerV1.class},
        new LogTraceFilterHandler(orderController, logTrace, PATTERNS)
    );
    return proxy;
  }

  @Bean
  public OrderServiceV1 orderServiceV1(LogTrace logTrace) {
    OrderServiceV1Impl orderService = new OrderServiceV1Impl(orderRepositoryV1(logTrace));

    OrderServiceV1 proxy = (OrderServiceV1) Proxy.newProxyInstance(
        OrderServiceV1.class.getClassLoader(),
        new Class[]{OrderServiceV1.class},
        new LogTraceFilterHandler(orderService, logTrace, PATTERNS)
    );
    return proxy;
  }

  @Bean
  public OrderRepositoryV1 orderRepositoryV1(LogTrace logTrace) {
    OrderRepositoryV1 orderRepository = new OrderRepositoryV1Impl();

    OrderRepositoryV1 proxy = (OrderRepositoryV1) Proxy.newProxyInstance(
        OrderRepositoryV1.class.getClassLoader(),
        new Class[]{OrderRepositoryV1.class},
        new LogTraceFilterHandler(orderRepository, logTrace, PATTERNS)
    );
    return proxy;
  }


}
