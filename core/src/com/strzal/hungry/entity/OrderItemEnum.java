package com.strzal.hungry.entity;

import com.strzal.hungry.constants.ImagesPaths;
import lombok.Getter;

public enum OrderItemEnum {

    WATER(ImagesPaths.HUNGRY_WATTER),
    CHIPS_BOOL(ImagesPaths.HUNGRY_BOOL_CHIPS),
    FISH_BOOL(ImagesPaths.HUNGRY_BOOL_FISH);

    @Getter
    private final String imagePath;

    private OrderItemEnum(String  imagePath) {
        this.imagePath = imagePath;
    }
}
