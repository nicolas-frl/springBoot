package com.testAlten.springBoot.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private int id;
    private String code;
    private String name;
    private String description;
    private int price;
    private int quantity;
    private String inventoryStatus;
    private String category;
    private String image;
    private int rating;


}
