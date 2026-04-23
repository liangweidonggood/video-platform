package com.hxl.video.config;

import lombok.Getter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 全局配置属性持有者
 * 统一管理所有配置属性，提供全局访问入口
 *
 * @author liangweidong
 */
@Component
@EnableConfigurationProperties(MediaProperties.class)
@Getter
public class GlobalConfigProperties {

    private final MediaProperties mediaProperties;

    public GlobalConfigProperties(MediaProperties mediaProperties) {
        this.mediaProperties = mediaProperties;
    }
}
