package com.jeleniasty.structuretask;

import java.util.List;
import java.util.stream.Collectors;

public class CompositeBlockImpl implements CompositeBlock{

    public CompositeBlockImpl(List<Block> blocks) {
        this.blocks = blocks;
    }

    private List<Block> blocks;
    @Override
    public String getColor() {
        return this.blocks.stream().map(Block::getColor).distinct().collect(Collectors.joining(" "));
    }

    @Override
    public String getMaterial() {
        return this.blocks.stream().map(Block::getMaterial).distinct().collect(Collectors.joining(" "));
    }

    @Override
    public List<Block> getBlocks() {
        return this.blocks;
    }
}
