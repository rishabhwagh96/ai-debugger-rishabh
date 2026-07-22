package com.aidebugger.ai_debugger.dto;

public record ParsedStackTrace(
        String exceptionType,
        String className,
        String methodName,
        Integer lineNumber
) {
}