package com.hyuuny.advanced.jdkdynamic;

import com.hyuuny.advanced.jdkdynamic.code.AImpl;
import com.hyuuny.advanced.jdkdynamic.code.AInterface;
import com.hyuuny.advanced.jdkdynamic.code.BImpl;
import com.hyuuny.advanced.jdkdynamic.code.BInterface;
import com.hyuuny.advanced.jdkdynamic.code.TimeInvocationHandler;
import java.lang.reflect.Proxy;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class JdkDynamicProxyTest {

  @Test
  void dynamicA() {
    AImpl target = new AImpl();
    TimeInvocationHandler handler = new TimeInvocationHandler(target);

    AInterface proxy = (AInterface) Proxy.newProxyInstance(
        AInterface.class.getClassLoader(),
        new Class[]{AInterface.class},
        handler
    );

    proxy.call();
    log.info("targetClass={}", target.getClass());
    log.info("proxyClass={}", proxy.getClass());
  }

  @Test
  void dynamicB() {
    BImpl target = new BImpl();
    TimeInvocationHandler handler = new TimeInvocationHandler(target);

    BInterface proxy = (BInterface) Proxy.newProxyInstance(
        AInterface.class.getClassLoader(),
        new Class[]{BInterface.class},
        handler
    );

    proxy.call();
    log.info("targetClass={}", target.getClass());
    log.info("proxyClass={}", proxy.getClass());
  }

}
