package ec.com.pakay.repository;

import ec.com.pakay.domain.Caja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICajaDAO extends JpaRepository<Caja, Integer> {
	List<Caja> findByIdEmpresa(Integer empresa);
	
	Boolean existsByIdEmpresaAndNombre(Integer idEmpresa, String cod);
	
	@Query("select count(1) from Caja c where c.idEmpresa = :idEmpresa and c.nombre = :nombre and c.id != :id")
	int existsByIdEmpresaAndNombreAndNotId(@Param("idEmpresa") Integer idEmpresa, @Param("nombre") String cod, @Param("id") Integer id);
	
}