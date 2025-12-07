package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

	@Mock
	private ProdutoRepository produtoRepository;

	@InjectMocks
	private ProdutoService produtoService;

	@Test
	void deveRetornarProdutoQuandoIdExistir() {
		Produto produto = new Produto(Long.valueOf(1), "placa de video", 0);
		when(produtoRepository.findById(anyLong())).thenReturn(Optional.of(produto));
		Produto produtoRetorno = produtoService.buscarPorId(Long.valueOf(1));
		assertNotNull(produtoRetorno);
		assertEquals("placa de video", produtoRetorno.getNome());
	}

	@Test
	void deveLancarExcecaoQuandoProdutoNaoExistir() {
		assertThrows(RuntimeException.class, () -> produtoService.buscarPorId(anyLong()));
	}
}