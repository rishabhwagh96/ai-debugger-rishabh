package com.aidebugger.ai_debugger.dto;

import java.util.List;

public record AnalyzeResponse(
        String rootCause,
        String explanation,
        List<String> fixes,
        Double confidence
) {
}