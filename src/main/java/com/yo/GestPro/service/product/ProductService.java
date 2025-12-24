package com.yo.GestPro.service.product;

import com.yo.GestPro.models.client.Client;
import com.yo.GestPro.models.product.CreateProductDto;
import com.yo.GestPro.models.product.Product;
import com.yo.GestPro.repository.ClientRepository;
import com.yo.GestPro.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
}
