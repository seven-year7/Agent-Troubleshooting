package com.catchat.agent.demo.webmvc;

import com.catchat.agent.applicationlog.ApplicationLogMcpServer;
import com.catchat.agent.applicationlog.ApplicationLogMcpServerImpl;
import io.modelcontextprotocol.json.McpJsonMapper;
import io.modelcontextprotocol.server.transport.WebMvcStreamableServerTransportProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

/**
 * 配置 Application-Log-MCP-Server（Spring WebMVC SSE 传输）。
 * MCP 端点：GET/POST/DELETE /mcp
 */
@Configuration
public class McpConfig {

    @Bean
    public WebMvcStreamableServerTransportProvider webMvcStreamableServerTransportProvider() {
        return WebMvcStreamableServerTransportProvider.builder()
                .jsonMapper(McpJsonMapper.getDefault())
                .mcpEndpoint("/mcp")
                .build();
    }

    @Bean
    public ApplicationLogMcpServer applicationLogMcpServer(
            WebMvcStreamableServerTransportProvider transportProvider) {
        return new ApplicationLogMcpServerImpl(transportProvider);
    }

    @Bean
    public RouterFunction<ServerResponse> mcpRouterFunction(
            WebMvcStreamableServerTransportProvider transportProvider) {
        return transportProvider.getRouterFunction();
    }
}
