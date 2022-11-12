package ec.com.pakay.repository;

import ec.com.pakay.domain.DocumentoEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IDocumentoEmpresaDAO extends JpaRepository<DocumentoEmpresa, Integer> {
	
	@Query(value = "CALL DocumentoConsultaSiguiente(:idEmpresa, :codDocumento)", nativeQuery = true)
	String consultaSiguiente(@Param("idEmpresa") Integer idEmpresa, @Param("codDocumento") String codDocumento);
	
}
