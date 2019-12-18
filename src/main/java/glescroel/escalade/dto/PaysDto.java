package glescroel.escalade.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PaysDto {

    private Integer id;
    private String nom;
    private ContinentDto continent;

    public PaysDto() {
        //default constructor vs lombok one
    }
}
