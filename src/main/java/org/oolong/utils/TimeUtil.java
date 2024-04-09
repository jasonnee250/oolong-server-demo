package org.oolong.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author: wxm
 * @created: 2024/04/02
 */
public abstract class TimeUtil {
    
    public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    
    /**
     * 当前时间转换为 yyyy-MM-dd HH:mm:ss
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String timeFormatNow() {
        return DateTimeFormatter.ofPattern(yyyy_MM_dd_HH_mm_ss).format(LocalDateTime.now());
    }
}