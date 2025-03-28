/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/SpringFramework/RestController.java to edit this template
 */
package uv.org.demo.Controller;

import uv.org.demo.Repository.RepositoryClientes;
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
import uv.org.demo.Entitys.Cliente;

/**
 *
 * @author david8a
 */
@RestController
@RequestMapping("/clientes")
public class ControllerClientes {

    @Autowired
    RepositoryClientes repositoryClientes;

    @GetMapping()
    public List<Cliente> list() {
        return repositoryClientes.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> get(@PathVariable Long id) {
        Optional<Cliente> resCli = repositoryClientes.findById(id);
        if (resCli.isPresent()) {
            return ResponseEntity.ok(resCli.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> put(@PathVariable Long id, @RequestBody Cliente input) {
        Optional<Cliente> resCli = repositoryClientes.findById(id);
        if (resCli.isPresent()) {
            Cliente cliToEdit = resCli.get();
            cliToEdit.setNombre(input.getNombre());

            Cliente cliEdited = repositoryClientes.save(cliToEdit);
            return ResponseEntity.ok(cliEdited);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Cliente> post(@RequestBody Cliente input) {
        Cliente newCli = repositoryClientes.save(input);
        return ResponseEntity.ok(newCli);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> delete(@PathVariable Long id) {
        Optional<Cliente> resCli = repositoryClientes.findById(id);
        if (resCli.isPresent()) {
            Cliente cliDeleted = resCli.get();
            repositoryClientes.delete(cliDeleted);
            return ResponseEntity.ok(cliDeleted);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error message")
    public void handleError() {
    }

}
