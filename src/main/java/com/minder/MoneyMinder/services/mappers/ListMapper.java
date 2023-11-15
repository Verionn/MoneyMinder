package com.minder.MoneyMinder.services.mappers;

import com.minder.MoneyMinder.controllers.list.dto.CreateListRequestBody;
import com.minder.MoneyMinder.controllers.list.dto.ListResponse;
import com.minder.MoneyMinder.models.ListEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ListMapper {
    ListMapper INSTANCE = Mappers.getMapper(ListMapper.class);

    List<ListResponse> listOfListEntityToListOfListResponse(List<ListEntity> list);

    ListResponse listEntityToListResponse(ListEntity listEntity);

    ListEntity createListRequestBodyToListEntity(CreateListRequestBody createListRequestBody);
}
