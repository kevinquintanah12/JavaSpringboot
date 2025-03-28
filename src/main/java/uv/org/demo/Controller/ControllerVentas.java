package uv.org.demo.Controller;

import java.time.LocalDateTime;
import uv.org.demo.Entitys.Cliente;
import uv.org.demo.Entitys.Venta;
import uv.org.demo.Repository.RepositoryClientes;
import uv.org.demo.Repository.RepositoryVentas;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import uv.org.demo.DTOVenta;
import uv.org.demo.services.ServiceVentas;

/**
 *
 * @author david8a
 */
@RestController
@RequestMapping("/ventas")
public class ControllerVentas {
    
    private ServiceVentas serviceVentas = new ServiceVentas();
    
    @Autowired
    RepositoryVentas repositoryVentas;
    
    @Autowired
    RepositoryClientes repositoryClientes; 
    
    @GetMapping()
    public List<DTOVenta> list() {
        List<DTOVenta> listDTOVentas = serviceVentas.fillVentas();
        return listDTOVentas;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Venta> get(@PathVariable Long id) {
        Optional<Venta> resVen = repositoryVentas.findById(id);
        if (resVen.isPresent()) {
            return ResponseEntity.ok(resVen.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Venta> put(@PathVariable Long id, @RequestBody Venta input) {
        Optional<Venta> resVen = repositoryVentas.findById(id);
        if (resVen.isPresent()) {
            Venta venToEdit = resVen.get();
            venToEdit.setFecha(input.getFecha());
            venToEdit.setMonto(input.getMonto());
            
            Venta venEdited = repositoryVentas.save(venToEdit);
            return ResponseEntity.ok(venEdited);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<DTOVenta> post(@RequestBody DTOVenta dTOVenta) {
        Optional<Cliente> clienteOpt = repositoryClientes.findById(dTOVenta.getIdCliente());
        Cliente cliente = null;
        if (!clienteOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            cliente = clienteOpt.get();
        }
        Venta venta = new Venta();
        venta.setFecha(dTOVenta.getFecha());
        venta.setCliente(cliente);
        venta.setMonto(dTOVenta.getMonto());
        venta.setListaDetalleVentas(dTOVenta.getListaDetalleVentas());

        Venta ventaNew = repositoryVentas.save(venta);
        dTOVenta.setId(ventaNew.getId());
        return ResponseEntity.ok(dTOVenta);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Venta> delete(@PathVariable Long id) {
        Optional<Venta> resVen = repositoryVentas.findById(id);
        if (resVen.isPresent()) {
            Venta venDeleted = resVen.get();
            repositoryVentas.delete(venDeleted);
            return ResponseEntity.ok(venDeleted);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public void handleError() {
    }
    
}
