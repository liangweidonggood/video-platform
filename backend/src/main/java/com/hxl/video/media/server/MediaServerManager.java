package com.hxl.video.media.server;

import com.aizuda.zlm4j.core.ZLMApi;
import com.aizuda.zlm4j.structure.MK_CONFIG;
import com.aizuda.zlm4j.structure.MK_INI;
import com.sun.jna.Native;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * ZLM 媒体服务器管理器
 * 封装 ZLM4J JNI 调用，管理媒体服务器生命周期
 *
 * @author liangweidong
 */
@Slf4j
@Component
public class MediaServerManager {

    private static volatile ZLMApi api;

    /**
     * 获取 ZLM API 实例（懒加载，单例）
     */
    public static ZLMApi getApi() {
        if (api == null) {
            synchronized (MediaServerManager.class) {
                if (api == null) {
                    api = Native.load("mk_api", ZLMApi.class);
                }
            }
        }
        return api;
    }

    /**
     * 初始化 ZLM 环境
     *
     * @param config ZLM 配置
     */
    public void initEnvironment(MK_CONFIG config) {
        api = getApi();
        api.mk_env_init(config);
        log.info("ZLM 环境初始化完成");
    }

    /**
     * 启动 HTTP 服务器
     *
     * @param port 端口
     * @return 是否启动成功
     */
    public boolean startHttpServer(short port) {
        short result = api.mk_http_server_start(port, 0);
        if (result == 0) {
            log.warn("ZLM HTTP 服务器启动失败, port={}", port);
            return false;
        }
        log.info("ZLM HTTP 服务器启动成功, port={}", result);
        return true;
    }

    /**
     * 启动 RTSP 服务器
     *
     * @param port 端口
     * @return 是否启动成功
     */
    public boolean startRtspServer(short port) {
        short result = api.mk_rtsp_server_start(port, 0);
        if (result == 0) {
            log.warn("ZLM RTSP 服务器启动失败, port={}", port);
            return false;
        }
        log.info("ZLM RTSP 服务器启动成功, port={}", result);
        return true;
    }

    /**
     * 启动 RTMP 服务器
     *
     * @param port 端口
     * @return 是否启动成功
     */
    public boolean startRtmpServer(short port) {
        short result = api.mk_rtmp_server_start(port, 0);
        if (result == 0) {
            log.warn("ZLM RTMP 服务器启动失败, port={}", port);
            return false;
        }
        log.info("ZLM RTMP 服务器启动成功, port={}", result);
        return true;
    }

    /**
     * 设置服务器 ID
     *
     * @param serverId 服务器标识
     */
    public void setServerId(String serverId) {
        MK_INI mkIni = api.mk_ini_default();
        api.mk_ini_set_option(mkIni, "general.mediaServerId", serverId);
        log.info("ZLM 服务器 ID 已设置: {}", serverId);
    }

    /**
     * 关闭所有服务器
     */
    @PreDestroy
    public void shutdown() {
        log.info("ZLM 媒体服务器关闭中...");
        if (api != null) {
            api.mk_stop_all_server();
        }
        log.info("ZLM 媒体服务器已关闭");
    }
}
