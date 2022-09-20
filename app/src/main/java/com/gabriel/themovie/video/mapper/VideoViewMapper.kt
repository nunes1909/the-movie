package com.gabriel.themovie.video.mapper

import com.gabriel.domain.video.model.VideoDomain
import com.gabriel.themovie.util.base.ViewMapper
import com.gabriel.themovie.video.model.VideoView

class VideoViewMapper : ViewMapper<VideoView, VideoDomain> {
    override fun mapToDomain(type: VideoView): VideoDomain {
        return VideoDomain(
            id = type.id,
            key = type.key,
            type = type.type,
            official = type.official
        )
    }

    override fun mapToView(type: VideoDomain): VideoView {
        return VideoView(
            id = type.id,
            key = type.key,
            type = type.type,
            official = type.official
        )
    }
}
