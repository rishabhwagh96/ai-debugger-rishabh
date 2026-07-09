package com.aidebugger.ai_debugger.service;

import com.aidebugger.ai_debugger.dto.AnalyzeResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OllamaLlmService implements LlmService {

    @Override
    public AnalyzeResponse analyzeStackTrace(String stackTrace) {

        return new AnalyzeResponse(
                "Dummy Root Cause",
                "Dummy AI Explanation",
                List.of("Fix 1", "Fix 2"),
                0.80
        );
    }
}