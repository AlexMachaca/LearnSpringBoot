package com.iis.app.business.office.response;

import java.util.ArrayList;
import java.util.List;

import com.iis.app.business.ResponseGeneral;

public class ResponseGetAllOffice extends ResponseGeneral {
    public class Dto {
		public List<Object> listOffice1;
	}

    public class DtoOffice{
        public List<Object> listOffice;
    }

	//public Dto dto;

	public ResponseGetAllOffice() {

		Dto dto = new Dto();
        DtoOffice dtoOffice=new DtoOffice();

		dto.listOffice1 = new ArrayList<>();
        dtoOffice.listOffice=new ArrayList<>();
	}
}
