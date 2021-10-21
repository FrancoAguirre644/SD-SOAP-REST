package com.example.modulocorreo.controllers;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.modulocorreo.dto.ApiResponse;
import com.example.modulocorreo.dto.CreateEnvioRequest;
import com.example.modulocorreo.dto.InsertOrUpdateEnvioResponse;
import com.example.modulocorreo.entities.Envio;
import com.example.modulocorreo.services.IEnvioService;

@RestController
@RequestMapping("/api/v1/envios")
@CrossOrigin("*")
public class EnvioRestController {
	
	@Autowired
	@Qualifier("envioService")
	private IEnvioService envioService;

	@GetMapping("")
	public ResponseEntity<List<Envio>> getAllEnvios() {
		return ResponseEntity.ok(envioService.getAll());
	}
	
	@PostMapping("")
	public ResponseEntity<?> createEnvio(@Valid @RequestBody CreateEnvioRequest createEnvioRequest) {
		
		Envio envio = envioService.insertOrUpdate(new Envio(createEnvioRequest.getDescripcion(),Envio.ESTADO_EN_PREPARACION , createEnvioRequest.getDniDestinatario(), UUID.randomUUID().toString()));

		return ResponseEntity.status(HttpStatus.CREATED).body(new InsertOrUpdateEnvioResponse(new ApiResponse(true, "Envio creado correctamente."), envio));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getEnvio(@PathVariable("id") int idEnvio) {
		
		Envio envio = envioService.findById(idEnvio);
		
		if(envio == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, "Envio no encontrado."));
		
		return ResponseEntity.ok(envio);
	}
	
	@GetMapping("/codigo/{codigoDeSeguimiento}")
	public ResponseEntity<?> getEnvioByCodigoDeSeguimiento(@PathVariable("codigoDeSeguimiento") String codigoDeSeguimiento) {
		
		Envio envio = envioService.findByCodigoDeSeguimiento(codigoDeSeguimiento);

		if(envio == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, "Envio no encontrado."));

		return ResponseEntity.ok(envio);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateEstadoDeEnvio(@PathVariable("id") int idEnvio, @RequestParam(name="estado") String estado) {
				
		Envio envio = envioService.findById(idEnvio);
		
		if(envio == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, "Envio no encontrado."));
		
		envio.setEstado(estado);
		
		Envio envioActualizado = envioService.insertOrUpdate(envio); 
				
		return ResponseEntity.status(HttpStatus.CREATED).body((new InsertOrUpdateEnvioResponse(new ApiResponse(true, "Envio del envio actualizado correctamente."), envioActualizado)));
	}

}
