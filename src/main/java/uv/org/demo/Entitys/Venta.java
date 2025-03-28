package uv.org.demo.Entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author david8a
 */
@Entity
@Table(name = "venta")
public class Venta {
    @Id
    @GeneratedValue(generator = "venta_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "venta_id_seq", sequenceName = "venta_id_seq", initialValue = 1, allocationSize = 1)
    @Column
    private Long id;
    @Column
    private LocalDateTime fecha;
    @Column(precision = 12, scale = 6)
    private BigDecimal monto;
    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<DetalleVenta> listaDetalleVentas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }


    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetalleVenta> getListaDetalleVentas() {
        return listaDetalleVentas;
    }

    public void setListaDetalleVentas(List<DetalleVenta> listaDetalleVentas) {
        this.listaDetalleVentas = listaDetalleVentas;
    }

    
}
