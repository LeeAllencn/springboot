package com.rocky.boot.design.patterns.decorator;

/**
 * @author rocky
 * description: 调料抽象类
 * @date Created in 2018/12/11
 */
public abstract class CondimentDecorator extends Beverage {

    /**
     * 获取描述
     * @return
     */
    public abstract String getDescription();
}
