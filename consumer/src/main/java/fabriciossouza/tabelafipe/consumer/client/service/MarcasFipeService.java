package fabriciossouza.tabelafipe.consumer.client.service;

import fabriciossouza.tabelafipe.consumer.domain.fipe.FipeModelosDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


@RegisterRestClient(configKey="fipe-api")
public interface MarcasFipeService {

    @GET
    @Path("/carros/marcas/{codigoMarca}/modelos")
    FipeModelosDTO sincronizarCarrosPorMarca(@PathParam("codigoMarca") Long codigoMarca);

}
