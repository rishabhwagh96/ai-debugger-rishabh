package com.aidebugger.ai_debugger.service;

import com.aidebugger.ai_debugger.client.OllamaClient;
import com.aidebugger.ai_debugger.dto.AnalyzeResponse;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.aidebugger.ai_debugger.dto.llm.LlmAnalysisResponse;

import java.util.List;

@Service
public class OllamaLlmService implements LlmService {

    private  final OllamaClient ollamaClient;
    private final ObjectMapper objectMapper;

    public OllamaLlmService(OllamaClient ollamaClient, ObjectMapper objectMapper) {
        this.ollamaClient = ollamaClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public AnalyzeResponse analyzeStackTrace(String stackTrace) {

        String prompt = """
            Analyze the following Java stack trace.

            Return ONLY valid JSON.

            {
              "rootCause":"",
              "explanation":"",
              "fixes":[],
              "confidence":0.0
            }

            Do not return markdown.
            Do not return explanations outside JSON.

            Stack Trace:
            %s
            """.formatted(stackTrace);

        String aiResponse = ollamaClient.generate(prompt);

        try {

            LlmAnalysisResponse llmResponse =
                    objectMapper.readValue(
                            aiResponse,
                            LlmAnalysisResponse.class
                    );

            return new AnalyzeResponse(
                    llmResponse.rootCause(),
                    llmResponse.explanation(),
                    llmResponse.fixes(),
                    llmResponse.confidence()
            );

        } catch (Exception e) {

            return new AnalyzeResponse(
                    "Parsing Error",
                    aiResponse,
                    List.of("Unable to parse AI response"),
                    0.0
            );
        }

    }
}