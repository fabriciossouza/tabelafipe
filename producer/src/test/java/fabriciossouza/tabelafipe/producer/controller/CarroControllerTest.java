package fabriciossouza.tabelafipe.producer.controller;

import fabriciossouza.tabelafipe.producer.controller.request.CarroRequest;
import fabriciossouza.tabelafipe.producer.domain.carro.entity.Carro;
import fabriciossouza.tabelafipe.producer.domain.carro.dto.CarroDTO;
import fabriciossouza.tabelafipe.producer.domain.marca.entity.Marca;
import fabriciossouza.tabelafipe.producer.infrastructure.mapper.GenericMapper;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import static io.restassured.RestAssured.given;

@QuarkusTest
public class CarroControllerTest {

    private Marca marca;
    private CarroDTO carroDto;
    private CarroRequest carroRequest;
    private Carro carro;

    @Autowired
    GenericMapper mapper;

    @BeforeEach
    void setup(){
        marca = Marca.builder()
                .id(1L)
                .nome("teste de marca")
                .codigo(1L)
                .build();
         carro = Carro.builder()
                .id(1L)
                .marca(marca)
                .codigo(1L)
                .nome("teste de carro")
                .observacao("teste integracao")
                .build();
        carroDto = mapper.converter(carro, CarroDTO.class);
        carroRequest = CarroRequest.builder()
                .observacao("teste de observacao")
                .build();
    }

    @Test
    public void testeBuscarCarros() {
        given()
                .when().get("/carro")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    public void testeBuscarCarrosPorCodigo() {
        given()
                .when().get("/carro/"+carroDto.getCodigo())
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Test
    public void testeAtualizarObservacao() {
               given()
                .body("{\"observacao\": \"teste de update\"}")
                .header("Content-Type", "application/json")
                .when()
                .put("/carro/atualizar/"+carroDto.getId())
                .then()
                .statusCode(200);
    }

}
