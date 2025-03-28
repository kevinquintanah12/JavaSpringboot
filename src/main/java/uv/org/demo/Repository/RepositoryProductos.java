package uv.org.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uv.org.demo.Entitys.Producto;

@Repository
public interface RepositoryProductos extends JpaRepository<Producto, Long>{
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
}
//mapeo de productos