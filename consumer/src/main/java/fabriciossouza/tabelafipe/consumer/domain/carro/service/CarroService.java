package fabriciossouza.tabelafipe.consumer.domain.carro.service;

import fabriciossouza.tabelafipe.consumer.domain.carro.entity.Carro;
import fabriciossouza.tabelafipe.consumer.domain.fipe.FipeModelosDTO;
import fabriciossouza.tabelafipe.consumer.domain.marca.entity.Marca;

import java.util.List;

public interface CarroService {

    List<Carro> buscarCarroPorMarca(Marca marca);

    void sincronizarCarrosPorMarca(FipeModelosDTO fipeModelosDto, Marca marca);
}
