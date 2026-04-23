# CLAUDE.md

## 项目概述

视频监控综合平台，支持多协议摄像头接入（GB28281、海康 SDK、RTSP）和内置 ZLM 媒体服务器。

## 技术栈

| 端 | 技术 |
|----|------|
| 后端 | Java 21 / Spring Boot 4 / Gradle 9.4.1 / ZLM4J 1.9.1 |
| 前端 | React 19 / Ant Design 6 / Vite 8 / UnoCSS / pnpm |

## 环境要求

- JDK 21+
- Node 22+
- pnpm 10+
- Gradle 9.4.1（通过 wrapper 使用，无需全局安装）

## 目录结构

```
video-platform/
├── backend/                    # Spring Boot 后端项目
│   ├── src/main/java/com/hxl/video/
│   │   ├── config/             # 配置类（GlobalConfigProperties 等）
│   │   ├── launch/             # 启动初始化（ApplicationRunner）
│   │   ├── common/             # 公共包（异常、DTO、工具）
│   │   ├── module/             # 业务模块（device/user 等，含 controller/service/model/mapper）
│   │   ├── media/               # 媒体服务层（ZLM 封装，server/stream/mapper）
│   │   ├── api/                 # 对外接口（openapi/）
│   │   └── VideoPlatformApplication.java
│   └── src/main/resources/
│       ├── application.yml     # 公共配置
│       ├── application-dev.yml # 开发环境
│       ├── application-test.yml # 测试环境
│       └── application-prod.yml # 生产环境
│
└── frontend/                    # React 前端项目
    ├── src/
    ├── vite.config.ts
    └── uno.config.ts
```

## 常用命令

### 后端
```bash
cd backend
./gradlew build        # 构建
./gradlew bootRun      # 启动
./gradlew test         # 运行测试
```

### 前端
```bash
cd frontend
pnpm install           # 安装依赖
pnpm dev               # 开发服务器
pnpm build             # 生产构建
pnpm lint              # 代码检查
pnpm test              # 运行测试
```

## 模块说明

| 模块 | 用途 |
|------|------|
| `config/` | 配置类，GlobalConfigProperties 统一管理所有配置属性 |
| `launch/` | 启动初始化，ApplicationRunner 实现 |
| `common/` | 公共基础，异常、DTO、工具类 |
| `module/` | 业务模块，含 device/user 等，下分 controller/service/model/mapper |
| `media/` | ZLM 媒体服务封装，含 server/stream/mapper |
| `api/` | 对外开放接口，独立于 module 体系 |

## ZLM 媒体服务器

| 端口 | 用途 |
|------|------|
| 7788 | HTTP 媒体端口 |
| 554  | RTSP |
| 1935 | RTMP |

- 初始化链路：`ZlmServerLauncher`（ApplicationRunner）→ `MediaServerManager.initEnvironment()` → 各协议 `start*Server()`
- 配置通过 `GlobalConfigProperties.getMediaProperties()` 访问

## 配置管理规范

- 所有 `@ConfigurationProperties` 类通过 `GlobalConfigProperties` 统一入口访问
- `MediaProperties` 是 Java 21 record，accessor 不带 `get` 前缀（如 `mediaProperties.server().httpPort()`）
- YAML 配置按环境拆分：`application-dev.yml`、`application-test.yml`、`application-prod.yml`
- `server.port` 等环境相关配置放在各环境文件中，不在 `application.yml` 中

## 依赖说明

- ZLM4J（`com.aizuda:zlm4j:1.9.1`）是 JNI 封装，native libs（mk_api.dll 等）已打包在 jar 中
- 不需要单独安装 ZLM Server，嵌入式加载

## 约定

- 使用 Kotlin DSL 配置 Gradle（`build.gradle.kts`）
- 前端包管理器统一使用 pnpm
- 敏感配置通过 `application-{profile}.yml` 管理（本地配置如 `application-local.yml` 需提交）

## 初始化备忘

- **Git**：新仓库默认分支是 `master`，需手动 `git branch -m main`
- **Gradle Wrapper**：生成前先创建空的 `settings.gradle.kts`，再执行 `gradle wrapper --gradle-version X.X.X`
- **配置文件忽略**：本地配置（`application-local.yml`、`*.local`）应提交，不忽略

## 启动命令

- 后端：`./gradlew bootRun`（端口 8080）
- 前端：`pnpm dev`（端口 5173）

## 代码质量

- 提交前执行 `/code-simplifier` 分析最近修改的代码
