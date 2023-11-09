package com.rocky.boot.design.patterns.factory.abstractfactory;

/**
 * 定义完整鞋子工厂接口，一个完整的鞋子由鞋子和鞋垫组成
 * @author : rocky
 * @date : created in 2023/11/9
 */
public interface CompleteShoeFactory {

    /**
     * shoe
     * @return shoe
     */
    Shoe createShoe();

    /**
     * insole
     * @return insole
     */
    Insole createInsole();
}
