package glescroel.escalade.mapper;

import glescroel.escalade.dto.TopoDto;
import glescroel.escalade.model.Topo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TopoMapper {

    TopoMapper INSTANCE = Mappers.getMapper(TopoMapper.class);

    Topo map(TopoDto topoDto);
    TopoDto map(Topo topo);

    List<Topo> dtosToTopos(List<TopoDto> topoDtos);
    List<TopoDto> toposToDtos(List<Topo> topos);
}
