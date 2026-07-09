package com.aidebugger.ai_debugger.service;
import com.aidebugger.ai_debugger.dto.AnalyzeRequest;
import com.aidebugger.ai_debugger.dto.AnalyzeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DebugAnalysisService {

    private final LlmService llmService;

    public AnalyzeResponse analyze(
            AnalyzeRequest request) {

        return llmService.analyzeStackTrace(
                request.stackTrace()
        );
    }
}
