package com.jeleniasty.structuretask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure {

    private final List<Block> blocks = new ArrayList<>();

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return blocks.stream().filter(block ->
            Arrays.asList(block.getColor().split(" ")).contains(color)
        ).findFirst();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return blocks.stream().filter(block -> Arrays.asList(block.getMaterial().split(" ")).contains(material)).toList();
    }

    @Override
    public int count() {
        return blocks.size();
    }

    public void addBlock(Block block) {
        blocks.add(block);
    }
}
