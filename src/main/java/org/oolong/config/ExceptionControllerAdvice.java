package org.oolong.config;

import lombok.extern.slf4j.Slf4j;
import org.oolong.entity.basic.ResponseEntity;
import org.oolong.exception.BizException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: wxm
 * @created: 2024/04/02
 */
@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {
    
    @ResponseBody
    @ExceptionHandler(value = BizException.class)
    public ResponseEntity bizException(BizException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.failure(e.getMessage());
    }
    
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity exception(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.failure(e.getMessage());
    }
    
}
