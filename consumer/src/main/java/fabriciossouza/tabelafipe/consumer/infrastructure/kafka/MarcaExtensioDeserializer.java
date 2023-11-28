package fabriciossouza.tabelafipe.consumer.infrastructure.kafka;

import fabriciossouza.tabelafipe.consumer.client.domain.MarcaExtension;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class MarcaExtensioDeserializer extends ObjectMapperDeserializer<MarcaExtension> {

    public MarcaExtensioDeserializer() {
        super(MarcaExtension.class);
    }
}
