package com.gabriel.remote.video.mapper.serie

import com.gabriel.data.video.model.VideoData
import com.gabriel.remote.util.base.RemoteMapper
import com.gabriel.remote.video.model.serie.SerieVideoResponse

class VideoSerieRemoteMapper : RemoteMapper<SerieVideoResponse, VideoData> {
    override fun mapToData(type: SerieVideoResponse): VideoData {
        return VideoData(
            id = type.id,
            key = type.key,
            type = type.type,
            official = type.official
        )
    }

    override fun mapToRemote(type: VideoData): SerieVideoResponse {
        return SerieVideoResponse(
            id = type.id,
            key = type.key,
            type = type.type,
            official = type.official
        )
    }
}
