package uv.org.demo.Repository;

import uv.org.demo.Entitys.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author david8a
 */
@Repository 
public interface RepositoryVentas extends JpaRepository<Venta, Long>{
    
}
