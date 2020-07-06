package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.entity.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByUsername(String username);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	@Query(value = "{call sp_obtener_user(:rucIn)}", nativeQuery = true)
	Usuario spObtenerUser(@Param("rucIn") String rucIn);
	
	@Query(value = "{call sp_eliminar_user(:param)}", nativeQuery = true)
	int spEliminarUsuario(@Param("param") String param);
	
}
