package ec.com.pakay.repository;

import ec.com.pakay.domain.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAuditoriaDAO extends JpaRepository<Auditoria, Long>{

	List<Auditoria> findByTablaAndIdTabla(String tabla, Long idTabla);
}
