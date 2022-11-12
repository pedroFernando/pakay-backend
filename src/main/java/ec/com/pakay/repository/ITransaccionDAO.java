package ec.com.pakay.repository;

import ec.com.pakay.domain.Documento;
import ec.com.pakay.domain.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ITransaccionDAO extends JpaRepository<Transaccion, Integer> {
	
	List<Transaccion> findByIdEmpresa(Integer empresa);
	
	List<Transaccion> findByIdEmpresaAndDocumento(Integer empresa, Documento documento);
	
	@Query(value = "CALL ConsultaTransaccion(:idEmpresa, :idSocio, :codDoc, :fechaDesde, :fechaHasta)", nativeQuery = true)
	List<Object[]> filtrar(@Param("idEmpresa") Integer idEmpresa, @Param("idSocio") Integer idSocio,
			@Param("codDoc") String codDoc, @Param("fechaDesde") Date fechaDesde, @Param("fechaHasta") Date fechaHasta);
	
	@Query(value = "CALL ConsultaSocio(:idSocio)", nativeQuery = true)
	List<Object[]> reporteSocio(@Param("idSocio") Integer idSocio);
	
	@Query(value = "CALL ConsultaEmpresa(:idEmpresa)", nativeQuery = true)
	List<Object[]> reporteEmpresa(@Param("idEmpresa") Integer idEmpresa);
	
}