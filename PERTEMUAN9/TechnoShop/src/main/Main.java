/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author m marsa n j
 */
import model.MyBatisUtil; 
import org.apache.ibatis.session.SqlSession; 
import view.ProductsView;
import controller.ProductsController;
import model.ProductMapper;

public class Main {
    public static void main(String[] args) {
        SqlSession session = MyBatisUtil.getSqlSession(); 
        ProductMapper mapper = session.getMapper(ProductMapper.class);
        
        ProductsView view = new ProductsView(); 
        new ProductsController(view, mapper);
        
        view.setVisible(true);
    }
}

