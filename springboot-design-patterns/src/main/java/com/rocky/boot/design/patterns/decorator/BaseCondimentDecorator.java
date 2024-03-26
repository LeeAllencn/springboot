package com.rocky.boot.design.patterns.decorator;

/**
 * @author rocky
 * description: 调料抽象类
 * @date Created in 2018/12/11
 */
public abstract class BaseCondimentDecorator extends BaseBeverage {

    /**
     * 获取描述
     * @return String
     */
    @Override
    public abstract String getDescription();
}
