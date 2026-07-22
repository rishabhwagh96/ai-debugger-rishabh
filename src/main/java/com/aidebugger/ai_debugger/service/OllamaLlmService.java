package com.aidebugger.ai_debugger.service;

import com.aidebugger.ai_debugger.client.OllamaClient;
import com.aidebugger.ai_debugger.dto.AnalyzeResponse;
import com.aidebugger.ai_debugger.dto.ParsedStackTrace;
import com.aidebugger.ai_debugger.parser.LogParserService;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.aidebugger.ai_debugger.dto.llm.LlmAnalysisResponse;


import java.util.List;

@Service
public class OllamaLlmService implements LlmService {

    private  final OllamaClient ollamaClient;
    private final ObjectMapper objectMapper;
    private final LogParserService logParserService;

//    public OllamaLlmService(OllamaClient ollamaClient, ObjectMapper objectMapper) {
//        this.ollamaClient = ollamaClient;
//        this.objectMapper = objectMapper;
//    }

    public OllamaLlmService(
            OllamaClient ollamaClient,
            ObjectMapper objectMapper,
            LogParserService logParserService) {

        this.ollamaClient = ollamaClient;
        this.objectMapper = objectMapper;
        this.logParserService = logParserService;
    }

    @Override
    public AnalyzeResponse analyzeStackTrace(String stackTrace) {

//        String prompt = """
//            Analyze the following Java stack trace.
//
//            Return ONLY valid JSON.
//
//            {
//              "rootCause":"",
//              "explanation":"",
//              "fixes":[],
//              "confidence":0.0
//            }
//
//            Do not return markdown.
//            Do not return explanations outside JSON.
//
//            Stack Trace:
//            %s
//            """.formatted(stackTrace);

        ParsedStackTrace parsed =
                logParserService.parse(stackTrace);


        String prompt = """
Analyze this Java exception.

Exception Type: %s
Class: %s
Method: %s
Line Number: %d

Return ONLY valid JSON.

{
  "rootCause":"",
  "explanation":"",
  "fixes":[],
  "confidence":0.0
}
""".formatted(
                parsed.exceptionType(),
                parsed.className(),
                parsed.methodName(),
                parsed.lineNumber()
        );

        String aiResponse = ollamaClient.generate(prompt);

        System.out.println("===== OLLAMA RESPONSE =====");
        System.out.println(aiResponse);
        System.out.println("===========================");
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