package com.pssdev.pss.annotation;

import com.pssdev.pss.util.ActionType;
import com.pssdev.pss.util.ResourceEnum;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Audit {

   /**
    * resource
    */
   ResourceEnum value();

   /**
    * op object name
    */
   String objectName() default "";

   /**
    * op
    */
   ActionType actionType() default ActionType.ADD;
}
