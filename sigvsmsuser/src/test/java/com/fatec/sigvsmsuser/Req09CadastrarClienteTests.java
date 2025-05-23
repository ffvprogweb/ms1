package com.fatec.sigvsmsuser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.fatec.sigvsmsuser.model.Cliente;
import com.fatec.sigvsmsuser.service.ClienteRepository;

@DataJpaTest
class Req09CadastrarClienteTests {
	private Cliente cliente;
	@Autowired
	private ClienteRepository clienteRepository;

	public String dataAtual() {
		LocalDate dataAtual = LocalDate.now();
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return dataAtual.format(pattern);
		
	}

	@Test
	void ct01_quando_dados_validos_cliente_cadastrado_com_sucesso() {
		// Dado - que o cpf nao esta cadastrado
		// Quando - campos obrigatorios validos
		cliente = new Cliente();
		cliente.setCpf("80983098000");
		cliente.setNome("Jose da Silva");
		cliente.setCep("01310-100");
		cliente.setEndereco("Av. Paulista");
		cliente.setEmail("jose@gmail.com");
		cliente.setDataCadastro();
		clienteRepository.save(cliente);
		// Entao - retorna cliente cadastrado com sucesso clienteRepository=1
		assertEquals(1, clienteRepository.count());
		assertEquals(dataAtual(), cliente.getDataCadastro());
	}

	@Test
	void ct02_quando_dados_invalidos_retorna_invalido() {
		// Dado - que o cpf nao esta cadastrado
		// Quando - cpf branco )
		try {
			cliente = new Cliente();
			cliente.setCpf("");
		} catch (Exception e) {
			// Entao - retorna cpf invalido
			assertEquals("CPF invalido", e.getMessage());
		}
	}
}
