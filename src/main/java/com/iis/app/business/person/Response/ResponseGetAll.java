package com.iis.app.business.person.Response;

import java.util.ArrayList;
import java.util.List;

import com.iis.app.business.ResponseGeneral;

public class ResponseGetAll extends ResponseGeneral {
	public class Dto {
		public List<Object> listPerson;
	}

	public Dto dto;

	public ResponseGetAll() {
		dto = new Dto();

		dto.listPerson = new ArrayList<>();
	}
}