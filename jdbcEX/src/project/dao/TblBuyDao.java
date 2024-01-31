package project.dao;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import project.vo.BuyVo;
import project.vo.CustomerBuyVo;

public class TblBuyDao {
    public static final String URL ="jdbc:oracle:thin:@//localhost:1521/xe";
    public static final String USERNAME = "c##idev";
    private static final String PASSWORD = "1234";

    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    //executeUpdate 메소드는 insert,update,delete가 정상 실행(반영되 행 있으면)되면 1을 리턴, 
    //구매하기               특히 update,delete 는 조건에 맞는 행이 없어서 반영된 행이 없으면 0을 리턴.

    public int buy(BuyVo vo) {
        String sql = "insert into tbl_buy(buy_idx,customid,pcode,quantity,buy_date) values (?,?,?,?,sysdate)";
       int result =0;
        try(Connection connection = getConnection();   //1)서버와의 연결
        PreparedStatement pstmt = connection.prepareStatement(sql);  //2) 연결된 서버로 실행할 SQL전달. 서버가 SQL 컴파일.
         //auto close
         ){
            pstmt.setInt(1,vo.getBuy_idx());
            pstmt.setString(2,vo.getCustomid());
            pstmt.setString(3,vo.getPcode());
            pstmt.setInt(4,vo.getQuantity());
           result = pstmt.executeUpdate();      



         }catch(SQLException e){
            //customid 와 pcode는 참조테이블에 존재하는 값으로 하지않으면 무결성 위반 오류
            System.out.println("구매 예외 발생 :" +e.getMessage());
         }
         return result;
        }
        //구매 취소 PK 가 행 식별합니다. 특정 행을 삭제하려면  where 조건컬럼은 buy_idx(PK)
        
    public int cancle (int buy_idx){
            String sql = "delete from tbl_buy where buy_idx = ?";
            int  result = 0;
        try(
            Connection connection = getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql);

        ){
            pstmt.setInt(1, buy_idx);
           result =  pstmt.executeUpdate();
           
        }catch(SQLException e){
            System.out.println("취소 실행 예외 발생 :" + e.getMessage());
        }
        return result;
}   
    //구매 수량 변경 - PK 가 행 식별합니다. 특정 행을 수정하려면  where 조건컬럼은 buy_idx(PK)
        public int modify(BuyVo vo){            //(BuyVo vo){ 
            int result = 0;
            String sql = "update tbl_buy set quantity = ? where buy_idx = ? ";
        try(
            Connection connection = getConnection();
                PreparedStatement pstmt = connection.prepareStatement(sql);
            ){
              
                pstmt.setInt(1, vo.getQuantity()); //vo.getquantity
                pstmt.setInt(2,vo.getBuy_idx()); //vo.getbuy_idx
              result = pstmt.executeUpdate();
              
                //buy_idx 컬럼에 없는 값이면 오류는 아니고 update 반영한 행의 개수
            }catch(SQLException e){
                System.out.println("수량 변경 실행 예외 발생" + e.getMessage());
            }
            return result;
    } 

    //mypage 기능의 메소드.

    public List<CustomerBuyVo> selectCustomerBuyList(String customerID){
        List<CustomerBuyVo> list = new ArrayList<>();
        String sql = "SELECT tb.BUY_IDX , tb.PCODE ,tp.PNAME,tp.PRICE ,BUY_DATE,QUANTITY  \r\n" + //
                "FROM TBL_BUY tb \r\n" + //
                "JOIN TBL_PRODUCT tp \r\n" + //
                "ON tb.PCODE  = tp.PCODE \r\n" + //
                "WHERE tb.CUSTOMID = ? order by buy_date desc";    
        try (Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(sql);
    ){  pstmt.setString(1, customerID);
        ResultSet rs = pstmt.executeQuery();     
        while(rs.next()){
            list.add(new CustomerBuyVo(rs.getInt(1),
            rs.getString(2),
            rs.getString(3),
            rs.getInt(4),
            rs.getTimestamp(5),
            rs.getInt(6)));

        }
    } catch (SQLException e) {
        System.out.println("예외 발생 :" + e.getMessage());
    }
    
    return list;
}
    //장바구니 모두 구매
    //ㄴbatch (배치)는 일괄처리 : 실행할 insert,update,delete 등의 데이터 저장 DMLdmf 여러개 모아 두었다가
    //                           한번에 실행시킵니다.
    //ㄴ 트랜잭션 : 특정 요구사항에 대한 '하나의 기능'을 실행할 여러 SQL 명령들로 구성된 '작업단위' 
    //             ㄴ예시 : cart에 저장된 상품 중 하나라도 참조값이 없는 pcode가 있으면 rollback, 모두 정상이면 commit
    //                      트랜잭션 commit 모드가 auto 에서 수동으로 변경.
    public int insertMany(List<BuyVo> cart) { //여러번(cart 크기)의 insert를 실행합니다.
        String sql = "insert into tbl_buy(buy_idx,customid,pcode,quantity,buy_date) values (buy_pk_seq.nextval,?,?,?,sysdate)";
       Connection connection = null;
       PreparedStatement pstmt = null;
       int count = 0;
        try{connection = getConnection();   
         pstmt = connection.prepareStatement(sql); 
         connection.setAutoCommit(false);  //auto 커밋 해제
         for (BuyVo vo : cart)
         {
            
            pstmt.setString(1,vo.getCustomid());
            pstmt.setString(2,vo.getPcode());
            pstmt.setInt(3,vo.getQuantity());
            pstmt.addBatch();       
            //sql을 메모리에 모아두기. insert sql 에 대입되는 매게변수값은 매번 다릅니다.
            count++;   


    }
    pstmt.executeBatch();           // 모아둔 sql을 일괄 실행하기.실행중 무결성 오류 생기면
    connection.commit();            // catch 에서 rollback
}catch(SQLException e){  //예외 발생 :트렌젝션 처리 
    try{
        connection.rollback();;
       
    }catch(SQLException e1){  }
    count = -1;
    System.out.println("구매불가능한 상품이 있습니다.");
    System.out.println("장바구니 구매 실행 예외 발생:"+ e.getMessage());

} finally{      //정상실행과 예외 모두에 대해 자원 해제
    try{        //트랜잭션 처리를 위해 connection을 사용해야 하므로 직접 close 했습니다.
        pstmt.close();
        connection.close();
    }catch(SQLException e1){  }
}
return count;
}

}