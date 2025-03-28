package uv.org.demo.Entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author david8a
 */
@Entity
@Table(name = "ventaDetalle")
public class DetalleVenta {
    @Id
    @GeneratedValue(generator = "ventadetalle_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ventadetalle_id_seq", sequenceName = "ventadetalle_id_seq", initialValue = 1, allocationSize = 1)
    @Column
    private Long id;
    @ManyToOne
    @JoinColumn(name = "idProducto")
    private Producto producto;
    @Column(precision = 12, scale = 6)
    private BigDecimal precio;
    @Column
    private String descripcion;
    @Column
    private int cantidad;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idVenta")
    private Venta venta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
    
    
}
