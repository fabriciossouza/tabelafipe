package fabriciossouza.tabelafipe.producer.domain.carro.service;

import fabriciossouza.tabelafipe.producer.controller.request.CarroRequest;
import fabriciossouza.tabelafipe.producer.domain.carro.repository.CarroRepository;
import fabriciossouza.tabelafipe.producer.domain.carro.dto.CarroDTO;
import fabriciossouza.tabelafipe.producer.domain.carro.entity.Carro;
import fabriciossouza.tabelafipe.producer.domain.marca.entity.Marca;
import fabriciossouza.tabelafipe.producer.infrastructure.mapper.GenericMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CarroServiceImpl implements CarroService{

    private final CarroRepository carroRepository;
    private final GenericMapper mapper;

    @Inject
    public CarroServiceImpl(CarroRepository carroRepository, GenericMapper mapper) {
        this.carroRepository = carroRepository;
        this.mapper = mapper;
    }


    @Override
    public List<CarroDTO> buscarTodosCarros(){
        List<CarroDTO> carros = new ArrayList<>();
        Iterable<Carro> carroRepositoryAll = carroRepository.findAll();
        carroRepositoryAll.forEach(carro-> carros.add(mapper.converter(carro, CarroDTO.class)));
        return carros;
    }

    @Override
    public CarroDTO buscarCarroPorCodigo(Long codigo) {
        Carro carro = carroRepository.findByCodigo(codigo)
                .orElseThrow(NotFoundException::new);

        return mapper.converter(carro, CarroDTO.class);
    }

    @Override
    public List<CarroDTO> buscarCarroPorMarca(Marca marca) {
        List<Carro> carros = carroRepository.findByMarca(marca);
        return mapper.converterCollection(carros, CarroDTO.class);
    }

    @Override
    public CarroDTO atualizarCarro(Long id,
                                   CarroRequest carroRequest) {

        Carro carro = carroRepository.findById(id)
                .orElseThrow(NotFoundException::new);

        carro.setObservacao(carroRequest.getObservacao());
        carro = carroRepository.save(carro);
        return mapper.converter(carro, CarroDTO.class);
    }
}
