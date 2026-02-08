package com.catchat.agent.applicationlog;

import io.modelcontextprotocol.server.McpServer;
import io.modelcontextprotocol.server.McpSyncServer;
import io.modelcontextprotocol.spec.McpSchema;
import io.modelcontextprotocol.spec.McpServerTransportProvider;
import io.modelcontextprotocol.spec.McpStreamableServerTransportProvider;

/**
 * Application-Log-MCP-Server 封装实现：包装 McpSyncServer，仅开启 logging capability，
 * 提供 log(level, logger, data) 等简便 API。
 */
public final class ApplicationLogMcpServerImpl implements ApplicationLogMcpServer {

    private final McpSyncServer syncServer;

    /** 单会话传输（如 STDIO）. */
    public ApplicationLogMcpServerImpl(McpServerTransportProvider transportProvider) {
        this.syncServer = McpServer.sync(transportProvider)
                .serverInfo("application-log-mcp-server", "1.0.0")
                .capabilities(McpSchema.ServerCapabilities.builder()
                        .logging()
                        .build())
                .build();
    }

    /** 可流式传输（如 Spring WebMVC/WebFlux SSE）. */
    public ApplicationLogMcpServerImpl(McpStreamableServerTransportProvider transportProvider) {
        this.syncServer = McpServer.sync(transportProvider)
                .serverInfo("application-log-mcp-server", "1.0.0")
                .capabilities(McpSchema.ServerCapabilities.builder()
                        .logging()
                        .build())
                .build();
    }

    @Override
    public void log(McpSchema.LoggingLevel level, String logger, Object data) {
        McpSchema.LoggingMessageNotification notification = McpSchema.LoggingMessageNotification.builder()
                .level(level)
                .logger(logger != null ? logger : "app")
                .data(data)
                .build();
        syncServer.loggingNotification(notification);
    }

    @Override
    public void close() {
        syncServer.closeGracefully();
    }

    public McpSyncServer getSyncServer() {
        return syncServer;
    }
}
