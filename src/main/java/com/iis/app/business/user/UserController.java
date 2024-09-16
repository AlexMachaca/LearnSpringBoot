package com.iis.app.business.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iis.app.Jwt.JwtService;
import com.iis.app.business.user.request.RequestLogin;
import com.iis.app.business.user.response.ResponseLogin;
import com.iis.app.dto.DtoUser;
import com.iis.app.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
	private UserService userService;
	@Autowired
	private JwtService jwtService;

	@PostMapping(path = "login", consumes = { "multipart/form-data" })
	public ResponseEntity<ResponseLogin> actionLogin(@Valid @ModelAttribute RequestLogin soLogin, BindingResult bindingResult) {
        ResponseLogin responseLogin = new ResponseLogin();

		DtoUser dtoUser = userService.getLogin(soLogin.getUserName(), soLogin.getPassword());

		if(dtoUser == null) {
			responseLogin.addResponseMessage("Usuario o contraseña incorrecta.");

			return new ResponseEntity<>(responseLogin, HttpStatus.OK);
		}

		Map<String, Object> map = new HashMap<>();

		map.put("idUser", dtoUser.getIdUser());
		map.put("userName", dtoUser.getUserName());

		responseLogin.dto.user = map;
		//Genrar el token
		String token=this.jwtService.generateToken(dtoUser.getIdUser(), dtoUser.getUserName());
		System.out.println("se envia el token "+ token);
		responseLogin.setToken(token);
		responseLogin.addResponseMessage("Bienvenido(a) al sistema.");
		responseLogin.success();

		return new ResponseEntity<>(responseLogin, HttpStatus.OK);
	}
}
