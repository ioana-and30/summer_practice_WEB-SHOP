package com.createq.curlsie.facades;

import com.createq.curlsie.dto.ProductDTO;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProductFacade {

    public List<ProductDTO> getAll() ;

    List<ProductDTO> getByCategoryId(Long categoryId, Sort sorting) ;

    ProductDTO getByProductId(Long productId) ;

    List<ProductDTO> getByProductsIds(List<Long> productsId) ;
}
