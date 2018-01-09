package exception;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chaowen on 2017/8/16.
 */
public class BaseException extends RuntimeException {

    private String errCode;

    private List<String> params = new ArrayList<String>(0);

    private String message;

    public BaseException(){

    }

    public BaseException(String errCode){
        this.errCode = errCode;
        if (message == null){
            this.message = errCode;
        }
    }

    public BaseException(String errCode,List<String> params){
        this.errCode = errCode;
        this.params = params;
        if (message == null){
            this.message =errCode;
        }
    }

    public BaseException(String errCode,String[] params){
        this.errCode = errCode;
        for (String s :params){
            this.params.add(s);
        }
        if (message == null){
            this.message = message;
        }
    }

    public BaseException(String errCode,String message){
        this.errCode = errCode;
        this.message =message;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
        if (message == null){
            this.message =errCode;
        }
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setParam(String p){
        this.params.add(p);
    }

}
