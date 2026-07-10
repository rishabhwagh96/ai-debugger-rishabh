package com.aidebugger.ai_debugger.dto.ollama;

public record OllamaRequest(
        String model,
        String prompt,
        boolean stream
) {
}