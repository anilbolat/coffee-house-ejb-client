package com.github.anilbolat.coffeehouseclient;

import com.github.anilbolat.ejbtest.data.Coffee;
import com.github.anilbolat.ejbtest.ejb.remote.CoffeeOrderRemoteEJB;
import com.github.anilbolat.ejbtest.ejb.remote.CoffeeRemoteEJB;

import javax.naming.NamingException;

public class CoffeeRemoteEJBClient {

    public static void main(String[] args) throws NamingException {
        // calls to Remote Stateless EJB
        CoffeeRemoteEJB coffeeRemoteStatelessEJB = EJBLookupManager.createCoffeeRemoteStatelessEJBFromJNDI();

        System.out.println("Coffee List -> " + coffeeRemoteStatelessEJB.findAll());
        Coffee aNewCoffee = new Coffee("Colombia is the best!", "5 euro");
        coffeeRemoteStatelessEJB.create(aNewCoffee);
        System.out.println("Coffee List -> " + coffeeRemoteStatelessEJB.findAll());
        coffeeRemoteStatelessEJB.create(new Coffee("Brazil is the second!", "4 euro"));
        System.out.println("Coffee List -> " + coffeeRemoteStatelessEJB.findAll());
        coffeeRemoteStatelessEJB.update(2L, new Coffee("Brazil is the second!", "3 euro"));
        System.out.println("Coffee List -> " + coffeeRemoteStatelessEJB.findAll());
        coffeeRemoteStatelessEJB.create(new Coffee("Ethiopia is the third one!", "2 euro"));
        System.out.println("Coffee List -> " + coffeeRemoteStatelessEJB.findAll());
        coffeeRemoteStatelessEJB.remove(2L);
        System.out.println("Coffee List -> " + coffeeRemoteStatelessEJB.findAll());

        // calls to Remote Stateful EJB
        CoffeeOrderRemoteEJB coffeeOrderRemoteStatefulEJB = EJBLookupManager.createCoffeeOrderRemoteStatefulEJBFromJNDI();

        System.out.println("Coffee List -> " + coffeeOrderRemoteStatefulEJB.checkCoffeeList());
        System.out.println("price of coffee3 -> " + coffeeOrderRemoteStatefulEJB.checkCoffeePrice(3L));
        System.out.println("price of coffee2 -> " + coffeeOrderRemoteStatefulEJB.checkCoffeePrice(2L));
        System.out.println("price of coffee5 -> " + coffeeOrderRemoteStatefulEJB.checkCoffeePrice(5L));


        System.out.println("Orders -> " + coffeeOrderRemoteStatefulEJB.getOrders());
        orderCoffee(3L, coffeeOrderRemoteStatefulEJB);
        System.out.println("Orders -> " + coffeeOrderRemoteStatefulEJB.getOrders());
        orderCoffee(2L, coffeeOrderRemoteStatefulEJB);
        System.out.println("Orders -> " + coffeeOrderRemoteStatefulEJB.getOrders());
        orderCoffee(5L, coffeeOrderRemoteStatefulEJB);
        System.out.println("Orders -> " + coffeeOrderRemoteStatefulEJB.getOrders());
    }

    private static void orderCoffee(Long coffeeId, CoffeeOrderRemoteEJB coffeeOrderRemoteEJB) {
        if (coffeeOrderRemoteEJB.orderCoffee(coffeeId)) {
            System.out.println("Enjoy!");
        } else {
            System.out.println("Sorry, the coffee not exist! Would you like some other one?");
        }
    }

}
