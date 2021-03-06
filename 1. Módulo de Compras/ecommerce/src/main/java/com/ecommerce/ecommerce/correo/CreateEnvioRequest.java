package com.ecommerce.ecommerce.correo;

public class CreateEnvioRequest {
	
    private String descripcion;

    private String dniDestinatario;
    
    private String idOrden;
    
    private String vendedor;
    
	public CreateEnvioRequest() {
		super();
	}

	public CreateEnvioRequest(String descripcion, String dniDestinatario, String idOrden, String vendedor) {
		super();
		this.descripcion = descripcion;
		this.dniDestinatario = dniDestinatario;
		this.idOrden = idOrden;
		this.vendedor = vendedor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDniDestinatario() {
		return dniDestinatario;
	}

	public void setDniDestinatario(String dniDestinatario) {
		this.dniDestinatario = dniDestinatario;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

	public String getIdOrden() {
		return idOrden;
	}

	public void setIdOrden(String idOrden) {
		this.idOrden = idOrden;
	}

	@Override
	public String toString() {
		return "CreateEnvioRequest [descripcion=" + descripcion + ", dniDestinatario=" + dniDestinatario + ", vendedor="
				+ vendedor + ", idOrden=" + idOrden + "]";
	}

}