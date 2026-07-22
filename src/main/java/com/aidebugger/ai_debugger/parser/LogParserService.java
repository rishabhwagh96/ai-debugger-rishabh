package com.aidebugger.ai_debugger.parser;
import com.aidebugger.ai_debugger.dto.ParsedStackTrace;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LogParserService {

    private static final Pattern STACK_TRACE_PATTERN =
            Pattern.compile("at\\s+([\\w\\.]+)\\.([\\w]+)\\(([^:]+):(\\d+)\\)");

    public ParsedStackTrace parse(String stackTrace) {

        String exceptionType = extractExceptionType(stackTrace);

        Matcher matcher = STACK_TRACE_PATTERN.matcher(stackTrace);

        if (matcher.find()) {

            String fullClassName = matcher.group(1);
            String methodName = matcher.group(2);
            String fileName = matcher.group(3);
            Integer lineNumber = Integer.parseInt(matcher.group(4));

            String className = extractClassName(fullClassName);

            return new ParsedStackTrace(
                    exceptionType,
                    className,
                    methodName,
                    lineNumber
            );
        }

        return new ParsedStackTrace(
                exceptionType,
                "Unknown",
                "Unknown",
                -1
        );
    }

    private String extractExceptionType(String stackTrace) {

        String firstLine = stackTrace.split("\n")[0];

        if (firstLine.contains(".")) {
            return firstLine.substring(
                    firstLine.lastIndexOf('.') + 1
            );
        }

        return firstLine;
    }

    private String extractClassName(String fullClassName) {

        if (fullClassName.contains(".")) {
            return fullClassName.substring(
                    fullClassName.lastIndexOf('.') + 1
            );
        }

        return fullClassName;
    }
}