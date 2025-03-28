package uv.org.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uv.org.demo.Entitys.Cliente;

/**
 *
 * @author david8a
 */
@Repository
public interface RepositoryClientes extends JpaRepository<Cliente, Long>{
    
}
