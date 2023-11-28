package fabriciossouza.tabelafipe.producer.domain.marca.repository;

import fabriciossouza.tabelafipe.producer.domain.marca.entity.Marca;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MarcaRepository extends CrudRepository<Marca,Long> {

    Optional<Marca> findByCodigo(Long codigo);

}
