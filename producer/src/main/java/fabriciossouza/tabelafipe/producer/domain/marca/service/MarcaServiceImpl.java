package fabriciossouza.tabelafipe.producer.domain.marca.service;

import fabriciossouza.tabelafipe.producer.client.domain.MarcaExtension;
import fabriciossouza.tabelafipe.producer.client.service.MarcasFipeServiceImpl;
import fabriciossouza.tabelafipe.producer.domain.carro.dto.CarroDTO;
import fabriciossouza.tabelafipe.producer.domain.carro.service.CarroService;
import fabriciossouza.tabelafipe.producer.domain.marca.repository.MarcaRepository;
import fabriciossouza.tabelafipe.producer.domain.marca.dto.MarcaDTO;
import fabriciossouza.tabelafipe.producer.domain.marca.entity.Marca;
import fabriciossouza.tabelafipe.producer.infrastructure.kafka.KafkaResource;
import fabriciossouza.tabelafipe.producer.infrastructure.mapper.GenericMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@ApplicationScoped
public class MarcaServiceImpl implements MarcaService{

    private static final String LOG_ENVIANDO_MENSAGEM_SINCRONIZAÇÃO_DE_MARCA = "Log enviando mensagem sincronização de marca {} ";
    private static final String LOG_BUSCANDO_MARCA_POR_CODIGO = "Buscando marca por codigo {}";

    private final MarcaRepository marcaRepository;
    private final CarroService carroService;
    private final MarcasFipeServiceImpl marcasFipeService;
    private final KafkaResource kafkaResource;
    private final GenericMapper mapper;

    @Inject
    public MarcaServiceImpl(MarcaRepository marcaRepository,
                            CarroService carroService,
                            MarcasFipeServiceImpl marcasFipeService,
                            KafkaResource kafkaResource,
                            GenericMapper mapper){

        this.marcaRepository = marcaRepository;
        this.carroService = carroService;
        this.marcasFipeService = marcasFipeService;
        this.kafkaResource = kafkaResource;
        this.mapper = mapper;
    }

    @Override
    public List<MarcaDTO> buscarMarcas() {
        List<MarcaDTO> marcas = new ArrayList<>();
        Iterable<Marca> marcaIterable = marcaRepository.findAll();
        marcaIterable.forEach(marca -> marcas.add(mapper.converter(marca, MarcaDTO.class)));
        return marcas;
    }

    @Override
    public MarcaDTO buscarMarcaDtoPorCodigo(Long codigo) {
        Marca marca = buscarMarcaPorCodigo(codigo);
        return mapper.converter(marca, MarcaDTO.class);
    }

    @Override
    public List<CarroDTO> buscarCarrosPorCodigoMarca(Long codigo) {
        Marca marca = buscarMarcaPorCodigo(codigo);
        return carroService.buscarCarroPorMarca(marca);
    }

    @Override
    public void sincronizarMarcas() {
       List<MarcaExtension> marcas = marcasFipeService.sincronizarMarcas();
       marcas.forEach(marca-> {
           log.info(LOG_ENVIANDO_MENSAGEM_SINCRONIZAÇÃO_DE_MARCA, marca);
           kafkaResource.send(marca);
       });
    }

    private Marca buscarMarcaPorCodigo(Long codigo) {
        log.info(LOG_BUSCANDO_MARCA_POR_CODIGO, codigo);

        Optional<Marca> marcaRepositoryByCodigo = marcaRepository.findByCodigo(codigo);
        return marcaRepositoryByCodigo
                .orElseThrow(NotFoundException::new);
    }

}
