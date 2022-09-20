package com.gabriel.remote.video.mapper.filme

import com.gabriel.data.video.model.VideoData
import com.gabriel.remote.util.base.RemoteMapper
import com.gabriel.remote.video.model.filme.FilmeVideoResponse

class VideoFilmeRemoteMapper : RemoteMapper<FilmeVideoResponse, VideoData> {
    override fun mapToData(type: FilmeVideoResponse): VideoData {
        return VideoData(
            id = type.id,
            key = type.key,
            type = type.type,
            official = type.official
        )
    }

    override fun mapToRemote(type: VideoData): FilmeVideoResponse {
        return FilmeVideoResponse(
            id = type.id,
            key = type.key,
            type = type.type,
            official = type.official
        )
    }
}
