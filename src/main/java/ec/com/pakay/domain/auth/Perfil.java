package ec.com.pakay.domain.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "au_perfil")
@Getter
@Setter
@RequiredArgsConstructor
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pe_id")
    private Long id;

    @Column(name = "pe_nombre")
    private String nombre;

    @Column(name = "pe_descripcion")
    private String descripcion;

}