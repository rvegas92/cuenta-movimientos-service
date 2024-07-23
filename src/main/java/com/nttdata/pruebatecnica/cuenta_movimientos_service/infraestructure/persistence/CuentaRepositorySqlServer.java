package com.nttdata.pruebatecnica.cuenta_movimientos_service.infraestructure.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.entity.Cuenta;

public interface CuentaRepositorySqlServer extends JpaRepository<Cuenta, Long>{
	
	@Query(nativeQuery = true, value = "select c.* from cuenta c where c.numero_cuenta = ?1 or c.tipo_cuenta = ?1")
    List<Cuenta> findBySearchTerm(@Param("search") String search);
}
