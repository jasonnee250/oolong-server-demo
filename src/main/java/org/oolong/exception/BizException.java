package org.oolong.exception;

/**
 * @author: wxm
 * @created: 2024/04/03
 */
public class BizException extends RuntimeException {
    
    public BizException() {
        super();
    }
    
    public BizException(String message) {
        super(message);
    }
    
    public BizException(String message, Throwable cause) {
        super(message, cause);
    }
}
