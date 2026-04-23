package com.hxl.video.launch;

import com.aizuda.zlm4j.structure.MK_CONFIG;
import com.hxl.video.config.GlobalConfigProperties;
import com.hxl.video.media.server.MediaServerManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * ZLM 媒体服务器启动器
 *
 * @author liangweidong
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ZlmServerLauncher implements ApplicationRunner {

    private final MediaServerManager mediaServerManager;
    private final GlobalConfigProperties globalConfigProperties;
    private final MK_CONFIG mkConfig;

    @Override
    public void run(ApplicationArguments args) {
        log.info("ZLM 媒体服务器初始化开始, serverId={}", globalConfigProperties.getMediaProperties().server().id());

        mediaServerManager.initEnvironment(mkConfig);
        mediaServerManager.setServerId(globalConfigProperties.getMediaProperties().server().id());
        mediaServerManager.startHttpServer((short) globalConfigProperties.getMediaProperties().server().httpPort());
        mediaServerManager.startRtspServer((short) globalConfigProperties.getMediaProperties().server().rtspPort());
        mediaServerManager.startRtmpServer((short) globalConfigProperties.getMediaProperties().server().rtmpPort());

        log.info("ZLM 媒体服务器初始化完成");
    }
}
