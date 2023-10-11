package com.testAlten.springBoot.Ressources;

import com.testAlten.springBoot.DTO.ProductDTO;
import com.testAlten.springBoot.Services.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class productRessource {

    @Autowired
    private productService createProduct;

    @PostMapping("/products")
    public void createProducts(@RequestBody List<ProductDTO> list_products)
    {
        createProduct.addProducts(list_products);
    }

    @GetMapping("/products")
    public List<ProductDTO> retrieveAllProducts()
    {
        return createProduct.getProducts();
    }

    @GetMapping("/product/{id}")
    public ProductDTO retrieveProductById(@PathVariable("id") int id) {
        return createProduct.getProduct(id);
    }

    @PatchMapping("/product/{id}")
    public void updateProduct(@PathVariable("id") int id, @RequestBody ProductDTO product)
    {
        createProduct.updateProduct(id, product);
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable("id") int id)
    {
        createProduct.deleteProduct(id);
    }

}
