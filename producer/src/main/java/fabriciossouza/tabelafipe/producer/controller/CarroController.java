package fabriciossouza.tabelafipe.producer.controller;

import fabriciossouza.tabelafipe.producer.controller.request.CarroRequest;
import fabriciossouza.tabelafipe.producer.domain.carro.dto.CarroDTO;
import fabriciossouza.tabelafipe.producer.domain.carro.service.CarroService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@RequestScoped
@Path("api/rest/v1/carro")
@Produces(APPLICATION_JSON)
public class CarroController {

    private final CarroService carroService;

    @Inject
    public CarroController(CarroService carroService) {
        this.carroService = carroService;
    }

    @GET
    @Produces(APPLICATION_JSON)
    public List<CarroDTO> buscarCarros(){
        return carroService.buscarTodosCarros();
    }

    @GET
    @Path("/{codigoCarro}")
    @Produces(APPLICATION_JSON)
    public CarroDTO buscarCarroPorCodigo(@PathParam("codigoCarro")Long codigoCarro){
        return carroService.buscarCarroPorCodigo(codigoCarro);
    }

    @PUT
    @Path("/atualizar/{carroId}")
    @Consumes(APPLICATION_JSON)
    public CarroDTO atualizarCarro(@PathParam("carroId") Long carroId, CarroRequest carroRequest){
      return carroService.atualizarCarro(carroId,carroRequest);
    }
}
