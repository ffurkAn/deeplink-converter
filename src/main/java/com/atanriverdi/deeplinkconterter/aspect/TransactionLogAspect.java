package com.atanriverdi.deeplinkconterter.aspect;

import com.atanriverdi.deeplinkconterter.model.TransactionLogBean;
import com.atanriverdi.deeplinkconterter.model.dto.ResponseDTO;
import com.atanriverdi.deeplinkconterter.service.impl.TransactionLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class TransactionLogAspect {

    public static final String GET_BODY = "getBody";
    private TransactionLogService transactionLogService;
    private TransactionLogBean transactionLogBean;

    public TransactionLogAspect(TransactionLogService transactionLogService, TransactionLogBean transactionLogBean) {
        this.transactionLogService = transactionLogService;
        this.transactionLogBean = transactionLogBean;
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postMapping() {
    }

    @Around("execution(* com.atanriverdi.deeplinkconterter.controller.DeeplinkConverterController.*(..)) && postMapping() && args(.., @RequestBody body)")
    public void logPostMethods(ProceedingJoinPoint joinPoint, Object body) throws Throwable {

        Method fieldGetter = body.getClass().getMethod(GET_BODY);
        String bodyStr = fieldGetter.invoke(body).toString();

        transactionLogBean.setRequestBody(bodyStr);
        Object result = joinPoint.proceed();
        ResponseEntity responseEntity = (ResponseEntity) result;
        String responseBody = ((ResponseDTO) responseEntity.getBody()).getConvertedBody();
        transactionLogBean.setResponseBody(responseBody);
        transactionLogService.save();
    }
}
