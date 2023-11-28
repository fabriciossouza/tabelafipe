package fabriciossouza.tabelafipe.producer.domain.carro.repository;

import fabriciossouza.tabelafipe.producer.domain.carro.entity.Carro;
import fabriciossouza.tabelafipe.producer.domain.marca.entity.Marca;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface CarroRepository extends CrudRepository<Carro,Long> {

    Optional<Carro> findByCodigo(Long codigo);

    List<Carro> findByMarca(Marca marca);

}
