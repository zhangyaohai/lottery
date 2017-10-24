package com.lottery.common.response;

public enum Message {
	
	SUCCESS(200,"成功"),
	NOT_FOUND(400,"TOKEN 不可用"),
	REDIRECT(300,"重定向"),
	FAIL(50000,"失败"),
	PARAM_ERROR(50001,"参数错误");

	public final int code;
    public final String message;
    
    Message(int code, String message) {
        this.code = code;
        this.message = message;
    }

	public int code() {
		return this.code;
	}

	public String message() {
		return this.message;
	}
}
