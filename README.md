# Agent-TroubleShooting: 基于 MCP 与 LangChain4j 的自动化日志分析智能体

## 1. 项目背景与目标

本项目旨在解决当前核心服务在 Oncall 过程中日志获取难、人工定位慢的问题。由于安全规范限制，禁止直接 SSH/SFTP 进入实例，本项目通过 **Log-Agent** 将日志标准化上报至 **ELK**，并利用 **MCP (Model Context Protocol)** 协议封装日志获取能力，最终通过 **LangChain4j** 驱动的 AI Agent 实现全自动的根因分析（RCA）。

## 2. 核心架构演进

### 第一阶段：封装 Application-Log-MCP-Server (中期方案)

我们将构建一个标准的 MCP Server，作为 AI 智能体与 ES 之间的桥梁。

* **输入参数**：`requestId`, `timestamp`, `serviceName`。
* **内部逻辑**：
1. **ES 抓取**：根据 `requestId` 从 Elasticsearch 中提取对应服务的 `application-log`。
2. **Workflow 解析**：内置解析流（Parser），提取日志中的异常堆栈、关键上下文信息。


* **标准协议**：遵循 MCP 规范定义 `Tools`，使任何兼容 MCP 的智能体（如本项目的 LangChain4j 智能体）都能直接调用。

### 第二阶段：落地微服务自动化 Oncall 

利用 **LangChain4j** 搭建的高级智能体实现以下闭环：

1. **问题分析**：智能体解析用户反馈的错误表现。
2. **日志抓取**：自动对比 **正常 requestId** 与 **错误 requestId** 的日志。
3. **Diff 分析**：识别日志执行路径的差异点。
4. **源码追溯**：通过 **GitLab MCP Server**（或 API）检索对应版本的逻辑代码。
5. **根因输出**：最终输出一份包含“日志表现-代码位置-修复建议”的分析报告。

---

## 3. 技术栈

* **核心框架**：[LangChain4j](https://github.com/langchain4j/langchain4j) (Java 生态 AI 编排)

* **协议层**：Model Context Protocol (MCP)

* **日志存储**：Elasticsearch (ELK Stack)

* **LLM**：(推荐使用 Claude 3.5 Sonnet 或 GPT-4o，因其对 MCP 和代码分析支持度最高)

  | Java                 | 17    | 核心开发语言 |
  | :------------------- | :---- | :----------- |
  | Spring Boot          | 3.5.6 | 应用框架     |
  | Spring AI MCP Server | 1.0.3 | MCP 协议实现 |

### 4.项目背景

本项目以 **Cat-Chat** 聊天系统为基准，利用其产生的业务日志，验证基于 **MCP (Model Context Protocol)** 协议的自动化 Oncall 流程。 目标是通过 Agent 实现：**“输入错误 RequestID -> 自动提取 ES 日志 -> 对比逻辑差异 -> 定位 GitLab 源码 -> 给出 RCA 报告”** 的闭环。

### 5.当前进度

开发中，敬请期待