package fabriciossouza.tabelafipe.producer.infrastructure.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.modelmapper.Conditions.isNotNull;

@Component
public class GenericMapper {

    @Autowired
    private ModelMapper mapper;


    public  <T> List<T> converterCollection(List<?> objects, Class<T> clazz) {
        return objects.stream()
                .map(obj -> converter(obj, clazz))
                .collect(toList());
    }

    public <T> T converter(Object object, Class<T> clazz) {
        return mapper().map(object, clazz);
    }

    private  ModelMapper mapper() {
        if( mapper == null ){
            mapper = new ModelMapper();
            mapper.getConfiguration().setPropertyCondition(isNotNull());
        }

        return mapper;
    }

}