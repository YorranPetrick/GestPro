package com.yo.GestPro.utils.mapstruct;

import com.yo.GestPro.models.product.CreateProductDto;
import com.yo.GestPro.models.product.Product;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product updateProductFromDto(@MappingTarget Product product, CreateProductDto createProductDto);

}
