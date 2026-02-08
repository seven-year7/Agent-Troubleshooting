package com.catchat.agent.demo.stdio;

import com.catchat.agent.applicationlog.ApplicationLogMcpServer;
import com.catchat.agent.applicationlog.ApplicationLogMcpServerImpl;
import io.modelcontextprotocol.json.McpJsonMapper;
import io.modelcontextprotocol.server.transport.StdioServerTransportProvider;
import io.modelcontextprotocol.spec.McpServerTransportProvider;

import java.util.Map;

/**
 * STDIO 传输的 Application-Log-MCP-Server Demo。
 * 通过 stdin/stdout 与 MCP 客户端通信，发送结构化日志。
 * 运行：mvn -pl demo-stdio exec:java -Dexec.mainClass=com.catchat.agent.demo.stdio.StdioApplicationLogDemo
 * 或作为子进程被 MCP 客户端启动，通过 stdin/stdout 交换 JSON-RPC。
 */
public class StdioApplicationLogDemo {

    public static void main(String[] args) throws Exception {
        McpJsonMapper jsonMapper = McpJsonMapper.getDefault();
        McpServerTransportProvider transport = new StdioServerTransportProvider(jsonMapper);
        ApplicationLogMcpServer server = new ApplicationLogMcpServerImpl(transport);

        // 发送示例日志（客户端连接后可通过 notifications/message 收到）
        server.info("demo-stdio", "Application-Log-MCP-Server (STDIO) demo started");
        server.debug("demo-stdio", Map.of("transport", "stdio", "version", "1.0"));
        server.info("app", "Sample info: Hello from Java MCP logging");
        server.warning("app", Map.of("message", "This is a warning", "code", 42));
        server.error("db", Map.of("error", "Connection failed", "host", "localhost", "port", 5432));

        // 保持 JVM 运行（STDIO 模式下 transport 在后台线程读取 stdin）
        Runtime.getRuntime().addShutdownHook(new Thread(server::close));
        Thread.sleep(Long.MAX_VALUE);
    }
}
