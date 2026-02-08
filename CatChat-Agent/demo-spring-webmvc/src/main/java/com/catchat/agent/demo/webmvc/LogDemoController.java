package com.catchat.agent.demo.webmvc;

import com.catchat.agent.applicationlog.ApplicationLogMcpServer;
import io.modelcontextprotocol.spec.McpSchema;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Demo：应用就绪后发送几条 MCP 日志；并提供一个 HTTP 接口用于手动触发日志。
 */
@RestController
public class LogDemoController {

    private final ApplicationLogMcpServer applicationLogMcpServer;

    public LogDemoController(ApplicationLogMcpServer applicationLogMcpServer) {
        this.applicationLogMcpServer = applicationLogMcpServer;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {
        applicationLogMcpServer.info("demo-webmvc", "Application-Log-MCP-Server (Spring WebMVC) demo started");
        applicationLogMcpServer.info("app", Map.of("message", "Hello from Spring WebMVC", "transport", "sse"));
        applicationLogMcpServer.warning("app", Map.of("code", 100, "hint", "This is a demo warning"));
    }

    @PostMapping("/demo/log")
    public String emitLog(@RequestBody Map<String, Object> body) {
        String level = (String) body.getOrDefault("level", "info");
        String logger = (String) body.getOrDefault("logger", "http-demo");
        Object data = body.getOrDefault("data", body);

        McpSchema.LoggingLevel mcpLevel = switch (level.toLowerCase()) {
            case "debug" -> McpSchema.LoggingLevel.DEBUG;
            case "warning" -> McpSchema.LoggingLevel.WARNING;
            case "error" -> McpSchema.LoggingLevel.ERROR;
            default -> McpSchema.LoggingLevel.INFO;
        };
        applicationLogMcpServer.log(mcpLevel, logger, data);
        return "ok";
    }
}
