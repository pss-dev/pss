package com.pssdev.pss.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
public class TransactionConfig implements TransactionManagementConfigurer {

   @Autowired
   private HibernateTransactionManager transactionManager;

   @Bean("transactionManager")
   public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
      HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
      hibernateTransactionManager.setSessionFactory(sessionFactory);

      return hibernateTransactionManager;
   }

   @Override
   public PlatformTransactionManager annotationDrivenTransactionManager() {
      return transactionManager;
   }
}
