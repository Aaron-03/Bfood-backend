package com.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Rol;
import com.app.enums.RolNombre;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
