package day11;

import java.util.ArrayList;
import java.util.List;

import collection.practice;

//14일 저녁 9시까지 제출
public class CustomerManageApp {
    //JavawordApp v2 형식으로
    private List<Customer> customer = new ArrayList<>();
    public static void main(String[] args) {
        
        CustomerManageApp app = new CustomerManageApp();
        app.start();
    }
    private void start(){
        initialize();
        //메뉴 선텍 : 등록, 검색 (이름, 그룹),삭제, 수정, 전체출력
        System.out.println("회원 관리 프로그램을 시작합니다~~~~~");
        while (true){
            System.out.println("\t<메뉴를 선택하세요>");
            System.out.println("\t 1. 회원 등록");
            System.out.println("\t 2. 회원 검색");
            System.out.println("\t 3. 회원 삭제");
            System.out.println("\t 4. 회원 정보 수정");
            System.out.println("\t 5. 회원 목록 출력");
            System.out.println("\t 6. 프로그램 종료");
            System.out.println("선택 >");
            int select = Integer.parseInt(System.console().readLine());
            switch (select) {
                case 1: registration(); break;
                case 2: search(); break;
                case 3: delete(); break;
                case 4: modify(); break;
                case 5: roster(customer); break;
                case 6: 
                System.out.println("프로그램을 종료합니다");
                System.exit(0); 
                break;
            
                default:
                    System.out.println("메뉴를 잘 보시고 다시 선택해 주세요");
                    break;
            }

        }

    }
    private void initialize(){
        customer.add(new Customer("홍흥봉", "01033553355", 1));
        customer.add(new Customer("오지심", "01033553351", 2));
        customer.add(new Customer("김말자", "01033553305", 3));
        customer.add(new Customer("김정자", "01022553355", 3));
        customer.add(new Customer("허홍휘", "01033553355", 4));
}

    private void registration(){
        System.out.println("\t :: 회원 등록 합니다::");
        System.out.println("이름을 입력하세요");
        String name = System.console().readLine();
        System.out.println("전화번호를 입력하세요");
        String phone = System.console().readLine();
        System.out.println("그룹을 입력하세요 (1 , 2 , 3 , 4)");
        int level = Integer.parseInt(System.console().readLine());

        customer.add(new Customer(name, phone, level));
    }

    private void search(){
        System.out.println("\t :: 회원 정보를 검색합니다. (이름으로 조회는 1, 그룹으로 조회는 2)::");
        String find = null;
        List<Customer>list = null;
        int group = 0;
        switch (System.console().readLine()) {
            case "1":
                   System.out.println("검색할 고객님의 성함을 입력하세요");
                   find = System.console().readLine();
                   list = searchName(find);
                break;
            case "2":
                  System.out.println("검색할 고객님의 그룹을 입력하세요");
                  group = Integer.parseInt(System.console().readLine());
                  list = (searchGroup(group));
                  break;
                default:
                  System.out.println("1,2중 하나만 입력 하세요");
                return;
        }
        System.out.println("\t:: 검색 결과 입니다.::");
        if(list.size()==0) System.out.println("찾는 회원님이 명단에 없습니다");
        else {roster(list);}
    }

    private List<Customer> searchName(String name){
        List<Customer> list = new ArrayList<>();
        for (Customer i : customer){
            if(i.getName().equals(name)){
                 list.add(i);
            }
        }
        return list;
    }
       
    private List<Customer> searchGroup(int group){
        List<Customer> number = new ArrayList<>();
        for(int i=0;i<customer.size();i++){
        //for(Customer i : customer){
            if(customer.get(i).getGroup()==group){
                number.add(customer.get(i));
            }
        }
        return number;
    }
    private void delete(){
        System.out.println("\t::탈퇴하신 고객님 정보를 삭제합니다.::");
        System.out.println("탈퇴하신 고객님 성함을 입력해주세요");
        String find = System.console().readLine();
        boolean isFind = false;
        for(int i =0;i<customer.size();i++){
            if (customer.get(i).getName().equals(find)){
                isFind = true;
                System.out.println("고객님의 정보" +customer.get(i).toString()+"를 찾았습니다");
                System.out.println("삭제하려면 y,취소는 n을 입력하세요");
                if(System.console().readLine().equals("n"))
                    continue;
                else {
                    if(System.console().readLine().equals("y"))
                    customer.remove(i);
                    System.out.println("회원님의 정보를 삭제했습니다.");

                }
            }

        }
        if (isFind); //isFind == false
        System.out.println("삭제할 고객님이 명단에 없습니다");
    }
       


        

        private void roster(List<Customer>roster){
            System.out.println(String.format("%-8s %20s %20s",  "Name","Phone","Group"));
           
        
        for(Customer i : roster){
             System.out.println(String.format("%-10s %20s %13d", i.getName(),i.getPhone(),i.getGroup()));
                }

        

        
        }

        private void modify(){
            
            System.out.println("고객님의 전화번호를 수정합니다");
            System.out.println("수정할 고객님의 성합을 입력하세요");
            String find = System.console().readLine();
            System.out.println("수정할 번호를 입력하세요");
            String phone = System.console().readLine();
            for(int i =0; i <customer.size();i++){
                if(customer.get(i).getName().equals(find)){
                    customer.get(i).modify(phone,customer.get(i).getGroup());
                    break;
                }

            }
            System.out.println("수정할 고객님의 전화번호를 입력하세요");
            





        }





    }





