package org.oolong.biz.error;

/**
 * @Author: J.N
 * @Date 2023/9/24 22:26
 * @Version 1.0
 */
public class DataError extends Error{

    String errMsg;

    public DataError(String errMsg){
        this.errMsg=errMsg;
    }
    @Override
    public String getMessage() {
        return "Data Error!"+this.errMsg;
    }
}
