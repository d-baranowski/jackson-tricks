package net.devtales.examples.jacksontricks;

/*
 * In this example we change how object is serialized and deserialized using only
 * annotations. Use of WRITE_ONLY allows to specify that this annotation only
 * applies when deserializing from json. Using an annotation on a getter
 * also signifies that it only applies when serializing.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class DeserializeSerializeDifferently {
    private Integer channelID;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    //Deserialize from json as 'code'
    private String code;
    //Deserialize from json as 'shortName'
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String shortName;
    private Integer bitValue;

    //Serialize to json as 'name'
    @JsonProperty(value = "name", access = JsonProperty.Access.READ_ONLY)
    public String getName() {
        return this.shortName;
    }

    //Serialize to json as 'value'
    @JsonProperty(value = "value", access = JsonProperty.Access.READ_ONLY)
    public String getValue() {
        return this.code;
    }
}
