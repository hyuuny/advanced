package com.hyuuny.advanced.blog.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Context {

  private Strategy strategy;

  public Context(Strategy strategy) {
    this.strategy = strategy;
  }

  public void execute() {
    long startTime = System.currentTimeMillis();

    // 비지니스 로직 실행
    strategy.call(); // 위임
    // 비지니스 로직 종료

    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;
    log.info("resultTime={}", resultTime);
  }

}
