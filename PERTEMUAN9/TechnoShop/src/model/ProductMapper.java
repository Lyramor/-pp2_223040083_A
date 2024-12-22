/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;


/**
 *
 * @author m marsa n j
 */
import org.apache.ibatis.annotations.*;
import java.util.List;

public interface ProductMapper {
    @Select("SELECT * FROM products")
    List<Product> getAllProducts();

    @Insert("INSERT INTO products (name, brand, price, stock) VALUES (#{name}, #{brand}, #{price}, #{stock})")
    void insertProduct(Product product);

    @Update("UPDATE products SET name = #{name}, brand = #{brand}, price = #{price}, stock = #{stock} WHERE id = #{id}")
    void updateProduct(Product product);

    @Delete("DELETE FROM products WHERE id = #{id}")
    void deleteProduct(int id);
}
