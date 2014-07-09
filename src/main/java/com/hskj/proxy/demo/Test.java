package com.hskj.proxy.demo;

public class Test {
    
    public static void main(String[] args) {
        people people_ref1 = new people();
        people_ref1.setCash(4000);
        people_ref1.setUsername("高洪岩");

        people people_ref2 = new people();
        people_ref2.setCash(2000);
        people_ref2.setUsername("岩洪高");

        ProxyBuyCarImple proxy_buy_car_imple = new ProxyBuyCarImple();
        proxy_buy_car_imple.setPeople(people_ref1);
        proxy_buy_car_imple.buy_car();

        proxy_buy_car_imple.setPeople(people_ref2);
        proxy_buy_car_imple.buy_car();

    }

}
