package fabriciossouza.tabelafipe.producer.controller;

import fabriciossouza.tabelafipe.producer.domain.carro.dto.CarroDTO;
import fabriciossouza.tabelafipe.producer.domain.marca.entity.Marca;
import fabriciossouza.tabelafipe.producer.domain.marca.dto.MarcaDTO;
import fabriciossouza.tabelafipe.producer.infrastructure.mapper.GenericMapper;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class MarcaControllerTest {
    private Marca marca;
    private List<CarroDTO> carroDTOList;
    private MarcaDTO marcaDto;

    @Autowired
    GenericMapper mapper;

    @BeforeEach
    void setup(){
        carroDTOList = new ArrayList<>();
        marca = Marca.builder()
                .id(1L)
                .nome("teste de marca")
                .codigo(1L)
                .build();
        carroDTOList.add(CarroDTO.builder()
                .id(1L)
                .marca(marca)
                .codigo(1L)
                .nome("teste de carro")
                .observacao("teste integracao")
                .build());
        marcaDto = mapper.converter(marca, MarcaDTO.class);
    }


    @Test
    public void testeBuscarMarcas() {
        given()
                .when().get("/marcas")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    public void testeBuscarMarcaPorId() {
        given()
                .when().get("/marcas/"+marcaDto.getId())
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    public void testeBuscarCarrosPorMarca() {
        given()
                .when().get("/marcas/"+marcaDto.getId()+"/carros")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    public void testeSincronizarMarca() {
        given()
                .when().get("/marcas/sincronizar")
                .then()
                .statusCode(204);
    }
}
