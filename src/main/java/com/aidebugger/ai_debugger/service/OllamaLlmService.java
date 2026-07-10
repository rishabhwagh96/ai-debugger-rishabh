package com.aidebugger.ai_debugger.service;

import com.aidebugger.ai_debugger.client.OllamaClient;
import com.aidebugger.ai_debugger.dto.AnalyzeResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OllamaLlmService implements LlmService {

    private  final OllamaClient ollamaClient;

    public OllamaLlmService(OllamaClient ollamaClient) {
        this.ollamaClient = ollamaClient;
    }

    @Override
    public AnalyzeResponse analyzeStackTrace(String stackTrace) {

        String prompt = """
            Analyze the following Java stack trace.

            Provide:
            1. Root cause
            2. Explanation
            3. Suggested fix

            Stack Trace:
            %s
            """.formatted(stackTrace);

        String aiResponse = ollamaClient.generate(prompt);

        return new AnalyzeResponse(
                "AI Generated",
                aiResponse,
                List.of("Review suggested fix"),
                0.90
        );
    }

}