package jdbc.day2;

import java.util.List;




import project.dao.TblProductDao;
import project.dao.TblBuyDao;
import project.vo.BuyVo;
import project.vo.CustomerBuyVo;
import project.vo.ProductVo;

public class CartApp_2 {
//static이 많아지면 상속의 특징을 사용할 수 없습니다. 멀티 스레드 환경에 적합하지 않습니다.
//         -> 여기서는 <테스트>를 위해 static 사용 중입니다.
    public static void showMenu() {
        System.out.println(".".repeat(50));
        System.out.println("[C] 카테고리별 상품 조회     [P] 상품명 검색  [M]나의 구매내역");
        System.out.println("[B] 구매하기   [D] 구매 취소   [Q] 구매 수량 변경  [X] 구매 종료");
        System.out.println(".".repeat(50));
    }
    public static void showMyPage(){
      
    



    }

    public static void main(String[] args) {
        TblProductDao productDao = new TblProductDao();
        TblBuyDao buyDao = new TblBuyDao();
        System.out.println("구매할 사용자 간편 로그인 필요합니다.");
        System.out.print("아이디 입력 __");

        //상품 목록을 선택한 카테고리에 대해 보여주기  (구매할 상품 조회)
        //또는 상품명으로 검색 (구매할 상품 조회)
        //또는 입력한 아이디로 구매한 구매내역 보여주기 (구매수량 변경 또는 구매 취소 buy_idx 조회)
        
        String customerID = System.console().readLine();
        System.out.println(customerID + "님 환영합니다.");
        boolean run = true;

        while (run) {       //메뉴 선택 반복
          showMenu();
          System.out.println("선택 >>>");  
         // int select = Integer.parseInt(System.console().readLine());
          String select = System.console().readLine();
            switch (select) {
                
                    case "M":  List<CustomerBuyVo> result = buyDao.selectCustomerBuyList(customerID);
                    for(CustomerBuyVo vo : result)
                    System.out.println(vo);   //나의 구매내역
                    break;
                    case "C","c": //카테고리별 상품조회
                    System.out.println("카테고리 : A1 - 과일 A2 - 수입과일 B1 - 인스턴트 B2 - 선물세트 C1 - 과자류");
                    System.out.println("카테고리 입력__");
                    String category = System.console().readLine();
                    List<ProductVo> productList = productDao.SelectByCategory(category);
                    for(ProductVo vo : productList)
                    System.out.println(vo);
                    break;
                
                    
                    case "P","p": //상품명 검색
                    System.out.println("상품명 검색어 입력__");
                    String pname = System.console().readLine();
                    productList  = productDao.SelectByPname(pname);
                    for(ProductVo vo : productList)
                    System.out.println(vo);
                    break; 
                    
                    case "X","x" : //구매종료
                    run =false;
                    break;
                    
                    case "B","b" : //구매하기
                    System.out.println("코드를 입력하세요");
                    String pcode = System.console().readLine();
                    System.out.println("구매 수량을 입력하세요");
                    int quantity = Integer.parseInt(System.console().readLine());

                   BuyVo vo = new BuyVo(0, customerID, pcode, quantity, null);
                    buyDao.buy(vo);
                    if(buyDao.buy(vo)==1)
                        System.out.println("상품을 담았습니다.");
                    else
                        System.out.println("상품코드 또는 고객아이디 오류입니다.");
                    break;

                    case"D","d": //취소
                    System.out.println("취소할 구매 번호를 입력하세요.__");
                    int buy_idx = Integer.parseInt(System.console().readLine());
                    if(buyDao.cancle(buy_idx)==1)
                            System.out.println("정상적으로 취소 되었습니다");
                    else
                        System.out.println("없는 구매번호 입니다.");

                    break;

                    case "Q","q": //수량 변경
                    System.out.println("수정할 구매 번호를 입력하세요.__");
                    buy_idx = Integer.parseInt(System.console().readLine());
                    
                    System.out.println("상품코드를 입력하세요.__");
                    pcode = System.console().readLine();

                    System.out.println("변경할 수량을 입력하세요.__");
                    quantity = Integer.parseInt(System.console().readLine());
                    vo = new BuyVo(buy_idx, customerID, pcode, quantity, null);
                    buyDao.modify(vo);
                    //Map 을 사용해 봅시다.
                    // Map<String,Integer>arg = new HandMap<>();
                    // //vo = new Buybo
                    // arg.put("buy_idx", buy_idx);
                    // arg.put("quantity", quantity);

                    if(buyDao.modify(vo)==1)
                                System.out.println("정상적으로 수정 되었습니다");
                    else
                                System.out.println("없는 구매번호 입니다.");

                    break;
                    default:
                    break;
            }
        }

    }
}   // tbl_buy 테이블을 대상을 insert, update, delete 할수 있는 dao 클래스 TblBuyDao.java 
    //         테이블 컬럼과 1:1 대응되는 BuyVo.java  
    //         테이블 PK 컬럼은 buy_idx -> update,delete 의 조건 컬럼입니다.     
    //         insert 에서 시퀀스는 sql 실행할 때와 동일하게 사용합니다.       
