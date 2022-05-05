package com.hyuuny.advanced.app.v2;

import com.hyuuny.advanced.trace.TraceId;
import com.hyuuny.advanced.trace.TraceStatus;
import com.hyuuny.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryV2 {

  private final HelloTraceV2 trace;

  public void save(TraceId traceId, String itemId) {
    TraceStatus status = null;
    try {
      status = trace.beginSync(traceId, "OrderRepository.save()");

      if (itemId.equals("ex")) {
        throw new IllegalStateException("예외 발생!");
      }
      sleep(1000);
      trace.end(status);
    } catch (Exception e) {
      trace.exception(status, e);
      throw e;
    }
  }

  private void sleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
