package basic.day2;

public class A10StringVariable {
    
    public static void main(String[] args) {
         //String 은 문자열 : 문자('')의 집합, 문자열 리터러은 기호 "" 사용합니다.
         String message = "Hello~ 안녕!! 하이하이      ";

         System.out.println(message);
         System.out.println("메세지 '"+ message + "'의 길이 :" + message.length());
         
         message="     welcome~ java world!!!!     ";
         System.out.println("메세지 '"+ message + "'의 길이 :" + message.length()); // 알파벳 개수
         System.out.println("메세지 '"+ message + "'의 길이 :" + message.lastIndexOf("!")); //마지막에 등장한 위치
         System.out.println("메세지 '"+ message + "'의 길이 :" + message.indexOf("java")); //처음 등장한 위치
         System.out.println("메세지 '"+ message + "'의 길이 :" + message.concat("????")); // 문자열을 추가한 새로운 문자열
         System.out.println("메세지 '"+ message + "'의 길이 :" + message.compareTo("comewel"));//
         System.out.println("메세지 '"+ message + "'의 길이 :" + message.trim());//앞뒤 공백 제거
         System.out.println("메세지 '"+ message + "'의 길이 :" + message.toLowerCase());//다 소문자
         System.out.println("메세지 '"+ message + "'의 길이 :" + message.toUpperCase());//다 대문자
         System.out.println("메세지 '"+ message + "'의 길이 :" + message.replace("java","python"));//해당 문자열 전부를 변경된 문자열로 변경
         

         

         //String 으로 선언된 변수는 객체입니다.
         // 그래서 메소드를 갖고 필요한 기능들을 제공해 줍니다.

         //char emo = '😊';   //char 은 2바이트 이모티콘은 4바이트 문자형으로 저장안됨
         String emo = "😊"; // 이모티콘은 문자열로 저장해야 합니다.
         System.out.println(emo);

         // 자바 String의 중요한 메소드 7가지씩 조사해서 테스트 해보기 - 

    

    }
}
