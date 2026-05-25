package BookPoint.inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BookPoint.inventario.model.Inventario;
import BookPoint.inventario.service.InventarioService;

@RestController
@RequestMapping("api/v1/inventario")
public class InventarioController {
    @Autowired
    private InventarioService inventarioService;

    @PostMapping
    public ResponseEntity<Inventario> postInventario(@RequestBody Inventario inventario) {
        try {
            return ResponseEntity.ok(inventarioService.crearInventario(inventario));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Inventario>> getInventarios() {
        try {
            return ResponseEntity.ok(inventarioService.obtenerInventarios());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<?> obtenerInventario(@PathVariable Long id) {
    //     Inventario buscado = inventarioService.findById(id).orElse(null);
    //     if (buscado == null) {
    //         return new ResponseEntity<>("Inventario con id " + id + " no existe", HttpStatus.NOT_FOUND);
    //     }
    //     return new ResponseEntity<>(buscado, HttpStatus.OK);
    // }
    @GetMapping("/stockPorBodega/{idBodega}")
        public ResponseEntity<?> getStockPorBodega(@PathVariable Long idBodega) {
        Integer total = inventarioService.getStockPorBodega(idBodega);
        return new ResponseEntity<>("Total de productos: " + total, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarInventario(@PathVariable Long id, @RequestBody Inventario inventario) {
        Inventario actualizado = inventarioService.actualizarInventario(id, inventario);
        if (actualizado == null) {
            return new ResponseEntity<>("Inventario con id " + id + " no existe", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(actualizado, HttpStatus.OK);
    }

    @PutMapping("/descontar/{idProducto}/{cantidad}")
    public ResponseEntity<?> descontarStock(@PathVariable Long idProducto, @PathVariable Integer cantidad) {
        Inventario actualizado = inventarioService.descontarStock(idProducto, cantidad);
        if (actualizado == null) {
            return new ResponseEntity<>("Stock insuficiente o producto no encontrado", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(actualizado, HttpStatus.OK);
    }
}
