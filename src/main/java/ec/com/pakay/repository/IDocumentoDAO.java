package ec.com.pakay.repository;

import ec.com.pakay.domain.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDocumentoDAO extends JpaRepository<Documento, Integer> {
	List<Documento> findById(String id);

	@Query(value = "CALL DocumentoConsultaSiguiente(:codDocumento)", nativeQuery = true)
	String consultaSiguiente(@Param("codDocumento") String codDocumento);

}
