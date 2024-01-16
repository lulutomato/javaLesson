package io.javaword;

import java.util.ArrayList;
import java.util.List;

import collection.myapp.JavaWord;


//프로그램 실행을 시작하는 메소드  

/* JavaWordApp_V4: 단어장 객체를 생성할 때 읽어올 단어장 파일을 꼭 전달해야 합니다.
/                  단어장 파일의 데이터로 리스트를 초기화 했습니다.

*/
   


public class JavaWordApp_V4 {
    // private : JavaWordList 객체
    private JavaWordList words = new JavaWordList("단어장.txt");

    // 프로그램 실행을 시작하는 메소드
    private void start() {
     words.fileLoad();          //파일에서 단어장 읽어오기
        // 단어 등록, 목록,검색,삭제 기능을 메뉴로 구현합니다.
        System.out.println("단어장 프로그램 시작합니다.~~" + "~".repeat(30));
        while (true) {
            System.out.println("\t<메뉴를 선택하세요>");
            System.out.println("\t1.단어 등록");
            System.out.println("\t2.단어 목록 출력");
            System.out.println("\t3.단어 검색");
            System.out.println("\t4.단어 삭제");
            System.out.println("\t5.프로그램 종료");
            System.out.println("선택 >"); // 메소드에 입력기능이 있을때는 Scanner 관리가 불편
            int select = Integer.parseInt(System.console().readLine()); // 키보드 입력 문자열->정수
            // System.console(): 시스템의 콘솔(표준입출력장치) 객체를 리턴. readLine 입력메소드 실행
            switch (select) {

                case 1:
                    addWord();
                    break; // 단어 등록 메소드 실행
                case 2:
                    ListWord();
                    break; // 단어 목록 출력 메소드 실행
                case 3:
                    searchWordBy();
                    break; //
                case 4:
                    removeWord();
                    break;
                case 5:
                    System.out.println("프로그램을 종료합니다.");
                    words.fileSave();      //최종 리스트 내용을 파일에 저장.
                    System.exit(0); // main 실행을 종료
                    break;
                   
                default:
                    System.out.println("잘못된 선택값 입니다.");
                    break;
            }// switch end

        } // while end

    }// start end

    
    private void removeWord() {
        System.out.println("\t::단어 삭제합니다.::");
        System.out.println("삭제할 단어를 영문으로 입력하세요");
        String find = System.console().readLine();
        int position = words.indexOfWord(find, 0); //단어의 인덱스를 배열로 리턴
            if (position==-1) {         //인덱스 0부터 단어를 찾아서 -1 이면 찾는 단어가 없음.
                System.out.println("삭제할 단어는 단어장에 없습니다");
                return;
            }
           while(position !=-1){
                   System.out.println("삭제할 단어 -" +words.getWord(position));
                    System.out.println("삭제하려면 엔터, 취소는 n을 입력하세요");
                if(!System.console().readLine().equals("n")){
            //단어 삭제
            words.remove(position);
            System.out.println("단어 삭제 완료!");
            position--; //삭제했을 때는 인덱스값이 position 뒤의 요소들은 모두 -1씩 감소
        
                }
                position=words.indexOfWord(find, ++position);// 단어의 인덱스를 배열로 리턴
        } 
        }

        
       
    

   
    
    private void searchWordBy(){
        System.out.println("\t::단어 검색합니다.(단어 조회는 1, 레벨조회는 2)::");
        String find=null;
        List<JavaWord>list=null;                      //조회되는 결과를 리턴받아 참조할 변수입니다.
        int level = 0;
        switch (System.console().readLine()) {  //키보드 입력을 변수에 저장하지 않고 직접 switch에 작성
            case "1":
                    System.out.println("검색한 단어를 영문을 입력하시오");
                    find = System.console().readLine();
                    list = words.searchAllByEnglish(find);
                break;
            case "2" : 
                    System.out.println("검색할 레벨을 입력하세요");
                    level = Integer.parseInt(System.console().readLine());
                    list = (words.searchAllByLevel(level));
                    break;
            default:
                System.out.println("1,2만 입력하세요");
                return;
        }
        System.out.println("\t::검색 결과 입니다.::");
        if(list.size()==0) 
        System.out.println("찾는 단어가 단어장에 없습니다");
        else 
        JavaWordList.print(list); //static 메소드
    }







    
    

      
    




    // 리스트에 없는 단어를 조회한다면? 같은 단어를 중복 저장 방법에 따라 위와 같이 할 수 있습니다.
    private void ListWord() {
        // 리스트 words 에 저장된 요소(항목)들을 모두 출력해 보세요. - 예쁘게~~
        System.out.println(String.format("%-20s %-30s\t %-20s", "English", "Korean", "Level"));
         words.print();
    }



    private void addWord() {
        System.out.println("\t::단어 등록합니다::");
        System.out.println("영어 단어 입력하세요._");
        String english = System.console().readLine();
        System.out.println("한글 의미 입력하세요._");
        String korean = System.console().readLine();
        System.out.println("단어 레벨 입력하세요.(1.초급 2.중급. 3고급)");
        int level = Integer.parseInt(System.console().readLine());

        words.add(new JavaWord(english, korean, level));
        //테스트 하면 서 코드 분석해보세요
    }

    public static void main(String[] args) {
        // 프로그램 실행하는 객체 생성하고
        // (메소드로 기능을 분리할 때 main이 호출하는 static을 없애기 위함.)
        // start 메소드 프로그램 실행 내용을 코딩

        JavaWordApp_V4 app = new JavaWordApp_V4();
        app.start();

    }

}
