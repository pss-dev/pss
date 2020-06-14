package com.pssdev.pss.config;

import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
//   @Bean("authRealm")
//   public AuthorizingRealm getAuthorizingRealm() {
//      return new PssRealm();
//   }
//
//   @Bean("securityManager")
//   public SecurityManager securityManager(@Qualifier("authRealm") AuthorizingRealm realm) {
//      DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(realm);
//
//      return securityManager;
//   }
//
//   @Bean
//   public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager securityManager) {
//      ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//      shiroFilterFactoryBean.setSecurityManager(securityManager);
//
//      // 设置默认登录的 URL，身份认证失败会访问该 URL
//      shiroFilterFactoryBean.setLoginUrl("/login");
//      // 设置成功之后要跳转的链接
//      shiroFilterFactoryBean.setSuccessUrl("/success");
//      // 设置未授权界面，权限认证失败会访问该 URL
//      shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
//
//      // LinkedHashMap 是有序的，进行顺序拦截器配置
//      Map<String,String> filterChainMap = new LinkedHashMap<>();
//
//      // 配置可以匿名访问的地址，可以根据实际情况自己添加，放行一些静态资源等，anon 表示放行
//      filterChainMap.put("/css/**", "anon");
//      filterChainMap.put("/imgs/**", "anon");
//      filterChainMap.put("/js/**", "anon");
//      filterChainMap.put("/swagger-*/**", "anon");
//      filterChainMap.put("/swagger-ui.html/**", "anon");
//      // 登录 URL 放行
//      filterChainMap.put("/login", "anon");
//
//      // authc 表示要进行身份认证
//      filterChainMap.put("", "authc");
//      // admin 角色才可以
//      filterChainMap.put("", "roles[admin]");
//      // 用户有create权限才可以
//      filterChainMap.put("", "perms[\"user:create\"]");
//
//      // 配置 logout 过滤器
//      filterChainMap.put("/logout", "logout");
//
//      // 设置 shiroFilterFactoryBean 的 FilterChainDefinitionMap
//      shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
//
//      return shiroFilterFactoryBean;
//   }
}
