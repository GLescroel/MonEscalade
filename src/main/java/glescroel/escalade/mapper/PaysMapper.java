package glescroel.escalade.mapper;

import glescroel.escalade.dto.PaysDto;
import glescroel.escalade.model.Pays;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(uses = ContinentMapper.class)
public interface PaysMapper {

    PaysMapper INSTANCE = Mappers.getMapper(PaysMapper.class);

    Pays map(PaysDto paysDto);
    PaysDto map(Pays pays);

    default List<Pays> dtosToPays(List<PaysDto> paysDtos) {
        List<Pays> paysList = new ArrayList<>();
        for (PaysDto paysDto : paysDtos) {
            paysList.add(this.map(paysDto));
        }
        return paysList;
    }

    default List<PaysDto> paysToDtos(List<Pays> paysList) {
        List<PaysDto> paysDtoList = new ArrayList<>();
        for (Pays pays : paysList) {
            paysDtoList.add(this.map(pays));
        }
        return paysDtoList;
    }
}
