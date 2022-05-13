package com.hyuuny.advanced.pureproxy.decorator.code;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class TimeDecorator implements Component {

  private Component component;

  @Override
  public String operation() {
    log.info("TimeDecorator 실행");
    long startTime = System.currentTimeMillis();
    String result = component.operation();
    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;
    log.info("TimeDecorator 종료 resultTime={}ms", resultTime);
    return result;
  }
}
