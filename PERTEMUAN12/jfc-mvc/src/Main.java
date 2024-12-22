/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import model.MyBatisUtil; 
import model.UserMapper;
import org.apache.ibatis.session.SqlSession; 
import view.UserPdf;
import view.UserView;

import controller.UserController;

/**
 *
 * @author m marsa n j
 */
public class Main {
    public static void main(String[] args) {
        SqlSession session = MyBatisUtil.getSqlSession(); 
        UserMapper mapper = session.getMapper(UserMapper.class);
        UserPdf pdf = new UserPdf();
        
        UserView view = new UserView(); 
        new UserController(view, mapper, pdf);
        
        view.setVisible(true);
    }
}
