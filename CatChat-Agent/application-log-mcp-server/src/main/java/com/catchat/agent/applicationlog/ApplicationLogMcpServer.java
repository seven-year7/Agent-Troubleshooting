package com.catchat.agent.applicationlog;

import io.modelcontextprotocol.spec.McpSchema;
import io.modelcontextprotocol.spec.McpSchema.LoggingMessageNotification;

/**
 * 封装的 Application-Log-MCP-Server（中期方案）门面。
 * 基于 MCP 规范提供结构化日志能力：声明 logging capability，
 * 通过 notifications/message 向客户端发送 level、logger、data。
 * 客户端可通过 logging/setLevel 设置最低日志级别。
 */
public interface ApplicationLogMcpServer {

    /**
     * 发送一条日志到 MCP 客户端。
     *
     * @param level  RFC 5424 级别：debug, info, notice, warning, error, critical, alert, emergency
     * @param logger 可选 logger 名称，可为 null
     * @param data   消息或可序列化数据，可为 String 或 Map 等
     */
    void log(McpSchema.LoggingLevel level, String logger, Object data);

    /**
     * 便捷：INFO 级别日志
     */
    default void info(String logger, Object data) {
        log(McpSchema.LoggingLevel.INFO, logger, data);
    }

    /**
     * 便捷：ERROR 级别日志
     */
    default void error(String logger, Object data) {
        log(McpSchema.LoggingLevel.ERROR, logger, data);
    }

    /**
     * 便捷：WARNING 级别日志
     */
    default void warning(String logger, Object data) {
        log(McpSchema.LoggingLevel.WARNING, logger, data);
    }

    /**
     * 便捷：DEBUG 级别日志
     */
    default void debug(String logger, Object data) {
        log(McpSchema.LoggingLevel.DEBUG, logger, data);
    }

    /**
     * 关闭 server，释放资源。
     */
    void close();
}
