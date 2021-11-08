package com.projetointegrador.service;

import com.projetointegrador.dto.ProductResponseDto;
import com.projetointegrador.dto.ProductSellerDto;
import com.projetointegrador.entity.Product;
import com.projetointegrador.entity.ProductSeller;
import com.projetointegrador.entity.Seller;
import com.projetointegrador.repository.ProductSellerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductSellerService {

    @Autowired
    private ProductSellerPersistence productSellerPersistence;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private ProductService productService;

    public ProductSellerService() {
    }

    /**
     * @param productSellerPersistence - é esperado um parâmetro do tipo productSellerPersistence para injeção de dependência
     * @author - Grupo 5 - Tester Ana
     */
    public ProductSellerService(ProductSellerPersistence productSellerPersistence) {
        this.productSellerPersistence = productSellerPersistence;
    }

    public ProductSellerService(ProductSellerPersistence productSellerPersistence, SellerService sellerService, ProductService productService) {
        this.productSellerPersistence = productSellerPersistence;
        this.sellerService = sellerService;
        this.productService = productService;
    }

    public ProductSellerService(SellerService sellerService, ProductService productService) {
        this.sellerService = sellerService;
        this.productService = productService;
    }

    /**
     * @param productSellerDto - é esperado um objeto do tipo productSellerDto
     * @return - retorna productSellerDto cadastrado na lista
     * @author - Grupo 5 - Tester Ana
     */
    public ProductSeller insert(ProductSellerDto productSellerDto) {
        ProductSeller productSeller = convert(productSellerDto);
        if (productSeller.getProduct() != null && productSeller.getSeller() != null) {
            return productSellerPersistence.save(productSeller);
        }

        throw new RuntimeException("Vendedor ou produto não existe!");
    }

    /**
     * @param productSellerDto - é esperado um objeto do tipo productSellerDto
     * @return - retorna o productSeller com os dados de product e seller
     * @author - Grupo 5 - Tester Ana
     */
    public ProductSeller convert(ProductSellerDto productSellerDto) {
        ProductSeller productSeller = new ProductSeller();
        productSeller.setVolume(productSellerDto.getVolume());
        productSeller.setMaximumTemperature(productSellerDto.getMaximumTemperature());
        productSeller.setMinimumTemperature(productSellerDto.getMinimumTemperature());
        productSeller.setPrice(productSellerDto.getPrice());

        Product p = productService.getByIdProduct(productSellerDto.getProductId());
        Seller s = sellerService.getByIdSeller(productSellerDto.getSellerId());

        productSeller.setProduct(p);
        productSeller.setSeller(s);

        return productSeller;
    }

    /**
     * @param id
     * @return retorna a verificaçao da existencia do código
     * @author - Grupo 5
     */
    public ProductSeller getProductSeller(Long id) {
        Optional<ProductSeller> val;

        val = productSellerPersistence.findById(id);

        if (val.isPresent()) {
            return val.get();
        } else {
            throw new RuntimeException("Não existe Seller para essa busca!");
        }

    }

    /**
     * @param product faz a validacao do productSeller
     * @return retorna a verificaçao de duplicidade do código
     * @author - Grupo 5
     */
    public ProductSeller getProductSellerByProduto(Product product) {
        Optional<ProductSeller> val;

        val = productSellerPersistence.findProductSellerByProduct(product);

        if (val.isPresent()) {
            return val.get();
        } else {
            throw new RuntimeException("Não existe Seller para essa busca!");
        }

    }

    /**
     * @return retorna a lista de productResponseDto
     * @author - Grupo 5
     */
    public List<ProductResponseDto> listProduct() {
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        List<ProductSeller> productSeller = productSellerPersistence.findAll();

        for (ProductSeller item : productSeller) {

            ProductResponseDto productResponseDto = new ProductResponseDto();

            productResponseDto.setMaximumTemperature(item.getMaximumTemperature());
            productResponseDto.setMinimumTemperature(item.getMinimumTemperature());
            productResponseDto.setVolume(item.getVolume());
            productResponseDto.setProduct(item.getProduct());

            productResponseDtoList.add(productResponseDto);

        }

        return productResponseDtoList;
    }

}

