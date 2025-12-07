package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

	@Mock
	private UsuarioRepository usuarioRepository;
	
	@InjectMocks
	private UsuarioService usuarioService;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void deveRetornarUsuarioQuandoIdExistir() {
		Usuario usuario = new Usuario(Long.valueOf(1), "João Silva", "js@mail.com");
		when(usuarioRepository.findById(anyLong())).thenReturn(Optional.of(usuario));
		Usuario usuarioRetorno = usuarioService.buscarUsuarioPorId(Long.valueOf(1));
		assertNotNull(usuarioRetorno);
		assertEquals(usuario, usuarioRetorno);
	}

	@Test
	void deveLancarExcecaoQuandoUsuarioNaoExistir() {
		assertThrows(RuntimeException.class, () -> usuarioService.buscarUsuarioPorId(anyLong()));
	}
	
	@Test
	void deveSalvarUsuarioComSucesso() {
		Usuario usuario = new Usuario(Long.valueOf(1), "João Silva", "js@mail.com");
		usuarioService.salvarUsuario(usuario);
		
		Usuario usuarioRetorno = usuarioService.buscarUsuarioPorId(Long.valueOf(1));

		assertNotNull(usuarioRetorno);
		assertEquals(usuario, usuarioRetorno);
	}
	
}
