package io.day14;

import java.io.IOException;

import collection.myapp.JavaWord;
import io.javaword.JavaWordList;

/**
 * 1. 파일장치로 데이터를 저장하는 것은 입력스트림을 사용한다.( O X ) O
 *      설명 :                ㄴ 출력      답 : X
 * 2. 데이터 전송 단위에 따라 바이트기반과 문자기반으로 분류된다. ( O X ) O
 * 
 * 3. 1) FileReader 클래스의 부모 클래스는 Reader 클래스이다. ( O X ) O
 *      설명 : 문자기반 입출력 부모(상위) 클래스는 모두 Reader 클래스
 * 2) FileReader 클래스는 문자기반으로 InputStream 의 자식 클래스이다. ( O X ) X
 *      설명 :InputStream은 바이트 기반 클래스의 상위클래스 
 * 3) BufferWriter 클래스는 단독으로 사용되는 출력스트림으로 라인단위 처리가 가능하다. ( O X ) O
 *      설명 : 보조스트림으로 기본 입출력스트림과 같이 사용 답 : X
 *      결론 : InputStream 또는 OutputStream 으로 끝나는 이름의 클래스 바이트 기반
 *        Reader 또는 Writer로 끝나는 클래스 이름의 클래스는 문자 기반
 * 4. 바이트 기반의 입출력스트림 사용시 처리 속도 향상을 위해 사용할 수 있는 방법은? (2가지)
 *      답: 1) byte 배열로 일정크기의 바이트 단위로 입출력 
 *        2) 보조스트림 BufferedInputStream 또는 BufferedOutputStream
 * 5. 입출력스트림의 메소드 대부분이 처리가 필요한 예외는?
 *      답 : IOExecption (대부분 checked exception)
 * 6. 예외처리를 강요하는 것은 언체크드 익셉션이다. ( O X )  O
 *      설명 : 강요하는 것은 체크드 익셉션. 답 : X
 *      (참고) 언체크드 익셉션. 실행 중에 발생하는 RuntimeException 의 하위클래스들입니다.
 * 7. 예외처리를 직접 하지 않고 호출한 메소드로 위임하는 키워드는? throws
 *      설명 : 직접 처리는 try~ catch 구문 사용.  답: throws 예외이름1,예외이름2,.....
 * 8. 개발자는 throw 명령으로 조건이 만족되지 않은 경우 예외를 발생시킬 수 있다. ( O X ) O
 *      설명 : JavaWordList.java 적용 , ReviewProb.java에서 테스트
 * 9.   입출력스트림으로 사용된 객체는 반드시 자원해제를 해야한다. ( O X ) O
 *      참고: 자동으로 자원해제되는 try 구분 형식에는 명시적인 close() 안씁니다.
 *            7번과 9번 문제 테스트를 D11FileWriterThrows.ㅓㅁㅍㅁ
 * 10. System.in 표준입력만 사용하여 최대 20바이트의 문자열을 입력받을 수 있는 코드를 작성해보세요.
 * 입력받은 내용은 출력으로 확인도 합니다. 총 라인수 3~4 줄 (ReviewProb.java)
 */
public class ReviewProb {
    public static void main(String[] args) {
        prob_10();
    }

    //8번 문제 예시
    public static void prob_8(){
        JavaWordList words = new JavaWordList("단어장.txt");
        words.fileLoad();
        words.add(new JavaWord("null","비어있는", 1));
       // words.remove(-9); //words. remove(10);              //2)
        //개발자가 만드는 예외 : 
        //1) 예외가 발생되는 문법오류, 실행 오류 없지만 값의 범위를 제한하기 위해 예외 발생
        //2) 기존 방생하는 예외를 새로운 내용으로 변경할 때 
    }
    
    public static void prob_10(){
        byte[] buffer = new byte[20];
        try {
            //최대 20바이트 실제 입력한 바이트는 정수값 리턴
            int b = System.in.read(buffer);  //입력기능 다양하게 만든 것이 Scanner 클래스
            System.out.println("입력 바이트 수 :" + b);
            //System.out.println("입력한 문자열 :" + new String(buffer));
            System.out.println("입력한 문자열 정수 변환 : " + 
                Integer.parseInt(new String(buffer).trim()));
        } catch (IOException  | NumberFormatException e) {
            //2개의 예외를 처리합니다. 참고  || 논리적인 OR, | 는 비트단위 OR
            System.out.println("IOException 또는 NumberFormatException 예외를 처리합니다.");
            e.printStackTrace();
        }

    }
}

/*e.printStackTrace () 메소드로 실행되는 결과 예시. trace 는 추적하다.
 *   예의 원인을 추척해서 출력.
 * Exception in thread "main" java.lang.IllegalArgumentException: 단어창이 꽉 찼습니다.단어장을 새로 만들어주세요
 *       at io.javaword.JavaWordList.add(JavaWordList.java:87)
 *       at io.day14.ReviewProb.prob_8(ReviewProb.java:46)
 *       at io.day14.ReviewProb.main(ReviewProb.java:39)
 * 
 * Exception in thread "main" java.lang.NumberFormatException: For input string: "abc"
 *       at java.base/java.lang.NumberFormatException.forInputString(NumberFormatException.java:67)
 *       at java.base/java.lang.Integer.parseInt(Integer.java:668)
 *       at java.base/java.lang.Integer.parseInt(Integer.java:786)
 *       at io.day14.ReviewProb.prob_10(ReviewProb.java:63)
 *       at io.day14.ReviewProb.main(ReviewProb.java:41)
 *  
 * 
 * 
 * 
 */