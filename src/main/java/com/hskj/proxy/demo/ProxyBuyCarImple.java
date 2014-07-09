package com.hskj.proxy.demo;

public class ProxyBuyCarImple implements  BuyCar{
    
    
    private people people;

    public people getPeople() {
        return people;
    }

    public void setPeople(people people) {
        this.people = people;
    }

    public void buy_car() {
        if (people.getCash() > 3000) {
            System.out.println(people.getUsername() + "花" + people.getCash()
                    + "块　买了新车　交易结束");
        } else {
            System.out.println(people.getUsername() + "金钱不够，请继续比赛!");
        }
    }

}
