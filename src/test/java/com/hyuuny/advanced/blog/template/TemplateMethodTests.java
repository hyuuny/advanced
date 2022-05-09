package com.hyuuny.advanced.blog.template;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTests {

  @Test
  void templateMethodV0() {
    startCookingKoreanChef();
    startCookingChineseChef();
  }

  private void startCookingKoreanChef() {
    long startTime = System.currentTimeMillis();

    // 비지니스 로직 실행
    log.info("한식 요리 시작 !");
    // 비지니스 로직 종료

    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;
    log.info("resultTime={}", resultTime);
  }

  private void startCookingChineseChef() {
    long startTime = System.currentTimeMillis();

    // 비지니스 로직 실행
    log.info("중식 요리 시작 !");
    // 비지니스 로직 종료

    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;
    log.info("resultTime={}", resultTime);
  }

  /**
   * 템플릿 메서드 패턴 적용
   */
  @Test
  void templateMethodV1() {
    Chef koreanChef = new KoreanChef();
    koreanChef.startCook();

    Chef chineseChef = new ChineseChef();
    chineseChef.startCook();
  }

}