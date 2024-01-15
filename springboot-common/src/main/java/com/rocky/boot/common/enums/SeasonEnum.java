package com.rocky.boot.common.enums;

/**
 * @author : rocky
 * @date : created in 2024/1/15
 */
public enum SeasonEnum {
    /**
     * 枚举项
     */
    SPRING(1),
    SUMMER(2),
    AUTUMN(3),
    WINTER(4);

    private final int seq;

    SeasonEnum(int seq) {
        this.seq = seq;
    }
    public int getSeq() {
        return seq;
    }
}
