package com.minder.MoneyMinder.list;

import com.minder.MoneyMinder.list.dto.ListResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ListMapper {
    ListMapper INSTANCE = Mappers.getMapper(ListMapper.class);

    List<ListResponse> listOfListEntityToListOfListResponse(List<ListEntity> list);

    ListResponse listEntityToListResponse(ListEntity listEntity);
}
