package com.socialmap.server.model.resource;

import com.socialmap.server.model.common.Audio;
import com.socialmap.server.model.common.Image;
import com.socialmap.server.model.common.Video;

import java.util.Set;

/**
 * Created by yy on 3/10/15.
 */
public abstract class TbsResource {
    private String name;
    private String description;
    private Set<Image> images;
    private Set<Video> videos;
    private Set<Audio> audios;
}
