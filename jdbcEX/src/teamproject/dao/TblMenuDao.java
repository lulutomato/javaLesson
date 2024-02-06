package teamproject.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TblMenuDao {
    public static final String URL = "jdbc:oracle:thin:@//localhost:1521/xe";
    public static final String USERNAME = "c##idev";
    private static final String PASSWORD = "1234";

       private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

public void deleteMenu(int place_seq){ //메뉴 테이블에서 삭제
    
    String sql = "DELETE FROM TBL_MENU tm WHERE tm.place_seq = ?";
    try(Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);){
        pstmt.setInt(1,place_seq);
         pstmt.executeUpdate();   
        }catch (SQLException e){
            System.out.println("[메뉴] 삭제 예외 발생:" + e.getMessage());
        }
      



}







}
