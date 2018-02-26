package net.devtales.examples.jacksontricks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class DeserializeSerializeDifferentlyTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void canSerialize() throws JsonProcessingException {
        DeserializeSerializeDifferently object = DeserializeSerializeDifferently.builder()
                .channelID(1)
                .bitValue(10)
                .code("This is a code")
                .shortName("This is short name")
                .build();

        String jsonString = objectMapper.writeValueAsString(object);
        assertThat(jsonString).isEqualTo(
                "{\"channelID\":1,\"bitValue\":10,\"name\":\"This is short name\",\"value\":\"This is a code\"}"
        );
    }

    @Test
    public void canDeserialize() throws IOException {
        String deserialize =
                "{\"channelID\":1,\"bitValue\":10,\"shortName\":\"This is short name\",\"code\":\"This is a code\"}";

        DeserializeSerializeDifferently object = objectMapper.readValue(
                deserialize,
                DeserializeSerializeDifferently.class);

        assertThat(object.getShortName()).isEqualTo("This is short name");
        assertThat(object.getCode()).isEqualTo("This is a code");
    }
}
