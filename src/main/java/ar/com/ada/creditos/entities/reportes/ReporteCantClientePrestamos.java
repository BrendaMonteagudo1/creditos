package ar.com.ada.creditos.entities.reportes;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class ReporteCantClientePrestamos {

    @Column(name="cant_clientes")
    public int cantClientes;

    @Column(name="cant_prestamos")
    public int cantPrestamos;
    
}
