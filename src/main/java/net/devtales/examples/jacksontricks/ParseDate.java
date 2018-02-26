package net.devtales.examples.jacksontricks;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Sometimes you need to map your dates into different formats.
 * This setup allows doing this without any additional setup.
 */

@Builder
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class ParseDate {
    private static final SimpleDateFormat DESERIALIZE_PARSER =
            new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
    private static final SimpleDateFormat SERIALIZE_FORMATTER =
            new SimpleDateFormat("yyyy-MM-dd");

    @JsonIgnore
    private Date date;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public void setDate(String date) throws ParseException {
        this.date =  DESERIALIZE_PARSER.parse(date);
    }

    @JsonIgnore
    public Date getDate() {
        return this.date;
    }

    @JsonProperty(value = "date", access = JsonProperty.Access.READ_ONLY)
    public String getAsStringDate() {
        return SERIALIZE_FORMATTER.format(this.date);
    }
}
