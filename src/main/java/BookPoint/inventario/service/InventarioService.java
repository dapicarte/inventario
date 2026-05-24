package BookPoint.inventario.service;

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
        String url = "http://localhost:8090/api/productos/"+inventario.getIdProducto();
        ProductoDTO producto = restTemplate.getForObject(url, ProductoDTO.class);

        if(producto!=null){
            inventario.setStockDisponible(producto.);
        }
    }
}
