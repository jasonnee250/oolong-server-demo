package org.oolong.entity.basic;

public enum SubType {
    UNKNOWN("UNKNOWN"),
    CONST_SOURCE("CONST_SOURCE"),
    NORMAL_SINK("NORMAL_SINK"),
    GAIN_PROCESS("GAIN_PROCESS");

    SubType(String type){
        this.type=type;
    }

    private String type;

    public static SubType create(String type){
        switch (type){

            case "CONST_SOURCE":
                return CONST_SOURCE;
            case "NORMAL_SINK":
                return NORMAL_SINK;
            case "GAIN_PROCESS":
                return GAIN_PROCESS;
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
