package com.ecommerce.ecommerce.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.entities.Domicilio;
import com.ecommerce.ecommerce.services.DomicilioService;
import com.ecommerce.ecommerce.services.UsuarioService;

@RestController
@RequestMapping("api/v1/direccion")
@CrossOrigin("*")
public class DireccionRestController {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	DomicilioService domicilioService;

	@PostMapping("/agregar")
	public Domicilio agregarDomicilio(@RequestBody Domicilio newDomicilio) {
		String username = "";
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		}

		newDomicilio.setUser(this.usuarioService.traerUser(username));

		return this.domicilioService.guardarDomicilio(newDomicilio);
	}

	@GetMapping("/getDomiciliosDeUsuario")
	public List<Domicilio> getDomiciliosDeUsuario() {
		String username = "";

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		}

		return this.domicilioService.findByIdUser(this.usuarioService.traerUser(username).getId());
	}

}