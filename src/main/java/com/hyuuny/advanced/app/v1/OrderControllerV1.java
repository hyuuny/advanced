package com.hyuuny.advanced.app.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@RequestMapping // 스프링 MVC는 @Controller 또는 @RequestMapping이 있어야 스프링 컨트롤러로 인식
public interface OrderControllerV1 {

  @GetMapping("/v1/request")
  String request(@RequestParam("itemId") String itemId);

  @GetMapping("/v1/no-log")
  String noLog();

}
