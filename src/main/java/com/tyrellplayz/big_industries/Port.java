package com.tyrellplayz.big_industries;

import com.google.common.collect.Lists;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public enum Port implements StringRepresentable {

    NONE(0,"none"),
    IMPORT(1,"import"),
    EXPORT(2,"export");

    private final int id;
    private final String name;
    private static final Port[] VALUES = values();
    private static final Port[] BY_ID = Arrays.stream(VALUES).sorted(Comparator.comparingInt((port) -> port.id)).toArray(Port[]::new);

    Port(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Port next() {
        int next = this.id+1;
        if(next > 2) return BY_ID[0];
        return BY_ID[next];
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    public static class Property extends EnumProperty<Port> {

        public Property(String name, Collection<Port> ports) {
            super(name, Port.class, ports);
        }

        public static Port.Property create(String name, Port... ports) {
            return create(name, Lists.newArrayList(ports));
        }

        public static Port.Property create(String name, Collection<Port> ports) {
            return new Property(name, ports);
        }

    }

}
