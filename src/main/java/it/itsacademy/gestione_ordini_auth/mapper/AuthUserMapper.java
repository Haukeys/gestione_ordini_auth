package it.itsacademy.gestione_ordini_auth.mapper;

import it.itsacademy.gestione_ordini_auth.dto.AuthUserDTO;
import it.itsacademy.gestione_ordini_auth.dto.SignUpDTO;
import it.itsacademy.gestione_ordini_auth.dto.AuthUserUpdateDTO;
import it.itsacademy.gestione_ordini_auth.entity.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface AuthUserMapper {

    // Correspond à toUtenteSignUp : convertit le DTO d'inscription en entité de base
    @Mapping(target = "idUtente", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "isAttivo", ignore = true)
    public AuthUser toAuthUserSignUp(SignUpDTO signupDto);

    // Correspond à toUtenteDTO : convertit l'entité MySQL vers le DTO de sortie sécurisé
    public AuthUserDTO toAuthUserDTO(AuthUser authUser);

    // Correspond à toUtente : reconvertit un DTO complet vers l'entité en masquant le mot de passe
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "isAttivo", ignore = true)
    public AuthUser toAuthUser(AuthUserDTO authUserDto);

    // Correspond à toUtenteDTOList : permet de mapper des listes d'utilisateurs si nécessaire
    public List<AuthUserDTO> toAuthUserDTOList(List<AuthUser> authUsers);

    // AJOUT : Permet de gérer proprement la mise à jour (authuser_update) sur une entité existante
    @Mapping(target = "idUtente", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", ignore = true)
    public void updateAuthUserFromDTO(AuthUserUpdateDTO authUserUpdateDto, @MappingTarget AuthUser authUser);
}