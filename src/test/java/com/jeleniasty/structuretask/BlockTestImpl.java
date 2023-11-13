package com.jeleniasty.structuretask;

public class BlockTestImpl implements Block {

    private final String color;
    private final String material;

    public BlockTestImpl(String color, String material) {
        this.color = color;
        this.material = material;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public String getMaterial() {
        return this.material;
    }
}
