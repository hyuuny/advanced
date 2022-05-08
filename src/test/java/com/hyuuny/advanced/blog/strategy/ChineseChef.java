package com.hyuuny.advanced.blog.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChineseChef implements Strategy{

  @Override
  public void call() {
    log.info("중식 요리 시작 !");
  }
}
