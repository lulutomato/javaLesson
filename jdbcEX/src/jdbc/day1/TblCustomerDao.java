package jdbc.day1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public class TblCustomerDao {
   
    public static final String URL ="jdbc:oracle:thin:@//localhost:1521/xe";
    public static final String USERNAME = "c##idev";
    private static final String PASSWORD = "1234";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    //회원 가입
    public void join(CustomerVo vo){
        //할일 1 : SQL작성하기 (매개변수 표시 정확히 합시다.)
        String sql = "insert into tbl_custom(custom_id,name,email,age,reg_date) values (?,?,?,?,sysdate)";            //insert
        try(    //auto close
            Connection connection = getConnection();   //1)서버와의 연결
            PreparedStatement pstmt = connection.prepareStatement(sql);  //2) 연결된 서버로 실행할 SQL전달. 서버가 SQL 컴파일.
             //auto close
             ){
                //할일2 : 매개변수 바인딩 (매개변수 타입에 맞는 매소드를 실행합시다.)
                pstmt.setString(1,vo.getCustomId());
                pstmt.setString(2,vo.getName());
                pstmt.setString(3,vo.getEmail());
                pstmt.setInt(4,vo.getAge());
                

            //매개변수 바인딩
            pstmt.executeUpdate();
         } catch(SQLException e){
                System.out.println("join 실행 예외 발생 :" + e.getMessage());
            }//finally 없음

    }
    
    //회원 정보 수정 
    public void modify(CustomerVo vo){
            String sql = "UPDATE TBL_CUSTOM SET email = ? , age = ? where custom_id = ?";
            try(
                Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);
                 //auto close
                 ){
                    pstmt.setString(1,vo.getEmail());
                    pstmt.setInt(2,vo.getAge());
                    pstmt.setString(3,vo.getCustomId());
                    pstmt.executeUpdate();    //실행
                    } catch(SQLException e){
                    System.out.println("수정 실행 예외 발생 :" + e.getMessage());
                    }//finally 없음
                }
    //회원 탈퇴
    public void delete(String customId){
        String sql = "Delete from tbl_custom where custom_id = ?";
        try(
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);
             //auto close
             ){
                pstmt.setString(1,customId);
                pstmt.executeUpdate();
            } catch(SQLException e){
                System.out.println("Delete 실행 예외 발생 :" + e.getMessage());
            }//finally 없음
    }
    //회원 정보 조회 : select * from tbl_custom where custom_id = ?
    public CustomerVo getCustomer(String customId){
        String sql = "SELECT * FROM TBL_CUSTOM tc WHERE CUSTOM_ID = ?"; 
        return null;
    }
    //관리자를 위한 기능 : 모든 회원정보 조회
    public List<CustomerVo> allCustomers(){
        return null;
    }
}
