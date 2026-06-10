package it.itsacademy.gestione_ordini_auth.mapper;

import it.itsacademy.gestione_ordini_auth.dto.RoleDTO;
import it.itsacademy.gestione_ordini_auth.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    public RoleDTO toRoleDto(Role role);

    public Role toRole(RoleDTO roleDto);
}