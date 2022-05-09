package com.hyuuny.advanced.blog.callback;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateCallbackTests {

  /**
   * 템플릿 콜백 패턴 - 익명 내부 클래스
   */
  @Test
  void anonymousCallback() {
    Template template = new Template();
    template.execute(new Callback() {
      @Override
      public void call() {
        log.info("한식 요리 시작 !");
      }
    });

    template.execute(new Callback() {
      @Override
      public void call() {
        log.info("중식 요리 시작 !");
      }
    });
  }

  /**
   * 템플릿 콜백 패턴 - 람다
   */
  @Test
  void lambdaCallback() {
    Template template = new Template();
    template.execute(() -> log.info("한식 요리 시작 !"));
    template.execute(() -> log.info("중식 요리 시작 !"));
  }

}
