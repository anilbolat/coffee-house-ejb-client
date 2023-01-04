package com.github.anilbolat.coffeehouseclient;

import com.github.anilbolat.ejbtest.ejb.remote.CoffeeOrderRemoteEJB;
import com.github.anilbolat.ejbtest.ejb.remote.CoffeeOrderRemoteStatefulEJBImpl;
import com.github.anilbolat.ejbtest.ejb.remote.CoffeeRemoteEJB;
import com.github.anilbolat.ejbtest.ejb.remote.CoffeeRemoteStatelessEJBImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class EJBLookupManager {

    private static final String APP_NAME = "coffee_house_ejb_war_exploded";

    private EJBLookupManager() {
    }

    public static CoffeeRemoteEJB createCoffeeRemoteStatelessEJBFromJNDI() throws NamingException {
        return lookupCoffeeRemoteStatelessEJBBean();
    }

    public static CoffeeOrderRemoteEJB createCoffeeOrderRemoteStatefulEJBFromJNDI() throws NamingException {
        return lookupCoffeeOrderRemoteStatefulEJBBean();
    }

    private static CoffeeRemoteEJB lookupCoffeeRemoteStatelessEJBBean() throws NamingException {
        Context ctx = createInitialContext();
        String namespace = "";
        String beanName = CoffeeRemoteStatelessEJBImpl.class.getSimpleName();
        String viewClassName = CoffeeRemoteEJB.class.getName();
        Object lookupObject = ctx.lookup(namespace + APP_NAME + "/" + beanName + "!" + viewClassName);
        return (CoffeeRemoteEJB) lookupObject;
    }


    private static CoffeeOrderRemoteEJB lookupCoffeeOrderRemoteStatefulEJBBean() throws NamingException {
        Context ctx = createInitialContext();
        String namespace = "";
        String beanName = CoffeeOrderRemoteStatefulEJBImpl.class.getSimpleName();
        String viewClassName = CoffeeOrderRemoteEJB.class.getName();
        Object lookupObject = ctx.lookup(namespace + APP_NAME + "/" + beanName + "!" + viewClassName);
        return (CoffeeOrderRemoteEJB) lookupObject;
    }

    private static Context createInitialContext() throws NamingException {
        Properties jndiProperties = new Properties();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:8080");
        jndiProperties.put("jboss.naming.client.ejb.context", false);
        return new InitialContext(jndiProperties);
    }

}
