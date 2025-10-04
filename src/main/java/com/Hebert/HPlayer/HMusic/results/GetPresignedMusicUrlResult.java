package com.Hebert.HPlayer.HMusic.results;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GetPresignedMusicUrlResult extends Result {
    private String presignedUrl;
}
