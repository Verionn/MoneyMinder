package com.minder.MoneyMinder.category;

import com.minder.MoneyMinder.category.dto.CategoriesResponse;
import com.minder.MoneyMinder.category.dto.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    CategoryResponse categoryEntityToCategoryResponse(CategoryEntity categoryEntity);
    List<CategoryResponse> listOfCategoryEntityToListOfCategoryResponse(List<CategoryEntity> category);
}
