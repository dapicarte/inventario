package BookPoint.inventario.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import BookPoint.inventario.model.Inventario;
import BookPoint.inventario.model.ProductoDTO;
import BookPoint.inventario.repository.InventarioRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class InventarioService {
    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Inventario crearInventario(Inventario inventario){
        String url = "http://localhost:8090/api/v1/productos/" + inventario.getIdProducto();
        ProductoDTO producto = restTemplate.getForObject(url, ProductoDTO.class);
        
        if (producto != null) {
            Optional<Inventario> inventarioExistente = inventarioRepository.findByIdProducto(inventario.getIdProducto());

            if (inventarioExistente.isPresent()) {
                Inventario inv = inventarioExistente.get();
                inv.setStockDisponible(inv.getStockDisponible() + inventario.getStockDisponible());
                inv.setFechaActualizacion(LocalDate.now());
                return inventarioRepository.save(inv);
            }
            inventario.setTituloProducto(producto.getTitulo());
            inventario.setFechaActualizacion(LocalDate.now());

            return inventarioRepository.save(inventario);
        }
        return null;
    }

    public List<Inventario> obtenerInventarios() {
        return inventarioRepository.findAll();
    }
}
