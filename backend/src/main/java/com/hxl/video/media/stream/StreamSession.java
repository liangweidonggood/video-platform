package com.hxl.video.media.stream;

import com.aizuda.zlm4j.core.ZLMApi;
import com.aizuda.zlm4j.structure.MK_PROXY_PLAYER;
import com.hxl.video.media.server.MediaServerManager;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 媒体流会话
 * 封装 ZLM 代理播放器，管理单条流的生命周期
 *
 * @author liangweidong
 */
@Slf4j
@Getter
public class StreamSession {

    private final String vhost;
    private final String app;
    private final String streamId;
    private final String sourceUrl;
    private final boolean hlsEnabled;
    private final boolean mp4Enabled;

    private MK_PROXY_PLAYER proxyPlayer;

    public StreamSession(String vhost, String app, String streamId, String sourceUrl,
                         boolean hlsEnabled, boolean mp4Enabled) {
        this.vhost = vhost;
        this.app = app;
        this.streamId = streamId;
        this.sourceUrl = sourceUrl;
        this.hlsEnabled = hlsEnabled;
        this.mp4Enabled = mp4Enabled;
    }

    /**
     * 开始播放（拉流）
     */
    public void play() {
        ZLMApi api = MediaServerManager.getApi();
        this.proxyPlayer = api.mk_proxy_player_create(
                vhost, app, streamId,
                hlsEnabled ? 1 : 0,
                mp4Enabled ? 1 : 0
        );
        api.mk_proxy_player_play(proxyPlayer, sourceUrl);
        log.info("流会话创建并开始播放: app={}, streamId={}, url={}",
                 app, streamId, sourceUrl);
    }

    /**
     * 停止播放
     */
    public void stop() {
        if (proxyPlayer != null) {
            ZLMApi api = MediaServerManager.getApi();
            api.mk_proxy_player_release(proxyPlayer);
            proxyPlayer = null;
            log.info("流会话已停止: app={}, streamId={}", app, streamId);
        }
    }

    /**
     * 获取当前观看人数
     */
    public int getReaderCount() {
        if (proxyPlayer != null) {
            return MediaServerManager.getApi().mk_proxy_player_total_reader_count(proxyPlayer);
        }
        return 0;
    }
}
