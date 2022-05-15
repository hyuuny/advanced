package com.hyuuny.advanced.config.v1_proxy.interface_proxy;

import com.hyuuny.advanced.app.v1.OrderRepositoryV1;
import com.hyuuny.advanced.trace.TraceStatus;
import com.hyuuny.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepositoryInterfaceProxy implements OrderRepositoryV1 {

  private final OrderRepositoryV1 target;
  private final LogTrace logTrace;

  @Override
  public void save(String itemId) {
    TraceStatus status = null;
    try {
      status = logTrace.begin("OrderRepository.request()");
      // target 호출
      target.save(itemId);
      logTrace.end(status);
    } catch (Exception e) {
      logTrace.exception(status, e);
      throw e;
    }

  }
}
