# video-platform

视频监控综合平台，支持 GB28281、海康 SDK、RTSP 等多协议摄像头接入，内置 ZLM 媒体服务器。

## 技术栈

### 后端
- Java 21
- Gradle 9.4.1
- Spring Boot 4
- ZLM4J 1.9.1

### 前端
- React 19
- Ant Design 6
- Vite 8
- UnoCSS
- pnpm / Node 22

## 项目结构

```
video-platform/
├── backend/          # Spring Boot 后端
├── frontend/         # React 前端
```

## 快速开始

### 后端

```bash
cd backend
./gradlew build
./gradlew bootRun
```

### 前端

```bash
cd frontend
pnpm install
pnpm dev
```

## 环境要求

- JDK 21+
- Node 22+
- pnpm 10+
