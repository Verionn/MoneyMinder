package com.minder.MoneyMinder.services.mappers;

import com.minder.MoneyMinder.controllers.category.dto.CategoryResponse;
import com.minder.MoneyMinder.controllers.category.dto.CreateCategoryRequestBody;
import com.minder.MoneyMinder.models.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryResponse categoryEntityToCategoryResponse(CategoryEntity categoryEntity);

    List<CategoryResponse> listOfCategoryEntityToListOfCategoryResponse(List<CategoryEntity> category);

    CategoryEntity createCategoryRequestBodyToCategoryEntity(CreateCategoryRequestBody createCategoryRequestBody, Long userId);

}
