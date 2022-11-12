package ec.com.pakay.repository;

import ec.com.pakay.domain.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDocumentoDAO extends JpaRepository<Documento, Integer> {
	List<Documento> findById(String id);
}
