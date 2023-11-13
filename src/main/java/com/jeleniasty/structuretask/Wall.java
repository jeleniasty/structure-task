package com.jeleniasty.structuretask;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure {

    private final List<Block> blocks = new ArrayList<>();

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return this.blocks.stream().filter(block -> findBlockByColorRecursively(block, color)).findFirst();
    }

    private boolean findBlockByColorRecursively(Block block, String color) {
        if (block.getColor().equals(color)) {
            return true;
        }

        if (block instanceof CompositeBlock) {
            var compositeBlocks = ((CompositeBlock) block).getBlocks();
            return compositeBlocks.stream().anyMatch(innerBlock -> findBlockByColorRecursively(innerBlock, color));
        }
        return false;
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return this.blocks.stream().filter(block -> findBlocksByMaterialRecursively(block, material)).toList();
    }

    private boolean findBlocksByMaterialRecursively(Block block, String material) {
        if (block.getMaterial().equals(material)) {
            return true;
        }

        if (block instanceof CompositeBlock) {
            var compositeBlocks = ((CompositeBlock) block).getBlocks();
            return compositeBlocks.stream().anyMatch(innerBlock -> findBlocksByMaterialRecursively(innerBlock, material));
        }
        return false;
    }

    @Override
    public int count() {
        return blocks.size();
    }

    public void addBlock(Block block) {
        blocks.add(block);
    }
}
