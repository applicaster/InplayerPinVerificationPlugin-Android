package com.applicaster.pinverification.models.networkresponse;

public class ValidatePincodeResponse {
	private int code;
	private String message;

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return
			"ValidatePincodeResponse{" +
			"code = '" + code + '\'' +
			",message = '" + message + '\'' +
			"}";
		}
}
