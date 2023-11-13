package com.jeleniasty.structuretask;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WallTest {
    private Block redBrick;
    private Block grayBrick;
    private Block steelRebar;
    private Block concrete;
    private Block rustySteelRebar;
    private Block titan;
    private Block aluminum;

    private CompositeBlock reinforcedConcrete;
    private CompositeBlock oldReinforcedConcrete;

    private CompositeBlock superReinforcedConcrete;

    private Wall brickWall;
    private Wall concreteWall;

    @BeforeEach
    void setUp() {
        this.redBrick = new BlockTestImpl("red", "brick");
        this.grayBrick = new BlockTestImpl("gray", "brick");

        this.steelRebar = new BlockTestImpl("silver", "steel");
        this.concrete = new BlockTestImpl("gray", "concrete");
        this.reinforcedConcrete = new CompositeBlockImpl(List.of(steelRebar, concrete));

        this.rustySteelRebar = new BlockTestImpl("rusty", "steel");
        this.oldReinforcedConcrete = new CompositeBlockImpl(List.of(rustySteelRebar, concrete));

        this.titan = new BlockTestImpl("silver", "titanium");
        this.aluminum = new BlockTestImpl("gray", "concrete");
        this.superReinforcedConcrete = new CompositeBlockImpl(List.of(titan, aluminum));

        this.brickWall = new Wall();
        this.brickWall.addBlock(redBrick);
        this.brickWall.addBlock(grayBrick);

        this.concreteWall = new Wall();
        concreteWall.addBlock(reinforcedConcrete);
        concreteWall.addBlock(oldReinforcedConcrete);
        concreteWall.addBlock(superReinforcedConcrete);


    }

    @Test
    void findBlockByColorBlockTest() {
        assertEquals(brickWall.findBlockByColor("red").orElse(null), redBrick);
        assertEquals(brickWall.findBlockByColor("gray").orElse(null), grayBrick);
        assertNotEquals(brickWall.findBlockByColor("gray").orElse(null), redBrick);
        assertNull(brickWall.findBlockByColor("blue").orElse(null));
    }

    @Test
    void findBlockByColorCompositeBlockTest() {
        assertEquals(concreteWall.findBlockByColor("silver").orElse(null), reinforcedConcrete);
        assertEquals(concreteWall.findBlockByColor("gray").orElse(null), reinforcedConcrete);
        assertEquals(concreteWall.findBlockByColor("rusty").orElse(null), oldReinforcedConcrete);

        assertNotEquals(concreteWall.findBlockByColor("gray").orElse(null), oldReinforcedConcrete);
        assertNotEquals(concreteWall.findBlockByColor("red").orElse(null), reinforcedConcrete);
        assertNotEquals(concreteWall.findBlockByColor("red").orElse(null), oldReinforcedConcrete);

        assertNull(concreteWall.findBlockByColor("blue").orElse(null));
    }

    @Test
    void findBlockByMaterialBlockTest() {
        assertEquals(brickWall.findBlocksByMaterial("brick"), List.of(redBrick, grayBrick));
        assertNotEquals(brickWall.findBlocksByMaterial("brick"), List.of(redBrick));
        assertNotEquals(brickWall.findBlocksByMaterial("brick"), List.of(grayBrick));
        assertEquals(brickWall.findBlocksByMaterial("LDPE"), Collections.emptyList());
    }

    @Test
    void findBlockByMaterialCompositeBlockTest() {
        assertEquals(concreteWall.findBlocksByMaterial("concrete"), List.of(reinforcedConcrete, oldReinforcedConcrete, superReinforcedConcrete));
        assertEquals(concreteWall.findBlocksByMaterial("steel"), List.of(reinforcedConcrete, oldReinforcedConcrete));
        assertEquals(concreteWall.findBlocksByMaterial("titanium"), List.of(superReinforcedConcrete));
        assertEquals(concreteWall.findBlocksByMaterial("LDPE"), Collections.emptyList());
    }

    @Test
    void countTest() {
        var wall = new Wall();
        assertEquals(wall.count(), 0);

        wall.addBlock(reinforcedConcrete);
        assertEquals(wall.count(), 1);

        wall.addBlock(oldReinforcedConcrete);
        assertEquals(wall.count(), 2);

        wall.addBlock(superReinforcedConcrete);

        assertEquals(wall.count(), 3);
    }
}