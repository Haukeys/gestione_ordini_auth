package it.itsacademy.gestione_ordini_auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="AuthUser")
public class AuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)//
    @Column(name = "id_AuthUser")
    private UUID idUtente;//UUID standard per i id

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(name="ruoli_user",
            joinColumns = @JoinColumn(name = "id_AuthUser"),
            inverseJoinColumns = @JoinColumn(name = "idRole"))
    private Set<Role> roles;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false)
    public Boolean isAttivo=true;
    //AJOUT FAIT POUR LA GESTION DES EMAILS DE PAYEMENTs
    @Column(nullable = false,unique = true)
    private String email;

}

