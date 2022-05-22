package com.hyuuny.advanced.config.v6_aop;

import com.hyuuny.advanced.config.AppV1Config;
import com.hyuuny.advanced.config.AppV2Config;
import com.hyuuny.advanced.config.v6_aop.aspect.LogTraceAspect;
import com.hyuuny.advanced.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class AopConfig {

  @Bean
  public LogTraceAspect logTraceAspect(LogTrace logTrace) {
    return new LogTraceAspect(logTrace);
  }

}
