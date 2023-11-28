package fabriciossouza.tabelafipe.consumer.domain.fipe;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FipeModelosDTO {

    private List<FipeModeloDTO> modelos;

}
