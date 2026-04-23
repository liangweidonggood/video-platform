# 后端包结构设计

## 1. 概述

视频监控综合平台后端包结构，按领域职责分层，支撑前端业务和未来开放接口。

## 2. 包结构

```
com.hxl.video/
├── config/                      # 配置类
├── launch/                      # 启动初始化（ApplicationRunner）
├── common/                      # 公共包（异常、DTO、工具）
│
├── module/                      # 业务模块（前端用）
│   ├── device/                  # 设备管理
│   │   ├── controller/
│   │   ├── service/
│   │   ├── model/
│   │   └── mapper/
│   └── user/                    # 用户管理
│       ├── controller/
│       ├── service/
│       ├── model/
│       └── mapper/
│
├── media/                       # 媒体服务（ZLM 封装）⭐ 单独顶级包
│   ├── server/                  # ZLM 服务封装
│   ├── stream/                  # 流管理
│   └── mapper/                  # 媒体统计/日志 Mapper
│
└── api/                          # 对外接口（第三方/开放平台）
    └── openapi/                  # 独立风格，不走 module 同一套
```

## 3. 各包职责

| 包 | 职责 | 说明 |
|----|------|------|
| `config/` | 配置类 | DataSource、ZLM、协议等配置 |
| `launch/` | 启动初始化 | ApplicationRunner 初始化 ZLM 和协议栈 |
| `common/` | 公共基础 | 统一异常、DTO、工具类 |
| `module/` | 业务模块 | 前端 BFF，完整 MVC 结构 |
| `media/` | 媒体能力层 | ZLM JNI 封装 + 流管理 + 日志 mapper |
| `api/openapi/` | 开放接口 | 第三方接入，独立于 module 体系 |

## 4. 设计原则

- `media/` 与 `module/` 平级，因其是基础设施能力层而非业务层
- `api/openapi/` 独立于 `module/`，因对外接口与前端 BFF 职责不同
- `module/` 下各模块保持一致的 controller/service/model/mapper 结构

## 5. 创建时间

2026-04-23