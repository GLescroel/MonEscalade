package glescroel.escalade.mapper;

import glescroel.escalade.dto.PaysDto;
import glescroel.escalade.model.Pays;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PaysMapper {

    PaysMapper INSTANCE = Mappers.getMapper(PaysMapper.class);

    Pays map(PaysDto paysDto);
    PaysDto map(Pays pays);

    List<Pays> dtosToPays(List<PaysDto> paysDtos);
    List<PaysDto> paysToDtos(List<Pays> pays);
}
