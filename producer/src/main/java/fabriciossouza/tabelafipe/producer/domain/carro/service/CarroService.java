package fabriciossouza.tabelafipe.producer.domain.carro.service;

import fabriciossouza.tabelafipe.producer.controller.request.CarroRequest;
import fabriciossouza.tabelafipe.producer.domain.carro.dto.CarroDTO;
import fabriciossouza.tabelafipe.producer.domain.marca.entity.Marca;

import java.util.List;

public interface CarroService {

    List<CarroDTO> buscarTodosCarros();

    CarroDTO buscarCarroPorCodigo(Long codigo);

    List<CarroDTO> buscarCarroPorMarca(Marca marca);

    CarroDTO atualizarCarro(Long id, CarroRequest carroRequest);
}
