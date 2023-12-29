package basic.day2;

public class A09HangulCharTest {
    /*
     * 1.문자 '나' 부터 '냿' 까지 while문으로 출력해보세요. -> 변수 char start ='나';
     * 2.문자 '나' 부터 '냿' 까지 몇개의 문자가 있는지 갯수 구하기. int codeNa, codeNet
     * 
     * 
     * 
     */
    public static void main(String[] args) {
        char start = '나';
        char end = '낟';
        int codeNa = start; //'나' 의 코드값
        int codeNet = end; // '낟'의 코드값
      //1)
        while (start <= end)
      System.out.print(start++);
       //이 시점에서의 start는 ?
      System.out.println("\nstart=??" +start);  //'낟' 문자 '날'

      //2)
      System.out.println("start ~ end 까지문자의 개수 =" +(end-codeNa+1));
    
      
       
     

     
             
       
      
    System.out.println();
    
    }
}
