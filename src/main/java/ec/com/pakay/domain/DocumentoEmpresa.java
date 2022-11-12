package ec.com.pakay.domain;

import javax.persistence.*;

@Entity
@Table(name = "documento_empresa")
public class DocumentoEmpresa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_documento_empresa")
	private Integer id;
	
	@Column(name = "id_empresa")
	private Integer idEmpresa;
	
	@ManyToOne
	@JoinColumn(name = "id_documento")
	private Documento documento;

	@Column(name = "secuencial")
	private Integer secuencial;

	public DocumentoEmpresa() {
	}

	public DocumentoEmpresa(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public Integer getSecuencial() {
		return secuencial;
	}

	public void setSecuencial(Integer secuencial) {
		this.secuencial = secuencial;
	}

}