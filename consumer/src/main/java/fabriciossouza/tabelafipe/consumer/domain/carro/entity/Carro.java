package fabriciossouza.tabelafipe.consumer.domain.carro.entity;

import fabriciossouza.tabelafipe.consumer.domain.marca.entity.Marca;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;


@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "carro")
public class Carro {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "codigo", nullable = false)
    private Long codigo;

    @JoinColumn(name= "marca_id", nullable = false)
    @ManyToOne
    private Marca marca;

    @Column
    private String observacao;
}
