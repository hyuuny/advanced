package com.hyuuny.advanced.config.v4_postpocessor;

import com.hyuuny.advanced.config.AppV1Config;
import com.hyuuny.advanced.config.AppV2Config;
import com.hyuuny.advanced.config.v3_proxyfactory.LogTraceAdvice;
import com.hyuuny.advanced.config.v4_postpocessor.postprocessor.PackageLogTracePostProcessor;
import com.hyuuny.advanced.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class BeanPostProcessorConfig {

  @Bean
  public PackageLogTracePostProcessor logTracePostProcessor(LogTrace logTrace) {
    return new PackageLogTracePostProcessor("com.hyuuny.advanced.app", getAdvisor(logTrace));
  }

  private Advisor getAdvisor(LogTrace logTrace) {
    // pointcut
    NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
    pointcut.setMappedNames("request*", "order*", "save*");

    // advice
    LogTraceAdvice advice = new LogTraceAdvice(logTrace);
    return new DefaultPointcutAdvisor(pointcut, advice);
  }

}
