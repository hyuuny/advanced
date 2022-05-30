package com.hyuuny.advanced.member;

import com.hyuuny.advanced.member.annotation.ClassAop;
import com.hyuuny.advanced.member.annotation.MethodAop;
import org.springframework.stereotype.Component;

@ClassAop
@Component
public class MemberServiceImpl implements MemberService{

  @MethodAop("test_value")
  @Override
  public String hello(String param) {
    return "ok";
  }

  public String internal(String param) {
    return "ok";
  }

}
