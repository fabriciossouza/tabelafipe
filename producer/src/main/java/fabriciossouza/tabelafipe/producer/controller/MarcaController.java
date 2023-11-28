package fabriciossouza.tabelafipe.producer.controller;

import fabriciossouza.tabelafipe.producer.domain.carro.dto.CarroDTO;
import fabriciossouza.tabelafipe.producer.domain.marca.dto.MarcaDTO;
import fabriciossouza.tabelafipe.producer.domain.marca.service.MarcaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("api/rest/v1/marcas")
@Produces(MediaType.APPLICATION_JSON)
public class MarcaController {

    private final MarcaService marcaService;

    @Inject
    public MarcaController(MarcaService marcaService){
        this.marcaService = marcaService;
    }

    @GET
    public List<MarcaDTO> buscarMarcas(){
       return marcaService.buscarMarcas();
    }

    @GET
    @Path("/{codigoMarca}")
    public MarcaDTO buscarMarcasPorCodigo(@PathParam("codigoMarca")Long codigoMarca){
        return marcaService.buscarMarcaDtoPorCodigo(codigoMarca);
    }

    @GET
    @Path("/{codigoMarca}/carros")
    public List<CarroDTO> buscarCarrosPorCodigoMarca(@PathParam("codigoMarca")Long codigoMarca){
        return marcaService.buscarCarrosPorCodigoMarca(codigoMarca);
    }

    @GET
    @Path("/sincronizar")
    public void sincronizarMarcasFipe(){
        marcaService.sincronizarMarcas();
    }
}
