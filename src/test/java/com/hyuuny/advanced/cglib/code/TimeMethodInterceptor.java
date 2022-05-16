package com.hyuuny.advanced.cglib.code;

import java.lang.reflect.Method;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

@Slf4j
@AllArgsConstructor
public class TimeMethodInterceptor implements MethodInterceptor {

  private final Object target;

  @Override
  public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
    log.info("TimeProxy 실행");
    long startTime = System.currentTimeMillis();

    Object result = method.invoke(target, args);

    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;
    log.info("resultTime={}", resultTime);

    return result;
  }
}
