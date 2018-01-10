package exception;

import domain.Result;
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
    Result handleBusinessException(BusinessException e){
        Result rs = new Result(false);
        rs.setMessage(e.getMessage());
        return rs;
    }

//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    Result handleException(Exception e){
//        Result rs = new Result(false);
//        rs.setMessage("服务器内部错误，请联系管理员！");
//        return rs;
//    }
//    @ExceptionHandler(Exception.class)
//    @ResponseBody
//    Result handleException(Exception e){
//        Result rs = new Result(false);
//        rs.setMessage("服务器内部错误，请联系管理员！"+e.getMessage());
//        return rs;
//    }
}
