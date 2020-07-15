package com.pssdev.pss.annotation;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface AuditObject {

   /**
    * object name provider spel expression
    */
   String value();
}
