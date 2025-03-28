package uv.org.demo.Entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author david8a
 */
@Entity
@Table(name = "productos")
public class Producto implements Serializable{
    @Id
    @GeneratedValue(generator = "productos_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "productos_id_seq", sequenceName = "productos_id_seq", initialValue = 1, allocationSize = 1)
    @Column
    private Long id;
    @Column
    private String nombre;
    @Column(precision = 12, scale = 6)
    private BigDecimal precio;
    @JsonIgnore
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<DetalleVenta> detalleVentas;  

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public List<DetalleVenta> getDetalleVentas() {
        return detalleVentas;
    }

    public void setDetalleVentas(List<DetalleVenta> detalleVentas) {
        this.detalleVentas = detalleVentas;
    }
    
    
    
}
