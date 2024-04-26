package com.example.hoteltvapptemplate.presenter.channels

import android.content.Context
import android.os.Handler
import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.Renderer
import androidx.media3.exoplayer.RenderersFactory
import androidx.media3.exoplayer.audio.AudioRendererEventListener
import androidx.media3.exoplayer.mediacodec.MediaCodecSelector
import androidx.media3.exoplayer.metadata.MetadataOutput
import androidx.media3.exoplayer.text.TextOutput
import androidx.media3.exoplayer.video.MediaCodecVideoRenderer
import androidx.media3.exoplayer.video.VideoRendererEventListener


@UnstableApi
class CustomRenderersFactory(val context: Context) : RenderersFactory {
    @UnstableApi
    override fun createRenderers(
        eventHandler: Handler,
        videoRendererEventListener: VideoRendererEventListener,
        audioRendererEventListener: AudioRendererEventListener,
        textRendererOutput: TextOutput,
        metadataRendererOutput: MetadataOutput
    ): Array<Renderer> {
        return arrayOf(
            MediaCodecVideoRenderer(
                context,
                MediaCodecSelector.DEFAULT
            ),
            //FfmpegAudioRenderer()
        )
    }
}

@OptIn(UnstableApi::class)
@Composable
fun WatchChannel(channelUrl: String) {
    val exoPlayer = ExoPlayer.Builder(LocalContext.current)
        .setRenderersFactory(CustomRenderersFactory(LocalContext.current))
        .build()
}