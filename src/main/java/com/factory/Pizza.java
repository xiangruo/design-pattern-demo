package com.factory;

import java.util.ArrayList;
import java.util.List;

public class Pizza {

    protected String name;

    protected String dough;

    protected String sauce;

    List<String> toppings = new ArrayList<String>();

    public void prepare() {
        System.out.println("正在准备比萨：" + name);
        System.out.println("搅拌面团：" + dough);
        System.out.println("添加酱料：" + sauce);

        for (String topping : toppings) {
            System.out.println("添加配料：" + topping);
        }
    }

    public void bake() {
        System.out.println("烘烤25分钟");
    }

    public void cut() {
        System.out.println("对称切割成8块");
    }

    public void box() {
        System.out.println("装在官方的包装盒中");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDough() {
        return dough;
    }

    public void setDough(String dough) {
        this.dough = dough;
    }

    public String getSauce() {
        return sauce;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public List<String> getToppings() {
        return toppings;
    }

    public void setToppings(List<String> toppings) {
        this.toppings = toppings;
    }
    
    

}
