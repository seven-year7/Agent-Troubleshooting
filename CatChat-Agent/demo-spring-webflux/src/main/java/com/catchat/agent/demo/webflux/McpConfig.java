package com.catchat.agent.demo.webflux;

import com.catchat.agent.applicationlog.ApplicationLogMcpServer;
import com.catchat.agent.applicationlog.ApplicationLogMcpServerImpl;
import io.modelcontextprotocol.json.McpJsonMapper;
import io.modelcontextprotocol.server.transport.WebFluxStreamableServerTransportProvider;
import io.modelcontextprotocol.spec.McpStreamableServerTransportProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * 配置 Application-Log-MCP-Server（Spring WebFlux SSE 传输）。
 * MCP 端点：GET/POST/DELETE /mcp
 */
@Configuration
public class McpConfig {

    @Bean
    public WebFluxStreamableServerTransportProvider webFluxStreamableServerTransportProvider() {
        return WebFluxStreamableServerTransportProvider.builder()
                .jsonMapper(McpJsonMapper.getDefault())
                .messageEndpoint("/mcp")
                .build();
    }

    @Bean
    public ApplicationLogMcpServer applicationLogMcpServer(
            WebFluxStreamableServerTransportProvider transportProvider) {
        return new ApplicationLogMcpServerImpl(transportProvider);
    }

    @Bean
    public RouterFunction<ServerResponse> mcpRouterFunction(
            WebFluxStreamableServerTransportProvider transportProvider) {
        return transportProvider.getRouterFunction();
    }
}
