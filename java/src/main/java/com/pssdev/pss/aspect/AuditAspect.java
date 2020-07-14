package com.pssdev.pss.aspect;

import com.pssdev.pss.annotation.Audit;
import com.pssdev.pss.entity.Log;
import com.pssdev.pss.service.LogService;
import com.pssdev.pss.util.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.Objects;

@Configuration
@Aspect
public class AuditAspect {

  @Autowired
  public AuditAspect(LogService logService) {
    this.logService = logService;
  }

  @Pointcut("@annotation(com.pssdev.pss.annotation.Audit) && within(com.pssdev.pss..*)")
  public void audit() {
  }

  @Around("audit()")
  public Object recordLog(ProceedingJoinPoint pjp) throws Throwable {
    Log log = null;
    Object principal = null;

    try {
      Subject subject = SecurityUtils.getSubject();
      principal = subject.getPrincipal();
    } catch (Exception ignore) {
    }

    try {
      MethodSignature signature = (MethodSignature) pjp.getSignature();
      Audit annotation = signature.getMethod().getAnnotation(Audit.class);
      ResourceEnum resource = annotation.value();
      ActionType actionType = annotation.actionType();
      log = new Log();
      log.setDate(new Date());
      log.setEmployee(Objects.toString(principal, SecurityUtil.Anonymous));
      log.setResource(resource.getLabel());
      log.setAction(actionType.getLabel());
    } catch (Exception ignore) {
      LOGGER.warn("Build Log Failed!");
    }

    Object result;

    try {
      result = pjp.proceed();
    } catch (Throwable throwable) {
      if (log != null) {
        log.setMessage("Execute Failed: " + throwable.getMessage());
      }

      throw throwable;
    } finally {
      if (log != null) {
        logService.insertLog(log);
      }
    }

    return result;
  }

  private final LogService logService;

  private static final Logger LOGGER = LoggerFactory.getLogger(AuditAspect.class);
}
