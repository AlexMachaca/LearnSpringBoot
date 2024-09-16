package com.iis.app.business.user.response;

import com.iis.app.business.ResponseGeneral;

public class ResponseLogin extends ResponseGeneral  {
	public String token;
    public class Dto {
		public Object user;
	}

	public Dto dto;

	public ResponseLogin() {
		dto = new Dto();
	}

	public void setToken(String token){
		this.token=token;
	}
}
