package com.gabriel.data.video.mapper

import com.gabriel.data.util.base.DataMapper
import com.gabriel.data.video.model.VideoData
import com.gabriel.domain.video.model.VideoDomain

class VideoDataMapper : DataMapper<VideoData, VideoDomain> {
    override fun mapToDomain(type: VideoData): VideoDomain {
        return VideoDomain(
            id = type.id,
            key = type.key,
            type = type.type,
            official = type.official
        )
    }

    override fun mapToData(type: VideoDomain): VideoData {
        return VideoData(
            id = type.id,
            key = type.key,
            type = type.type,
            official = type.official
        )
    }
}
