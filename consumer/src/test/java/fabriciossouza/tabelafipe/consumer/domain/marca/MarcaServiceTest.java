package fabriciossouza.tabelafipe.consumer.domain.marca;

import fabriciossouza.tabelafipe.consumer.domain.marca.entity.Marca;
import fabriciossouza.tabelafipe.consumer.domain.marca.repository.MarcaRepository;
import fabriciossouza.tabelafipe.consumer.domain.marca.service.MarcaService;
import fabriciossouza.tabelafipe.consumer.infrastructure.mapper.GenericMapper;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@QuarkusTest
public class MarcaServiceTest {

    @Inject
    MarcaService marcaService;
    Marca marca;
    @Autowired
    GenericMapper mapper;
    @Autowired
    MarcaRepository marcaRepository;


    @Nested
    public class Dado_uma_marca {
        @BeforeEach
        void setup() {
            marca = Marca.builder()
                    .id(1L)
                    .nome("teste de marca")
                    .codigo(1L)
                    .build();
            marcaRepository.save(marca);
        }

        @Nested
        public class Quando_buscar_todas_as_marcas {
            private List<Marca> marcaList;

            @BeforeEach
            void setup() {
                marcaList = new ArrayList<Marca>();
                marcaService.buscarMarcas().forEach(e -> {
                    marcaList.add(e);
                });
            }

            @Test
            public void Entao_deve_retornar_uma_lista() {
                assertFalse(marcaList.isEmpty());
            }
        }

    }

}
