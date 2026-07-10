package com.aidebugger.ai_debugger.controller;

import com.aidebugger.ai_debugger.client.OllamaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final OllamaClient ollamaClient;

    public TestController(OllamaClient ollamaClient) {
        this.ollamaClient = ollamaClient;
    }

    @GetMapping("/test-ollama")
    public String testOllama() {

        return ollamaClient.generate(
                "Explain NullPointerException in one sentence."
        );
    }
}