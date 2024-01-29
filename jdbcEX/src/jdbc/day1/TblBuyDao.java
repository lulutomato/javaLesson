package jdbc.day1;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TblBuyDao {
    public static final String URL ="jdbc:oracle:thin:@//localhost:1521/xe";
    public static final String USERNAME = "c##idev";
    private static final String PASSWORD = "1234";

    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    //구매하기 

    public void buy(BuyVo vo) {
        String sql = "insert into tbl_buy(buy_idx,customid,pcode,quantity,buy_date) values (?,?,?,?,sysdate)";
        try(Connection connection = getConnection();   //1)서버와의 연결
        PreparedStatement pstmt = connection.prepareStatement(sql);  //2) 연결된 서버로 실행할 SQL전달. 서버가 SQL 컴파일.
         //auto close
         ){
            pstmt.setInt(1,vo.getBuy_idx());
            pstmt.setString(2,vo.getCustomid());
            pstmt.setString(3,vo.getPcode());
            pstmt.setInt(4,vo.getQuantity());
            pstmt.executeUpdate();      



         }catch(SQLException e){
            System.out.println("구매 예외 발생 :" +e.getMessage());
         }}
        //구매 취소
        
    public void cancle (int buy_idx){
            String sql = "delete from tbl_buy where buy_idx = ?";
        try(
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);

        ){
            pstmt.setInt(1, buy_idx);
            pstmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("취소 실행 예외 발생 :" + e.getMessage());
        }
 
}   
    //구매 수량 변경
    public void modify(BuyVo vo){
        String sql = "update tbl_buy set quantity = ?, pcode = ?, customid = ?";
        try(
            Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);
            ){
               pstmt.setString(1,vo.getCustomid());
               pstmt.setString(2,vo.getPcode());
               pstmt.setInt(3, vo.getQuantity());

            }catch(SQLException e){
                System.out.println("수량 변경 실행 예외 발생" + e.getMessage());
            }
    } 


}
