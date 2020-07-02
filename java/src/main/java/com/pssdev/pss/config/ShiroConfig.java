package com.pssdev.pss.config;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

   @Bean("lifecycleBeanPostProcessor")
   public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
      return new LifecycleBeanPostProcessor();
   }

   @Bean
   @DependsOn("lifecycleBeanPostProcessor")
   @ConditionalOnMissingBean
   public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
      DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator
         = new DefaultAdvisorAutoProxyCreator();
      advisorAutoProxyCreator.setProxyTargetClass(true);

      return advisorAutoProxyCreator;
   }

   @Bean("securityManager")
   public SecurityManager securityManager(AuthorizingRealm realm) {
      DefaultWebSecurityManager securityManager
         = new DefaultWebSecurityManager(realm);

      return securityManager;
   }

   @Bean
   public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
      ShiroFilterFactoryBean shiroFilterFactoryBean
         = new ShiroFilterFactoryBean();
      shiroFilterFactoryBean.setSecurityManager(securityManager);

      // 设置默认登录的 URL，身份认证失败会访问该 URL
      shiroFilterFactoryBean.setLoginUrl("/login");
      // 设置成功之后要跳转的链接
      shiroFilterFactoryBean.setSuccessUrl("/success");
      // 设置未授权界面，权限认证失败会访问该 URL
      shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");

      // LinkedHashMap 是有序的，进行顺序拦截器配置
      Map<String,String> filterChainMap = new LinkedHashMap<>();

      // 配置可以匿名访问的地址，可以根据实际情况自己添加，放行一些静态资源等，anon 表示放行
      filterChainMap.put("/css/**", "anon");
      filterChainMap.put("/imgs/**", "anon");
      filterChainMap.put("/js/**", "anon");
      filterChainMap.put("/assets/**", "anon");
      filterChainMap.put("/swagger-*/**", "anon");
      filterChainMap.put("/swagger-ui.html/**", "anon");
      // 登录 URL 放行
      filterChainMap.put("/login", "anon");
      filterChainMap.put("/api/1.0/login", "anon");

      // 配置 logout 过滤器
      filterChainMap.put("/logout", "logout");

      // authc 表示要进行身份认证
      filterChainMap.put("/**", "authc");

      // 设置 shiroFilterFactoryBean 的 FilterChainDefinitionMap
      shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);

      return shiroFilterFactoryBean;
   }

   /**
    * 用于映射匹配权限注解.
    */
   @Bean
   public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
      AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
      authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);

      return authorizationAttributeSourceAdvisor;
   }

   /**
    * 密码加密.
    */
   @Bean
   public CredentialsMatcher credentialsMatcher() {
      HashedCredentialsMatcher credentialsMatcher
         = new HashedCredentialsMatcher("MD5");
      credentialsMatcher.setHashIterations(1024);

      return credentialsMatcher;
   }
}
