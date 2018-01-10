package domain;


/**
 * 请求返回结果类
 * @author Administrator
 *
 */
public class Result {
	private int resultCode;
	private boolean success;
	private Object data;
	private String message;
	
	public Result(boolean success){
		this.success = success;
		this.message = "";
	}
	public Result(boolean success, String message){
		this.success = success;
		this.message = message;
	}
	public Result(int resultCode, boolean success, String message){
		this.resultCode = resultCode;
		this.success = success;
		this.message = message;
	}

	public static Result error(String message) {
		return new Result(false, message);
	}

	public static Result success(String message) {
		return new Result(true, message);
	}

	public static Result success(String message, Object data) {
		Result result = new Result(true, message);
		result.setData(data);
		return result;
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
