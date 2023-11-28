package fabriciossouza.tabelafipe.producer.domain.carro.dto;

import fabriciossouza.tabelafipe.producer.domain.marca.entity.Marca;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CarroDTO {


    private Long id;

    private String nome;

    private Long codigo;

    private String observacao;

    private Marca marca;
}
