package fabriciossouza.tabelafipe.producer.domain.marca.service;

import fabriciossouza.tabelafipe.producer.domain.carro.dto.CarroDTO;
import fabriciossouza.tabelafipe.producer.domain.marca.dto.MarcaDTO;

import java.util.List;

public interface MarcaService {

    List<MarcaDTO> buscarMarcas();

    MarcaDTO buscarMarcaDtoPorCodigo(Long codigo);

    List<CarroDTO> buscarCarrosPorCodigoMarca(Long codigo);

    void sincronizarMarcas();

}
