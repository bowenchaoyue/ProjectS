package exception;

import domain.ResponseMessage;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/8/31.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    ResponseMessage handleBusinessException(BusinessException e){
        ResponseMessage rs = new ResponseMessage(false);
        rs.setMessage(e.getMessage());
        return rs;
    }

//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    ResponseMessage handleException(Exception e){
//        ResponseMessage rs = new ResponseMessage(false);
//        rs.setMessage("服务器内部错误，请联系管理员！");
//        return rs;
//    }
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    ResponseMessage handleException(Exception e){
//        ResponseMessage rs = new ResponseMessage(false);
//        rs.setMessage("服务器内部错误，请联系管理员！"+e.getMessage());
//        return rs;
//    }
}
