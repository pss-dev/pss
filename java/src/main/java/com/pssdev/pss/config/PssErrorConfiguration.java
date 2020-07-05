package com.pssdev.pss.config;

import org.springframework.boot.web.server.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class PssErrorConfiguration implements ErrorPageRegistrar {

   @Override
   public void registerErrorPages(ErrorPageRegistry registry) {
      ErrorPage[] errorPages = new ErrorPage[2];
      // server redirect 404 to index of web.
      errorPages[0] = new ErrorPage(HttpStatus.NOT_FOUND, "/index.html");
      errorPages[1] = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/exception");

      registry.addErrorPages(errorPages);
   }
}
