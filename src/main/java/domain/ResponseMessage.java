package domain;


/**
 * 请求返回结果类
 * @author Administrator
 *
 */
public class ResponseMessage {
	private int resultCode;
	private boolean success;
	private Object data;
	private String message;
	
	public ResponseMessage(boolean success){
		this.success = success;
		this.message = "";
	}
	public ResponseMessage(boolean success, String message){
		this.success = success;
		this.message = message;
	}
	public ResponseMessage(int resultCode, boolean success, String message){
		this.resultCode = resultCode;
		this.success = success;
		this.message = message;
	}

	public static ResponseMessage error(String message) {
		return new ResponseMessage(false, message);
	}

	public static ResponseMessage success(String message) {
		return new ResponseMessage(true, message);
	}

	public static ResponseMessage success(String message, Object data) {
		ResponseMessage responseMessage = new ResponseMessage(true, message);
		responseMessage.setData(data);
		return responseMessage;
	}


	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
