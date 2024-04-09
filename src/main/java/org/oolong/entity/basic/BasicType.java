package org.oolong.entity.basic;

public enum BasicType {
    UNKNOWN("UNKNOWN"),
    SOURCE("SOURCE"),
    SINK("SINK"),
    PROCESS("PROCESS");

    BasicType(String type){
        this.type=type;
    }
    private String type;

    public static BasicType create(String type){
        switch (type){
            case "SOURCE":
                return SOURCE;
            case "SINK":
                return SINK;
            case "PROCESS":
                return PROCESS;
            case "UNKNOWN":
            default:
                return UNKNOWN;
        }
    }

    @Override
    public String toString() {
        return type;
    }
}