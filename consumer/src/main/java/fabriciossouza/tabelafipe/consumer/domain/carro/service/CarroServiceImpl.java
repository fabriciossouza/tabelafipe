package fabriciossouza.tabelafipe.consumer.domain.carro.service;

import fabriciossouza.tabelafipe.consumer.domain.carro.repository.CarroRepository;
import fabriciossouza.tabelafipe.consumer.domain.carro.entity.Carro;
import fabriciossouza.tabelafipe.consumer.domain.fipe.FipeModeloDTO;
import fabriciossouza.tabelafipe.consumer.domain.fipe.FipeModelosDTO;
import fabriciossouza.tabelafipe.consumer.domain.marca.entity.Marca;
import fabriciossouza.tabelafipe.consumer.infrastructure.mapper.GenericMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;


@Slf4j
@ApplicationScoped
public class CarroServiceImpl implements CarroService{

    private static final String LOG_SINCRONIZANDO_CARRO_DA_MARCA = "sincronizando carro : {} da marca : {} ";

    private final CarroRepository carroRepository;
    private final GenericMapper mapper;

    @Inject
    public CarroServiceImpl(CarroRepository carroRepository, GenericMapper mapper) {
        this.carroRepository = carroRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Carro> buscarCarroPorMarca(Marca marca) {
        return carroRepository.findByMarca(marca);
    }

    @Override
    public void sincronizarCarrosPorMarca(FipeModelosDTO fipeModelosDto, Marca marca) {
        List<FipeModeloDTO> modelos = fipeModelosDto.getModelos();

        modelos.forEach(modelo -> {
            long codigo = modelo.getCodigo().longValue();

            if(!carroJaCadastrado(codigo)){
                log.info(LOG_SINCRONIZANDO_CARRO_DA_MARCA, modelo.getNome(),  marca.getNome());
                carroRepository.save(Carro.builder()
                                .nome(modelo.getNome())
                                .codigo(codigo)
                                .marca(marca)
                        .build());
            }
        });
    }

    private Boolean carroJaCadastrado(Long codigoCarro){
        Optional<Carro> carroRepositoryByCodigo = carroRepository.findByCodigo(codigoCarro);
        return carroRepositoryByCodigo.isPresent();
    }
}
