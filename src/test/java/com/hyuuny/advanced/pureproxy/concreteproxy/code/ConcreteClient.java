package com.hyuuny.advanced.pureproxy.concreteproxy.code;

import com.hyuuny.advanced.pureproxy.concreteproxy.ConcreteLogic;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConcreteClient {

  private ConcreteLogic concreteLogic;

  public void execute() {
    concreteLogic.operation();
  }

}
