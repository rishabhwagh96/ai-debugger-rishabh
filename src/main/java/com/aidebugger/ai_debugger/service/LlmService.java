package com.aidebugger.ai_debugger.service;

import com.aidebugger.ai_debugger.dto.AnalyzeResponse;

public interface LlmService {

    AnalyzeResponse analyzeStackTrace(String stackTrace);

}