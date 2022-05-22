package com.hyuuny.advanced.config.v6_aop.aspect;

import com.hyuuny.advanced.trace.TraceStatus;
import com.hyuuny.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@RequiredArgsConstructor
@Aspect
public class LogTraceAspect {

  private final LogTrace logTrace;

  @Around("execution(* com.hyuuny.advanced.app..*(..))")
  public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
    TraceStatus status = null;
    try {
      String message = joinPoint.getSignature().toShortString();
      status = logTrace.begin(message);

      Object result = joinPoint.proceed();

      // 로직 호출
      logTrace.end(status);
      return result;
    } catch (Exception e) {
      logTrace.exception(status, e);
      throw e;
    }
  }

}
