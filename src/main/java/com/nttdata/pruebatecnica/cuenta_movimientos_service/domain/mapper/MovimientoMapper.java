package com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.entity.Movimiento;
import com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.model.MovimientoDTO;

@Mapper(componentModel = "spring")
public interface MovimientoMapper {
    @Mappings({
    		@Mapping(source = "movimientoid", target = "movimientoid"),
            @Mapping(source = "fecha", target = "fecha"),
            @Mapping(source = "tipo_movimiento", target = "tipo_movimiento"),
            @Mapping(source = "valor", target = "valor"),
            @Mapping(source = "saldo", target = "saldo")
    })
    
    MovimientoDTO toMovimientoDTO(Movimiento movimiento);
    Iterable<MovimientoDTO> toMovimientos(Iterable<Movimiento> movimiento);

    @InheritInverseConfiguration
    Movimiento toMovimientoEntity(MovimientoDTO movimientoDto);

}