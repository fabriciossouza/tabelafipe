package fabriciossouza.tabelafipe.producer.domain.carro.entity;

import fabriciossouza.tabelafipe.producer.domain.marca.entity.Marca;
import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "carro")
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
