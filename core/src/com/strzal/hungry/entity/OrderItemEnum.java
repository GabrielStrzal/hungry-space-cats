package com.strzal.hungry.entity;

import com.strzal.hungry.constants.ImagesPaths;
import lombok.Getter;

public enum OrderItemEnum {

    WATER(ImagesPaths.WATTER),
    CHIPS_BOOL(ImagesPaths.BOOL_CHIPS),
    FISH_BOOL(ImagesPaths.BOOL_FISH);

    @Getter
    private final String imagePath;

    private OrderItemEnum(String  imagePath) {
        this.imagePath = imagePath;
    }
}
