/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author m marsa n j
 */
import model.MyBatisUtil;
import model.ItemMapper;
import org.apache.ibatis.session.SqlSession;
import view.TechnoShopView;
import controller.TechnoShopController;

public class Main {
    public static void main(String[] args) {
        // Mendapatkan SqlSession dari MyBatisUtil
        SqlSession session = MyBatisUtil.getSqlSession();

        // Mendapatkan mapper dari session
        ItemMapper mapper = session.getMapper(ItemMapper.class);

        // Membuat instance dari TechnoShopView
        TechnoShopView view = new TechnoShopView();

        // Membuat controller dan menghubungkan View dengan Mapper
        new TechnoShopController(view, mapper);

        // Menampilkan View
        view.setVisible(true);
    }
}


