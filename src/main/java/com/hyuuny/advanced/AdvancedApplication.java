package com.hyuuny.advanced;

import com.hyuuny.advanced.config.v2_dynamicproxy.DynamicProxyFilterConfig;
import com.hyuuny.advanced.trace.logtrace.LogTrace;
import com.hyuuny.advanced.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

//@Import({AppV1Config.class, AppV2Config.class})
//@Import({InterfaceProxyConfig.class})
//@Import({ConcreteProxyConfig.class})
//@Import({DynamicProxyBasicConfig.class})
@Import({DynamicProxyFilterConfig.class})
@SpringBootApplication(scanBasePackages = "com.hyuuny.advanced.app")
public class AdvancedApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedApplication.class, args);
	}

	@Bean
	public LogTrace logTrace() {
		return new ThreadLocalLogTrace();
	}

}
