package com.nttdata.pruebatecnica.cuenta_movimientos_service.infraestructure.persistence;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.entity.Movimiento;

public interface MovimientoRepositorySqlServer extends JpaRepository<Movimiento, Long>{
	
	@Query(nativeQuery = true, value = "select top 1 m.* from movimiento m where m.cuentaid = ?1 order by fecha, movimientoid desc")
    Optional<Movimiento> findByIdCuentaFecha(@Param("idcuenta") Long idcuenta);
}
