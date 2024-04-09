package org.oolong.entity.basic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.oolong.utils.TimeUtil;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: wxm
 * @created: 2024/04/02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEntity {
    
    private Integer code;
    
    private String msg;
    
    private String path;
    
    private String time;
    
    private Object data;
    
    
    public static ResponseEntity success(Object data) {
        return new ResponseEntity(200, "success", getReqPath(), TimeUtil.timeFormatNow(), data);
    }
    
    public static ResponseEntity success() {
        return success(null);
    }
    
    
    public static ResponseEntity failure(Object data) {
        return new ResponseEntity(400, "failure", getReqPath(), TimeUtil.timeFormatNow(), data);
    }
    
    private static String getReqPath() {
        String path = "";
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            path = request.getRequestURI();
        }
        return path;
    }
}
