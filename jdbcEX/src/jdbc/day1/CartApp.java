package jdbc.day1;

public class CartApp {
    public static void main(String[] args) {
        System.out.println("구매할 사용자 간편 로그인 필요합니다.");
        System.out.print("아이디 입력__");

        System.err.println("[1] 구매하기  [2] 구매 취소  [3] 구매 수량 변경 [4] 구매 종료");
        int select = Integer.parseInt(System.console().readLine());
        boolean run = true;

        while(run) {      //메뉴 선택 반복
            switch (select) {
                case 1:  //[1] 구매하기 - buy 테이블에 insert (1행)
                    
                    break;
                case 2: //[2] 구매 취소 - delete
                
                    break;
                        
                case 3: // [3] 구매수량 변경  - update
                    break;
                default:
                    break;
            }

        }
    }
   





}
// tbl_buy 테이블을 대상으로 insert,update,delete 할수 있는 dao 클래스 TblBuyDao.java
//         테이블 컬럼과 1:1 대응되는 BuyVo.java 
//         테이블 pk 컬럼은 buy_idx -> update.delete의 조건컬럼입니다.