package fabriciossouza.tabelafipe.consumer.domain.marca.service;

import fabriciossouza.tabelafipe.consumer.client.domain.MarcaExtension;
import fabriciossouza.tabelafipe.consumer.domain.marca.entity.Marca;

public interface MarcaService {

    Iterable<Marca> buscarMarcas();

    void sincronizarMarcas(MarcaExtension marcaExtension);

}
