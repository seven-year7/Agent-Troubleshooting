# Application-Log-MCP-Server（中期方案）

基于 MCP 规范的**应用日志 MCP 服务**封装，提供：

- **Logging capability**：声明 `logging` 能力，客户端可接收结构化日志
- **notifications/message**：按 RFC 5424 级别（debug/info/notice/warning/error/critical/alert/emergency）发送日志
- **logging/setLevel**：客户端可通过 `logging/setLevel` 设置最低日志级别

## 使用

```java
McpServerTransportProvider transport = new StdioServerTransportProvider(McpJsonMapper.getDefault());
ApplicationLogMcpServer server = new ApplicationLogMcpServerImpl(transport);

server.info("app", "Server started");
server.error("db", Map.of("error", "Connection failed", "host", "localhost"));
server.close();
```

## 依赖

- `io.modelcontextprotocol.sdk:mcp`（BOM 0.17.2）

## 说明

- `LoggingMessageNotification` 若 SDK 无 `builder()`，请改用 `new LoggingMessageNotification(level, logger, data, null)`（具体以 SDK 为准）。
