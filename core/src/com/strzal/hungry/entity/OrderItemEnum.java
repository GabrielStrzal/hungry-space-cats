package com.strzal.hungry.entity;

import com.strzal.hungry.constants.ImagesPaths;

public enum OrderItemEnum {

    WATER(ImagesPaths.HUNGRY_WATTER),
    CHIPS_BOOL(ImagesPaths.HUNGRY_BOOL_CHIPS),
    FISH_BOOL(ImagesPaths.HUNGRY_BOOL_FISH);

    private final String imagePath;

    public String getImagePath() {
        return imagePath;
    }

    private OrderItemEnum(String  imagePath) {
        this.imagePath = imagePath;
    }
}
