package basic.day1;


public class ProbChangeMoney{

   public static void main(String[] args) {

       int myWon=1000000;    //여행 경비 (100만원)
       double toYen;        //환전한 엔화 값

       int cost=98760;           //사용한 여행 비용
       int myYen;          //남은 여행 경비
       double toWon;       //남은 경비 환전한 원화 값

       double yenTowonRate=9.29;   //1엔이 몇 원? 살때
       double wonToYenRate=8.97;   //1엔이 몇 원? 팔때
   
   
       System.out.println("환전할 원화 :" + myWon);
       toYen = myWon/yenTowonRate;
       System.out.println("원화를 엔화로 바꿀 때 :" + toYen);
       
       System.out.println("일본에서 사용한 금액 :" + cost);
       myYen = (int)toYen - cost;
       System.out.println("남은 엔화는? : " + myYen+"엔");
       toWon = myYen*wonToYenRate;
       System.out.println("엔화를 원화로 바꿀 때 :" + toWon+"원");

    
    }

}