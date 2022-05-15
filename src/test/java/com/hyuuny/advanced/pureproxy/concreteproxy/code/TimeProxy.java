package com.hyuuny.advanced.pureproxy.concreteproxy.code;

import com.hyuuny.advanced.pureproxy.concreteproxy.ConcreteLogic;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class TimeProxy extends ConcreteLogic {

  private ConcreteLogic concreteLogic;

  @Override
  public String operation() {
    log.info("TimeDecorator 실행");
    long startTime = System.currentTimeMillis();

    String result = concreteLogic.operation();

    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;
    log.info("TimeDecorator 종료 resultTime={}ms", resultTime);
    return result;
  }

}
