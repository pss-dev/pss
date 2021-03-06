package com.pssdev.pss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = {
   JpaRepositoriesAutoConfiguration.class,
   HibernateJpaAutoConfiguration.class
})
@EnableTransactionManagement
@EnableCaching
public class PssApplication {

   public static void main(String[] args) {
      SpringApplication.run(PssApplication.class, args);
   }

}
