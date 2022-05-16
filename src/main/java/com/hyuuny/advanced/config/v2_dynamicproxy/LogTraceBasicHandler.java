package com.hyuuny.advanced.config.v2_dynamicproxy;

import com.hyuuny.advanced.trace.TraceStatus;
import com.hyuuny.advanced.trace.logtrace.LogTrace;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LogTraceBasicHandler implements InvocationHandler {

  private final Object target;
  private final LogTrace logTrace;

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

    TraceStatus status = null;
    try {
      String message = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
      status = logTrace.begin(message);

      // 로직 호출
      Object result = method.invoke(target, args);
      logTrace.end(status);
      return result;
    } catch (Exception e) {
      logTrace.exception(status, e);
      throw e;
    }
  }
}