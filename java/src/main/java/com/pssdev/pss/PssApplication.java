package com.pssdev.pss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = {
   DataSourceAutoConfiguration.class,
   JpaRepositoriesAutoConfiguration.class,
   HibernateJpaAutoConfiguration.class })
@EnableTransactionManagement(proxyTargetClass = true)
@EnableSwagger2
public class PssApplication {

   public static void main(String[] args) {
      SpringApplication.run(PssApplication.class, args);
   }

}
