package fabriciossouza.tabelafipe.consumer.client.service;

import fabriciossouza.tabelafipe.consumer.domain.fipe.FipeModelosDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class MarcasFipeServiceImpl {

    @Inject
    @RestClient
    MarcasFipeService marcasFipeService;

    @GET
    @Path("/carros/marcas/{codigoMarca}/modelos")
    public FipeModelosDTO sincronizarCarrosPorMarca(Long codigoMarca) {
        return marcasFipeService.sincronizarCarrosPorMarca(codigoMarca);
    }
}
