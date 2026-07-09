package com.aidebugger.ai_debugger;

import com.aidebugger.ai_debugger.config.OllamaProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(
		exclude = {DataSourceAutoConfiguration.class}
)
@EnableConfigurationProperties(OllamaProperties.class)
public class AiDebuggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiDebuggerApplication.class, args);
	}
}