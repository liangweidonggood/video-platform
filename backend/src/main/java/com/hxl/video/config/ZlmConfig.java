package com.hxl.video.config;

import com.aizuda.zlm4j.structure.MK_CONFIG;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ZLM 媒体服务器配置
 *
 * @author liangweidong
 */
@Configuration
@RequiredArgsConstructor
public class ZlmConfig {

    private final GlobalConfigProperties globalConfigProperties;

    @Bean
    public MK_CONFIG mkConfig() {
        MK_CONFIG config = new MK_CONFIG();
        // 日志配置
        config.log_mask = 3;
        // 日志掩码：3=INFO+WARN+ERROR，掩码含义参考 ZLM 文档
        config.log_file_path = null;
        // 日志文件路径，null=仅输出到控制台，不写文件
        config.log_file_days = 3;
        // 日志保留天数（仅 log_file_path 非 null 时生效）
        config.log_level = globalConfigProperties.getMediaProperties().logLevel();
        // 日志级别：0-6
        // 线程配置
        config.thread_num = globalConfigProperties.getMediaProperties().threadNum();
        // 工作线程数
        config.ini_is_path = 0;
        // 0=ini 为字符串内容，1=ini 为文件路径
        config.ini = null;
        // INI 配置内容/路径，null=使用 ZLM 内置默认配置
        // SSL 配置（预留，暂不使用）
        config.ssl_is_path = 0;
        // 0=ssl 为字符串内容，1=ssl 为文件路径
        config.ssl = null;
        // SSL 证书内容/路径，null=不启用 SSL
        config.ssl_pwd = null;
        // SSL 密码，null=无密码
        return config;
    }
}
