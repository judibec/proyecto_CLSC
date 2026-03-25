package com.financialLab.financedevapp;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class GlobalApplicationContext implements ApplicationContextAware {

    private static ApplicationContext context;

    public static Object getBean(String name) {
        if (context == null) {
            throw new ApplicationContextException("No Spring Context available");
        }
        return getInstance().getBean(name);
    }

    public static <T> T getBean(Class<T> aClass) {
        if (context == null) {
            throw new ApplicationContextException("No Spring Context available");
        }
        return getInstance().getBean(aClass);
    }

    public static void setGlobalContext(ApplicationContext globalContext) {
        context = globalContext;
    }

    public static ApplicationContext getInstance() {
        return context;
    }

    public static ApplicationContext build(String config) {
        setGlobalContext(new ClassPathXmlApplicationContext(config));
        return getInstance();
    }

    // ApplicationContextAware interface
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        setGlobalContext(ac);
    }
}
