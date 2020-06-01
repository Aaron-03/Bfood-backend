package com.app.repository;




import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Solicitud;

@Repository
public interface SolicitudRepository extends CrudRepository<Solicitud, Integer> {
	
}
