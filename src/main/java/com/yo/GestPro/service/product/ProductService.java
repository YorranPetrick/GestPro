package com.yo.GestPro.service.product;

import com.yo.GestPro.models.client.Client;
import com.yo.GestPro.models.error.ErrorField;
import com.yo.GestPro.models.error.ErrorResponse;
import com.yo.GestPro.models.product.ConsumeProductDto;
import com.yo.GestPro.models.product.CreateProductDto;
import com.yo.GestPro.models.product.Product;
import com.yo.GestPro.repository.ClientRepository;
import com.yo.GestPro.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    private ClientRepository clientRepository;

    public void createProduct(String loginClient, CreateProductDto createProductDto) {
        Client client = clientRepository.findByLoginClient(loginClient).orElse(null);

        if(client != null){
            Product product = createProductDto.toEntity(client);
            productRepository.save(product);
        }
    }

    public ResponseEntity<?> consumeProduct(String idProduct, Integer quantity) {
        UUID id = UUID.fromString(idProduct);
        Product product = productRepository.findById(id).orElse(null);

        if (product != null){

            if (product.getAtualQuantity() >= quantity){

                product.setAtualQuantity(product.getAtualQuantity() - quantity);
                productRepository.save(product);

                return ResponseEntity.noContent().build();

            }else{

                List<ErrorField> errorField = List.of(new ErrorField(
                        "quantity",
                        "Insufficient product quantity in Database"));

                ErrorResponse error = ErrorResponse.standardError(
                        422,
                        "Insufficient product quantity",
                        errorField);

                return ResponseEntity.unprocessableEntity().body(error);
            }
        }

        return null;
    }
}
