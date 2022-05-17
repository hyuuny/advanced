package com.hyuuny.advanced.proxyfactory;

import static org.assertj.core.api.Assertions.assertThat;

import com.hyuuny.advanced.common.advice.TimeAdvice;
import com.hyuuny.advanced.common.service.ConcreteService;
import com.hyuuny.advanced.common.service.ServiceImpl;
import com.hyuuny.advanced.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

@Slf4j
public class ProxyFactoryTest {

  @Test
  @DisplayName("인터페이스가 있으면 JDK 동적 프록시 사용")
  void interfaceProxy() {
    ServiceInterface target = new ServiceImpl();
    ProxyFactory proxyFactory = new ProxyFactory(target); // 프록시를 생성할 때, target 클래스를 넘겨준다.
    proxyFactory.addAdvice(new TimeAdvice());
    ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
    log.info("targetClass={}", target.getClass());
    log.info("proxyClass={}", proxy.getClass());

    proxy.save();

    assertThat(AopUtils.isAopProxy(proxy)).isTrue();
    assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue(); // JDK 동적 프록시라면 True
    assertThat(AopUtils.isCglibProxy(proxy)).isFalse(); // CGLIB 동적 프록시라면 True
  }

  @Test
  @DisplayName("구체 클래스만 있으면 CGLIB 사용")
  void concreteProxy() {
    ConcreteService target = new ConcreteService();
    ProxyFactory proxyFactory = new ProxyFactory(target); // 프록시를 생성할 때, target 클래스를 넘겨준다.
    proxyFactory.addAdvice(new TimeAdvice());
    ConcreteService proxy = (ConcreteService) proxyFactory.getProxy();
    log.info("targetClass={}", target.getClass());
    log.info("proxyClass={}", proxy.getClass());

    proxy.call();

    assertThat(AopUtils.isAopProxy(proxy)).isTrue();
    assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse(); // JDK 동적 프록시라면 True
    assertThat(AopUtils.isCglibProxy(proxy)).isTrue(); // CGLIB 동적 프록시라면 True
  }

  @Test
  @DisplayName("proxyTargetClass 옵션을 사용하면 인터페이스가 있어도 CGLIB를 사용하고, 클래스 기반 프록시 사용")
  void proxyTargetClass() {
    ServiceInterface target = new ServiceImpl();
    ProxyFactory proxyFactory = new ProxyFactory(target); // 프록시를 생성할 때, target 클래스를 넘겨준다.
    proxyFactory.setProxyTargetClass(true); // setProxyTargetClass를 true로 주면 인터페이스가 있어도 CGLIB를 기반으로 사용
    proxyFactory.addAdvice(new TimeAdvice());
    ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();
    log.info("targetClass={}", target.getClass());
    log.info("proxyClass={}", proxy.getClass());

    proxy.save();

    assertThat(AopUtils.isAopProxy(proxy)).isTrue();
    assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse(); // JsetProxyTargetClass(true) 설정으로 False
    assertThat(AopUtils.isCglibProxy(proxy)).isTrue(); // setProxyTargetClass(true) 설정으로 True
  }

}
