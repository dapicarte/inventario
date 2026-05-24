package BookPoint.inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import BookPoint.inventario.model.Inventario;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    
}
