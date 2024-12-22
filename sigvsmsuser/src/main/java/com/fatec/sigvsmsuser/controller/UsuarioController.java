package com.fatec.sigvsmsuser.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.sigvsmsuser.model.Cliente;
import com.fatec.sigvsmsuser.model.ClienteRecordDTO;
import com.fatec.sigvsmsuser.service.ClienteService;

import jakarta.validation.Valid;

@CrossOrigin("*") // desabilita o cors do spring security
@RestController
@RequestMapping("/api/v1/clientes")
public class UsuarioController {
	
	final ClienteService clienteService;
	
	
	public UsuarioController (ClienteService usuarioService) {
		this.clienteService = usuarioService;
	}
	@PostMapping
	public ResponseEntity<Cliente> saveCliente(@RequestBody @Valid ClienteRecordDTO cliente){
		Cliente novoCliente = new Cliente();
		novoCliente.setCpf(cliente.cpf());
		novoCliente.setNome(cliente.nome());
		novoCliente.setCep(cliente.cep());
		novoCliente.setEmail(cliente.email());
		Optional<Cliente> c = clienteService.save(novoCliente);
		if (c.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(c.get());
		}
		
	}

}
