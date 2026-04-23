package com.hxl.video.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 媒体服务配置属性
 *
 * @author liangweidong
 */
@ConfigurationProperties(prefix = "media")
public record MediaProperties(
    Server server,
    int threadNum,
    int logLevel
) {
    public record Server(
        int httpPort,
        int rtspPort,
        int rtmpPort,
        String id
    ) {}
}
