package day11;

import java.util.ArrayList;
import java.util.List;

import collection.myapp.JavaWord;
import collection.teacher.JavaWordX;
//프로그램 실행을 시작하는 메소드  

public class JavaWordApp_V1 {
    // private : 현재 클래스에서만 사용할 목적으로 접근 제한합니다.
    private List<JavaWordX> words = new ArrayList<>();

    // 프로그램 실행을 시작하는 메소드
    private void start() {
        initialize();
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
                    searchWord();
                    break; //
                case 4:
                    removeWord();
                    break;
                case 5:
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0); // main 실행을 종료
                    break;
                default:
                    System.out.println("잘못된 선택값 입니다.");
                    break;
            }// switch end

        } // while end

    }// start end

    private void initialize() {
        // words 리스트 요소를 몇개만 저장해서 초기화(테스트용)
        words.add(new JavaWordX("public", "공용의", 1));
        words.add(new JavaWordX("private", "사적인", 1));
        words.add(new JavaWordX("iterator", "반복자", 3));
        words.add(new JavaWordX("application", "응용프로그램", 2));
    }

    private void removeWord() {
        System.out.println("\t::단어 삭제합니다.::");
        System.out.println("삭제할 단어를 영문으로 입력하세요");
        String find = System.console().readLine();
        boolean isFind = false;  //단어 존재 유무 확인 변수
        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).getEnglish().equals(find)) {
                isFind = true;
                System.out.println("인덱스" + i + "에서 단어를 찾았습니다");
                System.out.println("삭제하려면 엔터, 취소는 n 을 입력하세요");
                if (System.console().readLine().equals("n"))
                    continue;
                else {
                    words.remove(i);
                    System.out.println("단어 삭제 완료");
                } // 단어삭제.
            } // 단어 비교 if end

        }//for end
        if(isFind);                 //isFind==false
        System.out.println("삭제할 단어는 단어장에 없습니다");
    }

    private void searchWord() {
        System.out.println("\t :: 단어 검색합니다.::");
        System.out.println("검색할 단어를 영문으로 입력하세요");
        String find = System.console().readLine();
        boolean isFind = false;
        for (JavaWordX word : words) {
            if (word.getEnglish().equals(find)) { // 문자의 비교 equals 등호사용 불가
                System.out.println("검색결과 : " + word.getEnglish()
                        + " = " + word.getKorean() + " 레벨" + word.getLevel());
                // return; //단어를 한개 찾으면 메소드 종료
                isFind = true; // 2)같은 단어가 2번이상 저장될 때
            }
        }
        if (isFind == false) // 2)이 if 문은 같은단어가 단어장에 2번이상 있을 때 사용.
            System.out.println("찾는 단어가 단어장에 없습니다");// 1),2)
    }

    // 리스트에 없는 단어를 조회한다면? 같은 단어를 중복 저장 방법에 따라 위와 같이 할 수 있습니다.
    private void ListWord() {
        // 리스트 words 에 저장된 요소(항목)들을 모두 출력해 보세요. - 예쁘게~~
        System.out.println(String.format("%-20s %-30s\t %-20s", "English", "Korean", "Level"));
        for (int i = 0; i < words.size(); i++) {
            System.out.println(String.format("%-10s %13s %32s", words.get(i).getEnglish(), words.get(i).getKorean(),
                    words.get(i).getLevel()));
        }
    }

    private void addWord() {
        System.out.println("\t::단어 등록합니다::");
        System.out.println("영어 단어 입력하세요._");
        String english = System.console().readLine();
        System.out.println("한글 의미 입력하세요._");
        String korean = System.console().readLine();
        System.out.println("단어 레벨 입력하세요.(1.초급 2.중급. 3고급)");
        int level = Integer.parseInt(System.console().readLine());

        words.add(new JavaWordX(english, korean, level));
    }

    public static void main(String[] args) {
        // 프로그램 실행하는 객체 생성하고
        // (메소드로 기능을 분리할 때 main이 호출하는 static을 없애기 위함.)
        // start 메소드 프로그램 실행 내용을 코딩

        JavaWordApp_V1 app = new JavaWordApp_V1();
        app.start();

    }

}
