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
        config.thread_num = globalConfigProperties.getMediaProperties().threadNum();
        config.log_level = globalConfigProperties.getMediaProperties().logLevel();
        config.log_mask = 3;
        config.log_file_path = null;
        config.log_file_days = 3;
        config.ini_is_path = 0;
        config.ini = null;
        config.ssl_is_path = 0;
        config.ssl = null;
        config.ssl_pwd = null;
        return config;
    }
}
