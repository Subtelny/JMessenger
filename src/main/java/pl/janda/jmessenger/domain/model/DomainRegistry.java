package pl.janda.jmessenger.domain.model;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class DomainRegistry implements ApplicationContextAware {

    private static ApplicationContext applicationContext;



    @Override
    public synchronized void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        DomainRegistry.applicationContext = applicationContext;
    }

}
