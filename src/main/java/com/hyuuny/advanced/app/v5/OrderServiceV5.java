package com.hyuuny.advanced.app.v5;

import com.hyuuny.advanced.trace.callback.TraceTemplate;
import com.hyuuny.advanced.trace.logtrace.LogTrace;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV5 {

  private final OrderRepositoryV5 orderRepository;
  private final TraceTemplate template;

  public OrderServiceV5(OrderRepositoryV5 orderRepository, LogTrace trace) {
    this.orderRepository = orderRepository;
    this.template = new TraceTemplate(trace);
  }

  public void orderItem(String itemId) {
    template.execute("OrderServiceV1.orderItem()", () -> {
      orderRepository.save(itemId);
      return null;
    });
  }

}