package basic.day4;


//A25 소스파일은 구글드라이브에 올려주세요.
public class A25MySampleMethod {
      //A22번 1) ~ 5) 을 각각 메소드로 정의해 보기 - 메소드안에서 출력문 만들지 않습니다.
      // 1) sum1ToN  2) Multiply1ToN 3) sumMToN 4)MultiplyToN  5)MultiplyNof2
      // 6) 구구단 '출력' 하는 메소드, 리턴은 void. printGuguDan
      public static void main(String[] args) {

        int result = addMToN(2,5);
        boolean isOk = checkjumsu(267);
        





      int n=5;
      System.out.println("sum1Ton() sum=" + sum1ToN(n));

      long a =3;
      System.out.println("Multply1ToN =" + Multiply1ToN(a));

      int m=5; n=8;
      System.out.println("sumTon =" + sumTon(5,8));

      
      System.out.println("MultplyTon =" + MultiplyToN(m,n));

      
      System.out.println("MultiplyNof2 =" + MultiplyNof2(n));

      printGuguDan();
          
      }
  



      private static boolean checkjumsu(int i) {
        return i>=0 && i<=100;
      }




      private static int addMToN(int i, int j) {
        int sum=0;
        for(int k=i;k<=j;k++){
        sum+=k;
        }
        return sum;
      }




      public static int sum1ToN(int n){
      int sum = 0;
        for(int i=1;i<=n;i++){
         sum+=i;
      }
      return sum;
    }
      public static long Multiply1ToN(long a){
        long Multiply1 = 1;
          for(long i=1;i<=a;i++){
            Multiply1*=i;
        }
         return Multiply1;
        }

      public static int sumTon(int m,int n){
      
        int sum = 0;
          for(int i=m;i<=n;i++){
            sum+=i;
        }
            return sum;
      }
       public static long MultiplyToN(long m,long n){
        int sum = 1;
        for(long i=m;i<=n;i++){
          sum*=i;
        }
        return sum;
        }

        public static long MultiplyNof2(long n){
        int sum = 1;
        for(long i=1;i<=n;i++){
          sum*=2;
        }
        return sum;
      }
      public static void printGuguDan(){
        for(int i=2;i<=9;i++){
        System.out.println("지금 ..." + i + "단을 출력합니다.");
        for(int j=1;j<=9;j++){
          System.out.println(String.format("%d x %d = %3d", i,j,i*j));
        }
       }
      }


      }

    


    