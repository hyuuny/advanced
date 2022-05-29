package com.hyuuny.advanced;

import static org.springframework.aop.support.AopUtils.isAopProxy;

import com.hyuuny.advanced.order.OrderRepository;
import com.hyuuny.advanced.order.OrderService;
import com.hyuuny.advanced.order.aop.AspectV6Advice;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
//@Import(AspectV1.class)
//@Import(AspectV2.class)
//@Import(AspectV3.class)
//@Import(AspectV4Pointcut.class)
//@Import({AspectV5Order.LogAspect.class, AspectV5Order.TxAspect.class})
@Import(AspectV6Advice.class)
public class AopTest {

  @Autowired
  OrderService orderService;

  @Autowired
  OrderRepository orderRepository;

  @Test
  void aopInfo() {
    log.info("isAopProxy, orderService={}", isAopProxy(orderService));
    log.info("isAopProxy, orderRepository={}", isAopProxy(orderRepository));
  }

  @Test
  void success() {
    orderService.orderItem("itemA");
  }

  @Test
  void exception() {
    Assertions.assertThatThrownBy(() -> orderService.orderItem("ex"))
        .isInstanceOf(IllegalStateException.class);
  }

}
