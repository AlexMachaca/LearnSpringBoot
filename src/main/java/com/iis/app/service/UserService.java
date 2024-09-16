package com.iis.app.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iis.app.dto.DtoUser;
import com.iis.app.entity.TUser;
import com.iis.app.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public DtoUser getLogin(String userName, String password) {
		TUser tUser = userRepository.getLogin(userName, password);

		if(tUser == null) {
			return null;
		}

		DtoUser dtoUser = new DtoUser();

		dtoUser.setIdUser(tUser.getIdUser());
		dtoUser.setUserName(tUser.getUserName());
		dtoUser.setCreatedAt(tUser.getCreatedAt());
		dtoUser.setUpdatedAt(tUser.getUpdatedAt());
		
		return dtoUser;
	}
}
