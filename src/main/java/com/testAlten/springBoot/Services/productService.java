package com.testAlten.springBoot.Services;

import com.testAlten.springBoot.DTO.ProductDTO;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class productService {

    public void addProducts(List<ProductDTO> list_products)
    {
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:src\\main\\java\\com\\testAlten\\springBoot\\alten_dataBase.db");

            for (ProductDTO productDTO : list_products) {
                String sql = "INSERT OR IGNORE INTO Products VALUES (?,?,?,?,?,?,?,?,?,?);";
                sql = String.format(sql, "s");
                PreparedStatement prepStmt = connection.prepareStatement(sql);
                prepStmt.setInt(1, productDTO.getId());
                prepStmt.setString(2, productDTO.getCode());
                prepStmt.setString(3, productDTO.getName());
                prepStmt.setString(4, productDTO.getDescription());
                prepStmt.setInt(5, productDTO.getPrice());
                prepStmt.setInt(6, productDTO.getQuantity());
                prepStmt.setString(7, productDTO.getInventoryStatus());
                prepStmt.setString(8, productDTO.getCategory());
                prepStmt.setString(9, productDTO.getImage());
                prepStmt.setInt(10, productDTO.getRating());
                prepStmt.executeUpdate();
            }

            connection.close();
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }


    public List<ProductDTO> getProducts()
    {
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:src\\main\\java\\com\\testAlten\\springBoot\\alten_dataBase.db");
            Statement stmt = connection.createStatement();
            System.out.println("Connexion établie");

            ResultSet res = stmt.executeQuery("SELECT * FROM Products;");

            List<ProductDTO> list_product = new ArrayList<>();

            while(res.next())
            {
                ProductDTO prod = new ProductDTO(
                        res.getInt(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getInt(5),
                        res.getInt(6),
                        res.getString(7),
                        res.getString(8),
                        res.getString(9),
                        res.getInt(10));

                list_product.add(prod);
            }
            connection.close();
            System.out.println("Connexion fermée");

            return list_product;
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
        return null;
    }


    public ProductDTO getProduct(int id) {

            try
            {
                Connection connection = DriverManager.getConnection("jdbc:sqlite:src\\main\\java\\com\\testAlten\\springBoot\\alten_dataBase.db");
                System.out.println("Connexion établie");

                String sql = "SELECT * FROM Products WHERE id = ?;";
                sql = String.format(sql, "s");
                PreparedStatement prepStmt = connection.prepareStatement(sql);
                prepStmt.setInt(1, id);
                ResultSet res = prepStmt.executeQuery();

                ProductDTO prod = new ProductDTO(
                        res.getInt(1),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getInt(5),
                        res.getInt(6),
                        res.getString(7),
                        res.getString(8),
                        res.getString(9),
                        res.getInt(10));


                connection.close();
                System.out.println("Connexion fermée");

                return prod;
            }
            catch(SQLException e)
            {
                System.err.println(e.getMessage());
            }
            return null;
    }


    public void updateProduct(int id, ProductDTO product)
    {
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:src\\main\\java\\com\\testAlten\\springBoot\\alten_dataBase.db");
            System.out.println("Connexion établie");

            String sql = "UPDATE Products SET " +
                    "code = ?," +
                    "name = ?," +
                    "description = ?," +
                    "price = ?," +
                    "quantity = ?," +
                    "inventoryStatus = ?," +
                    "category = ?," +
                    "image = ?," +
                    "rating = ?" +
                    " WHERE id = ?;";
            sql = String.format(sql, "s");
            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setString(1, product.getCode());
            prepStmt.setString(2, product.getName());
            prepStmt.setString(3, product.getDescription());
            prepStmt.setInt(4, product.getPrice());
            prepStmt.setInt(5, product.getQuantity());
            prepStmt.setString(6, product.getInventoryStatus());
            prepStmt.setString(7, product.getCategory());
            prepStmt.setString(8, product.getImage());
            prepStmt.setInt(9, product.getRating());
            prepStmt.setInt(10, id);
            prepStmt.executeUpdate();


            connection.close();
            System.out.println("Connexion fermée");
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }


    public void deleteProduct(int id)
    {
        try
        {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:src\\main\\java\\com\\testAlten\\springBoot\\alten_dataBase.db");
            System.out.println("Connexion établie");

            String sql = "DELETE FROM Products " +
                    "WHERE id = ?;";
            sql = String.format(sql, "s");
            PreparedStatement prepStmt = connection.prepareStatement(sql);
            prepStmt.setInt(1, id);
            prepStmt.executeUpdate();

            connection.close();
            System.out.println("Connexion fermée");
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }
}
