package com.aidebugger.ai_debugger.dto.llm;

import java.util.List;

public record LlmAnalysisResponse(
        String rootCause,
        String explanation,
        List<String> fixes,
        Double confidence
) {
}