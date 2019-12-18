package glescroel.escalade.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ContinentDto {
    private Integer id;
    private String nom;

    public ContinentDto() {
        //default constructor vs lombok
    }
}
