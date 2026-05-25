package BookPoint.inventario.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inventario")
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInventario;

    @Column(nullable = false)
    private Long idProducto;

    @Column(nullable = true)
    private Long idBodega;    

    @Column(nullable = false)
    private String tituloProducto;

    @Column(nullable = false)
    private Integer stockDisponible;

    @Column(nullable = false)
    private LocalDate fechaActualizacion;
    
}
