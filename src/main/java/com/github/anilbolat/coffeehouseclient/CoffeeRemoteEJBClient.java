package com.github.anilbolat.coffeehouseclient;

import com.github.anilbolat.ejbtest.data.Coffee;
import com.github.anilbolat.ejbtest.ejb.remote.CoffeeRemoteEJB;

import javax.naming.NamingException;

public class CoffeeRemoteEJBClient {

    public static void main(String[] args) throws NamingException {
        CoffeeRemoteEJB coffeeRemoteEJB = EJBLookupManager.createCoffeeRemoteStatelessEJBFromJNDI();

        System.out.println("Coffee List -> " + coffeeRemoteEJB.findAll());

        Coffee aNewCoffee = new Coffee("Colombia is the best!", "5 euro");
        coffeeRemoteEJB.create(aNewCoffee);
        System.out.println("Coffee List -> " + coffeeRemoteEJB.findAll());
        coffeeRemoteEJB.create(new Coffee("Brazil is the second!", "4 euro"));
        System.out.println("Coffee List -> " + coffeeRemoteEJB.findAll());
        coffeeRemoteEJB.update(2L, new Coffee("Brazil is the second!", "3 euro"));
        System.out.println("Coffee List -> " + coffeeRemoteEJB.findAll());
        coffeeRemoteEJB.create(new Coffee("Ethiopia is the third one!", "2 euro"));
        System.out.println("Coffee List -> " + coffeeRemoteEJB.findAll());
        coffeeRemoteEJB.remove(2L);
        System.out.println("Coffee List -> " + coffeeRemoteEJB.findAll());
    }

}
