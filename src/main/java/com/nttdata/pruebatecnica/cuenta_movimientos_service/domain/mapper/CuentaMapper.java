package com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.entity.Cuenta;
import com.nttdata.pruebatecnica.cuenta_movimientos_service.domain.model.CuentaDTO;

@Mapper(componentModel = "spring")
public interface CuentaMapper {
    @Mappings({
    		@Mapping(source = "cuentaid", target = "cuentaid"),
            @Mapping(source = "numero_cuenta", target = "numero_cuenta"),
            @Mapping(source = "tipo_cuenta", target = "tipo_cuenta"),
            @Mapping(source = "saldo_inicial", target = "saldo_inicial"),
            @Mapping(source = "estado", target = "estado"),
            @Mapping(source = "personaid", target = "personaid")
    })
    
    CuentaDTO toCuentaDTO(Cuenta cuenta);
    Iterable<CuentaDTO> toCuentas(Iterable<Cuenta> cuenta);

    @InheritInverseConfiguration
    Cuenta toCuentaEntity(CuentaDTO cuentaDto);

}