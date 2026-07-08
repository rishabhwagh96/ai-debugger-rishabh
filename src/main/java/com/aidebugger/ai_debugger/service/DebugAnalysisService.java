package com.aidebugger.ai_debugger.service;
import com.aidebugger.ai_debugger.dto.AnalyzeRequest;
import com.aidebugger.ai_debugger.dto.AnalyzeResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DebugAnalysisService {

    public AnalyzeResponse analyze(
            AnalyzeRequest request) {

        return new AnalyzeResponse(
                "Dummy Root Cause",
                "Dummy Analysis",
                List.of("Fix 1", "Fix 2","Fix 3"),
                0.90
        );
    }
}
