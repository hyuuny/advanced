package com.hyuuny.advanced.pureproxy.proxy;

import com.hyuuny.advanced.pureproxy.proxy.code.CacheProxy;
import com.hyuuny.advanced.pureproxy.proxy.code.ProxyPatternClient;
import com.hyuuny.advanced.pureproxy.proxy.code.RealSubject;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {

  @Test
  void noProxyTest() {
    RealSubject realSubject = new RealSubject();
    ProxyPatternClient client = new ProxyPatternClient(realSubject);
    client.execute();
    client.execute();
    client.execute();
  }

  @Test
  void cacheProxyTest() {
    // 첫번째 execute 실행 후, cacheProxy가 중간에서 캐시하므로 2, 3번째 execute는 바로 호출된다.
    RealSubject realSubject = new RealSubject();
    CacheProxy cacheProxy = new CacheProxy(realSubject);
    ProxyPatternClient client = new ProxyPatternClient(cacheProxy);
    client.execute();
    client.execute();
    client.execute();
  }

}
