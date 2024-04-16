package com.example.hoteltvapptemplate.presenter.channels

import android.os.Handler
import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem.fromUri
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.decoder.ffmpeg.FfmpegAudioRenderer
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.Renderer
import androidx.media3.exoplayer.RenderersFactory
import androidx.media3.exoplayer.audio.AudioRendererEventListener
import androidx.media3.exoplayer.hls.DefaultHlsExtractorFactory
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.exoplayer.metadata.MetadataOutput
import androidx.media3.exoplayer.text.TextOutput
import androidx.media3.exoplayer.video.VideoRendererEventListener
import androidx.media3.extractor.ts.DefaultTsPayloadReaderFactory.FLAG_ALLOW_NON_IDR_KEYFRAMES
import androidx.media3.ui.PlayerView

@UnstableApi
class CustomRenderersFactory : RenderersFactory {
    @UnstableApi
    override fun createRenderers(
        eventHandler: Handler,
        videoRendererEventListener: VideoRendererEventListener,
        audioRendererEventListener: AudioRendererEventListener,
        textRendererOutput: TextOutput,
        metadataRendererOutput: MetadataOutput
    ): Array<Renderer> {
        return arrayOf(
           FfmpegAudioRenderer()
        )
    }
}

@OptIn(UnstableApi::class)
@Composable
fun WatchChannel(channelUrl: String) {
    val defaultHlsExtractorFactory = DefaultHlsExtractorFactory(FLAG_ALLOW_NON_IDR_KEYFRAMES, true)

    val mediaSource = HlsMediaSource.Factory(
        DefaultHttpDataSource.Factory()
    ).setExtractorFactory(defaultHlsExtractorFactory)
        .createMediaSource(fromUri(channelUrl))

    val exoPlayer = ExoPlayer.Builder(LocalContext.current)
        .build()
    exoPlayer.setMediaSource(mediaSource)

    exoPlayer.prepare()
    exoPlayer.play()

    AndroidView(
        factory = {context ->
            PlayerView(context).apply {
                player = exoPlayer
            }
        },
    )
}