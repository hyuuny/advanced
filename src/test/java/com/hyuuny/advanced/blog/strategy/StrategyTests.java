package com.hyuuny.advanced.blog.strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class StrategyTests {

  /**
   * 전략 패턴 적용
   */
  @Test
  void strategyV1() {
    KoreanChef koreanChef = new KoreanChef();
    Context koreanChefContext = new Context(koreanChef);
    koreanChefContext.execute();

    ChineseChef ChineseChef = new ChineseChef();
    Context chineseChefContext = new Context(ChineseChef);
    chineseChefContext.execute();
  }

}
