package glescroel.escalade.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaysDto {

    private Integer id;
    private String nom;
    private ContinentDto continent;
}
