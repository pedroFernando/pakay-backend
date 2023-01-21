package ec.com.pakay.domain.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "au_usuario")
@RequiredArgsConstructor
public class Usuario implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "us_id")
    private Integer id;

    @Column(name = "us_usuario")
    private String username;

    @Column(name = "us_clave")
    private String password;

    @Column(name = "us_email")
    private String email;

    @Column(name = "us_identificacion")
    private String identificacion;

    @Column(name = "us_nombre")
    private String nombre;

    @Column(name = "us_apellido")
    private String apellido;

    @Column(name = "us_estado")
    private String estado;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "au_perfil_usuario", joinColumns = @JoinColumn(name = "pu_id_usuario", referencedColumnName = "us_id"), inverseJoinColumns = @JoinColumn(name = "pu_id_perfil", referencedColumnName = "pe_id"))
    private List<Perfil> perfil;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        perfil.forEach(p -> {
            authorities.add(new SimpleGrantedAuthority(p.getNombre()));
        });
        return authorities;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return !this.estado.equals("I");
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return !this.estado.equals("B");
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return this.estado.equals("A");
    }
}
