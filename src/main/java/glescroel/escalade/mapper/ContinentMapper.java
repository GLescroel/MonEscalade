package glescroel.escalade.mapper;

import glescroel.escalade.dto.ContinentDto;
import glescroel.escalade.model.Continent;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ContinentMapper {
    ContinentMapper INSTANCE = Mappers.getMapper(ContinentMapper.class);

    Continent map(ContinentDto continentDto);
    ContinentDto map(Continent continent);

    default List<Continent> dtosToContinents(List<ContinentDto> continentDtos) {
        List<Continent> continents = new ArrayList<>();
        for (ContinentDto continentDto : continentDtos) {
            continents.add(this.map(continentDto));
        }
        return continents;
    }
    default List<ContinentDto> continentsToDto(List<Continent> continents) {
        List<ContinentDto> continentDtoList = new ArrayList<>();
        for (Continent continent : continents) {
            continentDtoList.add(this.map(continent));
        }
        return continentDtoList;
    }
}
