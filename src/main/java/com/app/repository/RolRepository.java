package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Rol;


@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

}
