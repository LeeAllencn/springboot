package com.rocky.boot.common.util;

import org.junit.Test;

/**
 * @author rocky
 * Description:
 * Created in 2021/3/12
 */
public class FloatingOperationUtilTest {

    @Test
    public void add() {
        System.out.println(FloatingOperationUtil.add(0.05, 0.01));
    }

    @Test
    public void sub() {
        System.out.println(FloatingOperationUtil.sub(1.0, 0.42));
    }

    @Test
    public void mul() {
        System.out.println(FloatingOperationUtil.mul(4.015, 100));
    }

    @Test
    public void div() {
        System.out.println(FloatingOperationUtil.div(123.3, 100));
    }
}