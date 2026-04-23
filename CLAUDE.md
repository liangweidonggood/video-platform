# CLAUDE.md

## 项目概述

视频监控综合平台，支持多协议摄像头接入（GB28281、海康 SDK、RTSP）和内置 ZLM 媒体服务器。

## 技术栈

| 端 | 技术 |
|----|------|
| 后端 | Java 21 / Spring Boot 4 / Gradle 9.4.1 / ZLM4J 1.9.1 |
| 前端 | React 19 / Ant Design 6 / Vite 8 / UnoCSS / pnpm |

## 目录结构

```
video-platform/
├── backend/               # Spring Boot 后端项目
│   ├── src/main/java/     # Java 源码
│   ├── src/main/resources/# 配置文件
│   ├── build.gradle.kts   # Gradle 构建配置
│   └── gradlew            # Gradle Wrapper
│
└── frontend/              # React 前端项目
    ├── src/               # React 源码
    ├── vite.config.ts     # Vite 配置
    ├── uno.config.ts      # UnoCSS 配置
    └── package.json       # pnpm 依赖配置
```

## 常用命令

### 后端
```bash
cd backend
./gradlew build        # 构建
./gradlew bootRun      # 启动
```

### 前端
```bash
cd frontend
pnpm install           # 安装依赖
pnpm dev               # 开发服务器
pnpm build             # 生产构建
```

## 约定

- 使用 Kotlin DSL 配置 Gradle（build.gradle.kts）
- 前端包管理器统一使用 pnpm
- 敏感配置通过 application-{profile}.yml 管理，不提交到 Git
