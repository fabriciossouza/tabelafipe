package fabriciossouza.tabelafipe.producer.domain.marca;

import fabriciossouza.tabelafipe.producer.domain.carro.entity.Carro;
import fabriciossouza.tabelafipe.producer.domain.carro.dto.CarroDTO;
import fabriciossouza.tabelafipe.producer.domain.carro.repository.CarroRepository;
import fabriciossouza.tabelafipe.producer.domain.marca.dto.MarcaDTO;
import fabriciossouza.tabelafipe.producer.domain.marca.entity.Marca;
import fabriciossouza.tabelafipe.producer.domain.marca.repository.MarcaRepository;
import fabriciossouza.tabelafipe.producer.domain.marca.service.MarcaService;
import fabriciossouza.tabelafipe.producer.infrastructure.mapper.GenericMapper;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class MarcaServiceTest {

    @Inject
    MarcaService marcaService;
    Carro carro;
    Marca marca;
    CarroDTO carroDto;
    @Autowired
    GenericMapper mapper;
    @Autowired
    MarcaRepository marcaRepository;
    @Autowired
    CarroRepository carroRepository;

    @Nested
    public class Dado_uma_marca {
        @BeforeEach
        void setup(){
            marca = Marca.builder()
                    .id(1L)
                    .nome("teste de marca")
                    .codigo(1L)
                    .build();
            marcaRepository.save(marca);
        }

        @Nested
        public class Quando_buscar_todas_as_marcas{
            private List<MarcaDTO> marcaDTOS;

            @BeforeEach
            void setup(){
                marcaDTOS =  marcaService.buscarMarcas();
            }

            @Test
            public void Entao_deve_retornar_uma_lista(){
                assertFalse(marcaDTOS.isEmpty());
            }
        }

        @Nested
        public class Quando_buscar_marca_por_codigo{
            private MarcaDTO marcaDto;

            @BeforeEach
            void setup(){
                marcaDto =  marcaService.buscarMarcaDtoPorCodigo(1L);
            }

            @Test
            public void Entao_nao_deve_retornar_uma_lista_vazia(){
                assertEquals(marcaDto.codigo,1L);
            }
        }

        @Nested
        public class Dado_um_carroDto {
            @Nested
            public class Quando_buscar_carros_por_codigo_da_marca{
                private MarcaDTO marcaDto;
                private List<CarroDTO> carros;


                @BeforeEach
                void setup(){
                    carro = Carro.builder()
                            .id(1L)
                            .marca(marca)
                            .codigo(1L)
                            .nome("teste de carro")
                            .observacao("teste integracao")
                            .build();

                    carroRepository.save(carro);
                    carros =  marcaService.buscarCarrosPorCodigoMarca(1L);
                }

                @Test
                public void Entao_nao_deve_retornar_uma_lista_de_carros(){
                    assertNotNull(carros);
                }
            }
        }

    }

}
