package fabriciossouza.tabelafipe.producer.domain.carro;

import fabriciossouza.tabelafipe.producer.domain.carro.dto.CarroDTO;
import fabriciossouza.tabelafipe.producer.domain.carro.entity.Carro;
import fabriciossouza.tabelafipe.producer.domain.carro.repository.CarroRepository;
import fabriciossouza.tabelafipe.producer.domain.carro.service.CarroService;
import fabriciossouza.tabelafipe.producer.domain.marca.entity.Marca;
import fabriciossouza.tabelafipe.producer.infrastructure.mapper.GenericMapper;
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
    CarroDTO carroDto;
    @Autowired
    GenericMapper mapper;
    @Autowired
    CarroRepository carroRepository;

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
            carroDto = mapper.converter(carro, CarroDTO.class);
            carroRepository.save(carro);
        }

        @Nested
        public class Quando_buscar_todos_os_carros{
            private List<CarroDTO> carroDTOList;

            @BeforeEach
            void setup(){
                carroDTOList =  carroService.buscarTodosCarros();
            }

            @Test
            public void Entao_nao_deve_retornar_uma_lista_vazia(){
                assertFalse(carroDTOList.isEmpty());
            }

        }

    }
}
