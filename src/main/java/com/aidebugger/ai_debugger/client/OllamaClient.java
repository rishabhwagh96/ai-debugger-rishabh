package com.aidebugger.ai_debugger.client;

import com.aidebugger.ai_debugger.config.OllamaProperties;
import com.aidebugger.ai_debugger.dto.ollama.OllamaRequest;
import com.aidebugger.ai_debugger.dto.ollama.OllamaResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class OllamaClient {

    private final RestClient restClient;
    private final OllamaProperties ollamaProperties;

    public OllamaClient(OllamaProperties ollamaProperties) {
        this.ollamaProperties = ollamaProperties;
        this.restClient = RestClient.builder()
                .baseUrl(ollamaProperties.baseUrl())
                .build();
    }

    public String generate(String prompt) {

        OllamaRequest request =
                new OllamaRequest(
                        ollamaProperties.model(),
                        prompt,
                        false
                );

        OllamaResponse response =
                restClient.post()
                        .uri("/api/generate")
                        .body(request)
                        .retrieve()
                        .body(OllamaResponse.class);

        return response != null
                ? response.response()
                : "No response from Ollama";
    }
}