/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author m marsa n j
 */

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


public interface ItemMapper {
    @Select("SELECT * FROM tb_items")
    List<Item> getAllItems();

    @Insert("INSERT INTO tb_items (name, category, price, stock) VALUES (#{name}, #{category}, #{price}, #{stock})")
    void insertItem(Item item);
    
    @Update("UPDATE tb_items SET name = #{name}, category = #{category}, price = #{price}, stock = #{stock} WHERE id_item = #{id}")
    void updateItem(Item item);

    @Delete("DELETE FROM tb_items WHERE id_item = #{id}")
    void deleteItem(int id);
}
