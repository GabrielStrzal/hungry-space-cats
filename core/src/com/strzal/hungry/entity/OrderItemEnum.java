package com.strzal.hungry.entity;

import com.strzal.hungry.constants.ImagesPaths;
import lombok.Getter;

public enum OrderItemEnum {

    WATER(ImagesPaths.WATTER),
    SANDWISH(ImagesPaths.WATTER);

    @Getter
    private final String imagePath;

    private OrderItemEnum(String  imagePath) {
        this.imagePath = imagePath;
    }
}
