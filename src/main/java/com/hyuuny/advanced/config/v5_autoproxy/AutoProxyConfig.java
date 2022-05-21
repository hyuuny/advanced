package com.hyuuny.advanced.config.v5_autoproxy;

import com.hyuuny.advanced.config.AppV1Config;
import com.hyuuny.advanced.config.AppV2Config;
import com.hyuuny.advanced.config.v3_proxyfactory.LogTraceAdvice;
import com.hyuuny.advanced.trace.logtrace.LogTrace;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class AutoProxyConfig {

//  @Bean
  public Advisor advisor1(LogTrace logTrace) {
    // pointcut
    NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
    pointcut.setMappedNames("request*", "order*", "save*");

    // advice
    LogTraceAdvice advice = new LogTraceAdvice(logTrace);
    return new DefaultPointcutAdvisor(pointcut, advice);
  }

//  @Bean
  public Advisor advisor2(LogTrace logTrace) {
    // pointcut
    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    pointcut.setExpression("execution(* com.hyuuny.advanced.app..*(..))");

    // advice
    LogTraceAdvice advice = new LogTraceAdvice(logTrace);
    return new DefaultPointcutAdvisor(pointcut, advice);
  }

  @Bean
  public Advisor advisor3(LogTrace logTrace) {
    // pointcut
    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    pointcut.setExpression("execution(* com.hyuuny.advanced.app..*(..)) && !execution(* com.hyuuny.advanced.app..noLog(..))");

    // advice
    LogTraceAdvice advice = new LogTraceAdvice(logTrace);
    return new DefaultPointcutAdvisor(pointcut, advice);
  }

}