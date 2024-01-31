package project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import project.vo.ProductVo;

public class TblProductDao {
     public static final String URL ="jdbc:oracle:thin:@//localhost:1521/xe";
    public static final String USERNAME = "c##idev";
    private static final String PASSWORD = "1234";

    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public List<ProductVo> SelectByCategory(String category){
        List<ProductVo> list = new ArrayList<>();
        String sql = "select * from tbl_product where category = ? order by Pname";
        try (Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
        ){
            pstmt.setString(1, category);
            ResultSet rs =pstmt.executeQuery();
           while(rs.next()){
                    list.add ( new ProductVo(rs.getString(1),
                                       rs.getString(2),
                                       rs.getString(3),
                                       rs.getInt(4)));
            
        }
        
        } catch (SQLException e) {
            System.out.println("예외 발생 :" + e.getMessage());
        }

        return list;

    }
    
    public List<ProductVo> SelectByPname(String pcode){
        List<ProductVo> list = new ArrayList<>();
        String sql = "select * from tbl_product\r\n where pname like '%' || ? || '%'\r\n order by category";
        try (Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
        ){  pstmt.setString(1, pcode);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                    list.add( new ProductVo(rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getInt(4)));
            }
        } catch (SQLException e) {
            System.out.println("예외 발생 :" + e.getMessage());
        }

        
        return list;
    }

    public Map<String,Integer> getPriceTable(){
        Map<String,Integer> priceMap = new HashMap<>();
        List<ProductVo>list = allProduct();
        for(ProductVo productVo : list){
            priceMap.put(productVo.getPcode(),productVo.getPrice());

        }
            return priceMap;




             //     public Map<String,Integer> getPriceTable(){
    //     Map<String,INteger>map = new Hashmap();
    //     String sql ; "select pcode,price from tbl_product";
    //     try(
    //         Connection conn = getConnection();
    //         PreparedStatement psmt = conn.prepareStatement(sql))
    //     {
    //         ResultSet rs = psmt.executeQuery();
    //         while(rs.next()){
    //             map.put(rs.getString(1),
    //                     rs.getInt(2));
    //         }
    //     } catch (SQLException e) {
    //         System.out.println("예외 발생 :" + e.getMessage());
    //     }

            //return map;

    //     }
    }

    public List<ProductVo> allProduct(){
         List<ProductVo> list = new ArrayList<>();
         String sql = "SELECT * FROM TBL_PRODUCT tp";
    try (Connection connection = getConnection();
         PreparedStatement pstmt = connection.prepareStatement(sql);
    ){
        
        ResultSet rs =pstmt.executeQuery();
        while(rs.next()){
            list.add(new ProductVo(rs.getString (1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getInt(4)));
        }
    } catch (SQLException e) {
        System.out.println("예외 발생 :" + e.getMessage());
    }

    return list;


        }
    }

   
    
        
    

