package com.hyuuny.advanced.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class Pointcuts {

  @Pointcut("execution( * com.hyuuny.advanced.order..*(..))") // 포인트컷
  public void allOrder() { // pointcut signature
  }

  // 클래스 이름 패턴이 *Service
  @Pointcut("execution(* *..*Service.*(..))")
  public void allService() {
  }

  @Pointcut("allOrder() && allService()")
  public void orderAndService() {

  }

}
