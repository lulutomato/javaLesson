package object.day5;

import java.util.Arrays;

//학생들의 성적을 객체로 만들어 줄 클래스 입니다.
public class Score {
  

      private String name; // 학생이름
      private int grade; // 학년
      private int[] jumsues; // 점수들이 저장될 배열
                            // 배열의 크기는 학년마다 다를 수 있음.

      //getter
      public String getname(){
        return name;
      }

      public int getgrade(){
        return grade;
      }

      public int[] getjumsues(){
        return jumsues;
      }

      //setter
      public void setname(String n1){
        name = n1;
      }

      public void setgrade(int g1){
        grade = g1;
      }
      public void setjumsues(int[] j1){
        jumsues = j1;
      }
      
      public int sum(){
          int sum =0;
          for(int i=0;i<jumsues.length;i++){
            sum += jumsues[i];
        }
        return sum;
      }

        public double average(){
          double temp = 0;  // temp를 이용해서 위에 sum을 구한걸 가져옴
          temp = (double)sum()/jumsues.length;//double 로 캐스팅
          return temp;
          
        }

        public void printData(){
          System.out.println("이름: " + name + "\n학년 : "+ grade+ "\n과목 점수=" + Arrays.toString(jumsues));

        }
          
          


        }



      //점수의 합계를 리턴하는 sum() 메소드

      //점수의 평균 double 리턴하는 average() 메소드

      //모든 인스턴스 필드값을 확인하는 printScore() 메소드






