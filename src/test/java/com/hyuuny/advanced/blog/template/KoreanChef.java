package com.hyuuny.advanced.blog.template;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KoreanChef extends Chef {

  @Override
  protected void call() {
    log.info("한식 요리 시작 !");
  }

}
