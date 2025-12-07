package com.example.demo.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Usuario;

@Repository
public interface UsuarioRepository {

	void save(Usuario entity);

	Optional<Usuario> findById(Long id);
	
}
