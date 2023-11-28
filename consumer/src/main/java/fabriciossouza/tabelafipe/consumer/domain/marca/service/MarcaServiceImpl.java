package fabriciossouza.tabelafipe.consumer.domain.marca.service;

import fabriciossouza.tabelafipe.consumer.client.domain.MarcaExtension;
import fabriciossouza.tabelafipe.consumer.client.service.MarcasFipeService;
import fabriciossouza.tabelafipe.consumer.domain.carro.service.CarroService;
import fabriciossouza.tabelafipe.consumer.domain.marca.repository.MarcaRepository;
import fabriciossouza.tabelafipe.consumer.domain.marca.entity.Marca;
import fabriciossouza.tabelafipe.consumer.infrastructure.mapper.GenericMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import java.util.Optional;


@Slf4j
@ApplicationScoped
public class MarcaServiceImpl implements MarcaService{

    private static final String LOG_SALVANDO_POST = "Log inicial salvando post: {}";
    private static final String LOG_SALVANDO_MARCA = "Log inicial salvando marca : {}";

    private final MarcaRepository marcaRepository;
    private final CarroService carroService;
    private final MarcasFipeService marcasFipeService;
    private final GenericMapper mapper;

    @Inject
    public MarcaServiceImpl(MarcaRepository marcaRepository,
                            CarroService carroService,
                            MarcasFipeService marcasFipeService,
                            GenericMapper mapper){

        this.marcaRepository = marcaRepository;
        this.carroService = carroService;
        this.marcasFipeService = marcasFipeService;
        this.mapper = mapper;
    }

    @Override
    public Iterable<Marca> buscarMarcas() {
        return marcaRepository.findAll();
    }

    @Transactional
    @Incoming("sincronizar-marcas-fipe")
    public void sincronizarMarcas(MarcaExtension marcaExtension) {
        log.info(LOG_SALVANDO_POST, marcaExtension.getNome());

        Long codigo = marcaExtension.getCodigo();

        if(!marcaJaCadastrada(codigo)){
            log.info(LOG_SALVANDO_MARCA, marcaExtension.getNome());
            var marca = marcaRepository.save(mapper.converter(marcaExtension, Marca.class));
            var fipeModelosDto = marcasFipeService.sincronizarCarrosPorMarca(codigo);
            carroService.sincronizarCarrosPorMarca(fipeModelosDto,marca);
        }
    }

    private Boolean marcaJaCadastrada(Long codigo) {
        Optional<Marca> marcaRepositoryByCodigo = marcaRepository.findByCodigo(codigo);
        return marcaRepositoryByCodigo.isPresent();
    }
}
