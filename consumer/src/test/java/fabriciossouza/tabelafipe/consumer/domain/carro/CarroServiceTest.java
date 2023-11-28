package fabriciossouza.tabelafipe.consumer.domain.carro;

import fabriciossouza.tabelafipe.consumer.domain.carro.entity.Carro;
import fabriciossouza.tabelafipe.consumer.domain.carro.repository.CarroRepository;
import fabriciossouza.tabelafipe.consumer.domain.carro.service.CarroService;
import fabriciossouza.tabelafipe.consumer.domain.marca.entity.Marca;
import fabriciossouza.tabelafipe.consumer.domain.marca.repository.MarcaRepository;
import fabriciossouza.tabelafipe.consumer.infrastructure.mapper.GenericMapper;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@QuarkusTest
public class CarroServiceTest {
    @Inject
    CarroService carroService;
    Carro carro;

    Marca marca;
    @Autowired
    GenericMapper mapper;
    @Autowired
    CarroRepository carroRepository;
    @Autowired
    MarcaRepository marcaRepository;

    @Nested
    public class Dado_um_carro {
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
            marcaRepository.save(marca);
            carroRepository.save(carro);
        }

        @Nested
        public class Quando_buscar_todos_os_carros{
            private List<Carro> carroList;

            @BeforeEach
            void setup(){
                carroList =  carroService.buscarCarroPorMarca(marca);
            }

            @Test
            public void Entao_nao_deve_retornar_uma_lista_vazia(){
                assertFalse(carroList.isEmpty());
            }

        }

    }
}
