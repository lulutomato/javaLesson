package teamproject;


import java.util.List;


import teamproject.dao.TblMenuDao;
import teamproject.dao.TblPlaceAddressDao;
import teamproject.dao.TblPlaceDao;
import teamproject.vo.PlaceAddressVo;
import teamproject.vo.PlaceVo;

public class Restaurant {
    private TblPlaceDao placeDao = new TblPlaceDao();
    private TblPlaceAddressDao placeAddressDao = new TblPlaceAddressDao();
    private TblMenuDao menuDao = new TblMenuDao();

    
 
    
    public static void main(String[] args) {
        Restaurant app = new Restaurant();
        app.start();
    
    }
    public static void joinAddressBook(){
        System.out.println(".".repeat(50));
        System.out.println("[A]이름으로 맛집 찾기  [B]지역 별로 맛집 찾기  [C]평점 순위 보기  [D] 랜덤 맛집 뽑기");
        System.out.println("   [F]맛집 평점 수정     [G]삭제      [H]종료");
        System.out.println(".".repeat(50));
    }


    public void start () {
        boolean run = true;
        while (run){
            joinAddressBook();
            System.out.println("선택 >>> ");
            String select = System.console().readLine();
            switch(select){
                case "A","a":
                System.out.println("[A]이름으로 맛집 찾기");
                System.out.println("찾으시는 맛집 이름을 입력해주세요__");
                String name = System.console().readLine();
                System.out.println(placeDao.findName(name));
                break;

                case"B","b":
                System.out.println("[B] 지역 별로 맛집 찾기");
                System.out.println("맛집 목록[1.서울 2.인천 3. 경기 4. 대구 5. 광주 6. 제주]");
                System.out.println("찾는 맛집의 지역명을 입력해주세요__");
                String address = System.console().readLine();
                List<PlaceAddressVo> addressList = placeAddressDao.findArea(address);
                for(PlaceAddressVo vo : addressList)
                System.out.println(vo);
                break;
                
                case"C","c":
                System.out.println("[C] 평점 순위 보기");
                List<PlaceVo> list = placeDao.showRate(1);
                if(list.size()>0){
                    for (PlaceVo vo : list){
                        System.out.println(vo.getPlace_seq() + "\t상호명: " + vo.getName() +"\t\t\t"+"평점:" +vo.getRate() + "\t\t오픈시간:" + vo.getOpen_time() + "\t마감시간:" + vo.getClose_time() +"\t\t주소:" + vo.getAddress());
                    }
                }
                

                break;

                case"D","d":
                System.out.println("[D] 랜덤 맛집 뽑기");
                System.out.println("지역을 입력해주세요");
                System.out.println("서울\t"+ "경기\t" + "광주\t" + "대구\t" + "인천\t" +"제주");
                String place = System.console().readLine();
                System.out.println("리스트 몇개를 출력할지 알려주세요");
                int time = Integer.parseInt(System.console().readLine());
                List<PlaceVo> random = placeDao.randomRestaurant(place, time);
                if(random.size()>0){
                
                    for(PlaceVo vo : random){
                        System.out.println("상호명:" + vo.getName() + "\t오픈시간:" + vo.getOpen_time() + "\t마감시간:" + vo.getClose_time() + "\t주소:" +vo.getAddress());
                    }
                }
                
                break;

   
                case "F", "f":
                System.out.println("[F] 평점 수정");
                System.out.println("가게명을 입력해주세요");
                name = System.console().readLine();
                System.out.println("수정할 평점을 입력해주세요");
                double newRate = Double.parseDouble(System.console().readLine());
                if (newRate <=5 && newRate>0) {
                    placeDao.modifyRate(name, newRate);
                    System.out.println("가게 평점이 수정되었습니다.");
                }else{
                    System.out.println("평점의 범위를 벗어났습니다. 다시 입력해주세요");
                }
                break;

                case "G","g" :
                System.out.println("맛집을 삭제하겠습니다.");
                System.out.println(".".repeat(50));
                System.out.println("추방할 맛집 ID번호를 입력하세요");
                System.out.println("100001 ~ 100120 >>>");
                int place_seq = Integer.parseInt(System.console().readLine());
                if(place_seq >= 100001 && place_seq <=100120 ){
                    menuDao.deleteMenu(place_seq);
                    placeAddressDao.deletePlaceAddress(place_seq);
                    placeDao.deletePlace(place_seq);
                    System.out.println("맛집이 추방되었습니다. 새로운 맛집을 탐방해주세요");
                }else{
                    System.out.println("입력값이 잘못되었습니다 다시 입력해주세요");
                }
                break;

                case "H","h":
                System.out.println("[H] 종료");
                System.out.println("프로그램을 종료합니다");
                run = false;
                break;

                default :
                System.out.println("잘못된 알파벳 입력입니다. 다시 확인하고 입력하세요");
                break;






                
    
            }
        }
    }
    
    
   
    
}
