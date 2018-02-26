package net.devtales.examples.jacksontricks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Date;

public class ParseDateTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void canDeserialize() throws IOException {
        String deserialize =
                "{\"date\": \"Thu Jun 18 20:56:02 EDT 2009\"}";

        ParseDate object = objectMapper.readValue(
                deserialize,
                ParseDate.class);

        assertThat(object.getDate()).isEqualTo(new Date(1245372962000L));
    }

    @Test
    public void canSerialize() throws JsonProcessingException {
        ParseDate object = ParseDate.builder()
                .date(new Date(1245372962000L))
                .build();

        String jsonString = objectMapper.writeValueAsString(object);
        assertThat(jsonString).isEqualTo(
                "{\"date\":\"2009-06-19\"}"
        );
    }
}
