package com.pssdev.pss.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@ConfigurationProperties(prefix = "pss.hibernate")
public class SessionFactoryConfig {
   @Autowired
   private DataSource dataSource;

   @Bean("sessionFactory")
   public LocalSessionFactoryBean getSessionFactory() throws IOException {
      LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
      localSessionFactoryBean.setDataSource(dataSource);
      PathMatchingResourcePatternResolver pmprpr = new PathMatchingResourcePatternResolver();
      Resource[] resource = pmprpr.getResources("classpath*:com/pssdev/pss/**/domain/*.hbm.xml");
      localSessionFactoryBean.setMappingLocations(resource);
      Properties hibernateProperties = new Properties();
      hibernateProperties.put("hibernate.dialect",dialect);
      hibernateProperties.put("current_session_context_class", sessionContextClass);
      hibernateProperties.put("hibernate.show_sql", showSql);
      hibernateProperties.put("hibernate.format_sql", formatSql);
      hibernateProperties.put("hibernate.hbm2ddl.auto", ddlAuto);
      localSessionFactoryBean.setHibernateProperties(hibernateProperties);

      return localSessionFactoryBean;
   }

   public DataSource getDataSource() {
      return dataSource;
   }

   public void setDataSource(DataSource dataSource) {
      this.dataSource = dataSource;
   }

   public String getDialect() {
      return dialect;
   }

   public void setDialect(String dialect) {
      this.dialect = dialect;
   }

   public String getSessionContextClass() {
      return sessionContextClass;
   }

   public void setSessionContextClass(String sessionContextClass) {
      this.sessionContextClass = sessionContextClass;
   }

   public boolean isShowSql() {
      return showSql;
   }

   public void setShowSql(boolean showSql) {
      this.showSql = showSql;
   }

   public boolean isFormatSql() {
      return formatSql;
   }

   public void setFormatSql(boolean formatSql) {
      this.formatSql = formatSql;
   }

   public String getDdlAuto() {
      return ddlAuto;
   }

   public void setDdlAuto(String ddlAuto) {
      this.ddlAuto = ddlAuto;
   }

   private String dialect;
   private String sessionContextClass;
   private boolean showSql;
   private boolean formatSql;
   private String ddlAuto;
}
