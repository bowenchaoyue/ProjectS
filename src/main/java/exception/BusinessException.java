package exception;

/**
 * Created by chaowen on 2017/8/16.
 */
public class BusinessException extends BaseException {
    public BusinessException() {
        super();
    }

    public BusinessException(String errCode) {
        super(errCode);
    }

    public BusinessException(String errCode, String[] params) {
        super(errCode, params);
    }

    public BusinessException(String errCode, String message) {
        super(errCode, message);
    }



    public static void throwMessage(String errCode,String message){
        throw  new BusinessException(errCode, message);
    }

    public static void throwMessage(String errCode){
        throw new BusinessException(errCode);
    }


}
