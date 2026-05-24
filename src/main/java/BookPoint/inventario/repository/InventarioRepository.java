package BookPoint.inventario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import BookPoint.inventario.model.Inventario;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    
    Optional<Inventario> findByIdProducto(Long idProducto);
}
