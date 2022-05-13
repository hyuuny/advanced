package com.hyuuny.advanced.pureproxy.decorator.code;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class DecoratorPatternClient {

  private Component component;

  public void execute() {
    String result = component.operation();
    log.info("result={}", result);
  }

}
