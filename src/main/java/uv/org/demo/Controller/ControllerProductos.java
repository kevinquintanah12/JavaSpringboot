package uv.org.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import uv.org.demo.Entitys.Producto;
import uv.org.demo.Repository.RepositoryProductos;

@RestController
@RequestMapping("/productos")
public class ControllerProductos {

    @Autowired
    private RepositoryProductos repositoryProductos;

    @GetMapping()
    public List<Producto> list() {
        return repositoryProductos.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> get(@PathVariable Long id) {
        Optional<Producto> resPro = repositoryProductos.findById(id);
        if (resPro.isPresent()) {
            return ResponseEntity.ok(resPro.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Nuevo endpoint de búsqueda
    @GetMapping("/buscar")
    public ResponseEntity<List<Producto>> buscar(@RequestParam("nombre") String nombre) {
        List<Producto> resultados = repositoryProductos.findByNombreContainingIgnoreCase(nombre);
        if(resultados.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resultados);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> put(@PathVariable Long id, @RequestBody Producto input) {
        Optional<Producto> resPro = repositoryProductos.findById(id);
        if (resPro.isPresent()) {
            Producto proToEdit = resPro.get();
            proToEdit.setNombre(input.getNombre());
            proToEdit.setPrecio(input.getPrecio());

            Producto proEdited = repositoryProductos.save(proToEdit);
            return ResponseEntity.ok(proEdited);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Producto> post(@RequestBody Producto input) {
        Producto newPro = repositoryProductos.save(input);
        return ResponseEntity.ok(newPro);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Producto> delete(@PathVariable Long id) {
        Optional<Producto> resPro = repositoryProductos.findById(id);
        if (resPro.isPresent()) {
            Producto proDeleted = resPro.get();
            repositoryProductos.delete(proDeleted);
            return ResponseEntity.ok(proDeleted);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public void handleError() {
    }
}
