package com.catchat.agent.demo.webflux;

import com.catchat.agent.applicationlog.ApplicationLogMcpServer;
import io.modelcontextprotocol.spec.McpSchema;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * Demo：应用就绪后发送几条 MCP 日志；并提供一个 HTTP 接口用于手动触发日志。
 */
@Component
public class LogDemoHandler {

    private final ApplicationLogMcpServer applicationLogMcpServer;

    public LogDemoHandler(ApplicationLogMcpServer applicationLogMcpServer) {
        this.applicationLogMcpServer = applicationLogMcpServer;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {
        applicationLogMcpServer.info("demo-webflux", "Application-Log-MCP-Server (Spring WebFlux) demo started");
        applicationLogMcpServer.info("app", Map.of("message", "Hello from Spring WebFlux", "transport", "sse"));
        applicationLogMcpServer.warning("app", Map.of("code", 200, "hint", "This is a demo warning"));
    }

    public Mono<ServerResponse> emitLog(ServerRequest request) {
        return request.bodyToMono(Map.class)
                .defaultIfEmpty(Map.of())
                .flatMap(body -> {
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
                    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue("ok");
                });
    }
}
