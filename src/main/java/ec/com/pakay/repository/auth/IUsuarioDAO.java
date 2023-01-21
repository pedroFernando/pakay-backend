package ec.com.pakay.repository.auth;

import ec.com.pakay.domain.auth.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUsuarioDAO extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByUsername(String username);

}
