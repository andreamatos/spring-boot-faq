package br.edu.cruzeirodosul.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PageRequestDTO<T> {

    private List<T> content;

    @JsonProperty(value = "last")
    private Boolean last;

    @JsonProperty(value = "totalPages")
    private Integer totalPages;

    @JsonProperty(value = "totalElements")
    private Integer totalElements;

    @JsonProperty(value = "numberOfElements")
    private Integer numberOfElements;

    @JsonProperty(value = "first")
    private Boolean first;

    @JsonProperty(value = "size")
    private Integer size;

    @JsonProperty(value = "number")
    private Integer number;

    public static <T> PageRequestDTO<T> of(Integer pgNum, Integer pgSize) {
        PageRequestDTO<T> x = new PageRequestDTO<>();
        x.setNumber(pgNum);
        x.setSize(pgSize);
        return x;
    }
}
