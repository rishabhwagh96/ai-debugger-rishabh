package com.aidebugger.ai_debugger.controller;

import com.aidebugger.ai_debugger.dto.AnalyzeRequest;
import com.aidebugger.ai_debugger.dto.AnalyzeResponse;
import com.aidebugger.ai_debugger.service.DebugAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/debug")
@RequiredArgsConstructor
public class DebugController {

    private final DebugAnalysisService debugAnalysisService;

    @PostMapping("/analyze")
    public AnalyzeResponse analyze(
            @RequestBody AnalyzeRequest request) {

        return debugAnalysisService.analyze(request);
    }
}