package com.gzdata.common.db.mybatis.result;

public class Result {

	private int code;

	private String msg;

	private int count;

	private Object data;

	public final static int SUCCESS = 200;
	public final static int ERROR = 500;
	public final static int VALAID = 401;
	public final static String SUCCESS_STR = "success";
	public final static String ERROR_STR = "error";

	public Result(int code, String msg) {
		this.msg = msg;
		this.code = code;
	}

	public static Result valueOf(int code) {

		switch (code) {
		case SUCCESS:
			return new Result(code, SUCCESS_STR);
		case ERROR:
			return new Result(code, ERROR_STR);
		default:
			return null;
		}
	}

	public static Result valueOf(int code, String msg) {
		Result r = Result.valueOf(code);
		if (r == null) {
			return null;
		}
		r.setMsg(msg);
		return r;
	}

	public static Result valueOf(int code, Object data) {
		Result r = Result.valueOf(code);
		if (r == null) {
			return null;
		}
		r.setData(data);
		return r;
	}

	public static Result valueOf(int code, String msg, Object data) {
		Result r = Result.valueOf(code, msg);
		if (r == null) {
			return null;
		}
		r.setData(data);
		return r;
	}

	public static Result valueOf(int code, String msg, int count,
			Object data) {
		Result r = Result.valueOf(code, msg, data);
		if (r == null) {
			return null;
		}
		// r.setData(data);
		r.setCount(count);
		return r;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResultPage [code=" + code + ", msg=" + msg + ", count=" + count
				+ ", data=" + data + "]";
	}

}
