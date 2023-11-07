package com.rocky.boot.design.patterns.decorator;

/**
 * @author rocky
 * @Description: 星巴克咖啡测试类
 * @Date: Created in 2018/12/11
 */
public class StarbuzzCoffee {

    public static void main(String[] args) {
        // 点一份浓缩咖啡
        Beverage espresso = new Espresso();
        System.out.println(espresso.getDescription() + " $" + espresso.cost());

        // 点一份深焙咖啡，加两份摩卡，加一份奶泡
        Beverage darkRoast = new DarkRoast();
        darkRoast = new Mocha(darkRoast);
        darkRoast = new Mocha(darkRoast);
        darkRoast = new Whip(darkRoast);
        System.out.println(darkRoast.getDescription() + " $" + darkRoast.cost());

        // 点一份综合咖啡，加一份豆浆,加一份摩卡，加一份奶泡
        Beverage houseBlend = new HouseBlend();
        houseBlend = new Soy(houseBlend);
        houseBlend = new Mocha(houseBlend);
        houseBlend = new Whip(houseBlend);
        System.out.println(houseBlend.getDescription() + " $" + houseBlend.cost());
    }
}
