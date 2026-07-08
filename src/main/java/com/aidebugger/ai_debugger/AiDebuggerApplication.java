package com.aidebugger.ai_debugger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(
		exclude = {DataSourceAutoConfiguration.class}
)
public class AiDebuggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiDebuggerApplication.class, args);
	}
}