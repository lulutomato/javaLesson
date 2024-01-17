package io.day14;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ThreadTest {

    public static void main(String[] args) {


        //실행 시간이 오래 걸릴 경우 실행 중이라는 표시를 사용자에게 보여줍니다
        //      ㄴ 1초마다 .출력하기 => 새로운 쓰레드로 만듭니다.
        // 1) Thread 클래스 상속받기  2) Runnable 인터페이스 구현하기


        //쓰레드가 할일을 인터페이스로 활용하여 정의합니다. => 익명클래스
        Runnable runnable = new Runnable() {  //쓰레드가 할 일을 여기에 코딩하세요.
            @Override
            public void run() {
              boolean run = true;
              while(run){
                System.out.print(".");
                try {
                    Thread.sleep(500); //0.5초
                } catch (InterruptedException e) {
                    run = false;
                    System.out.println();
                }
                // interrupt : 방해하다,간섭하다,끼어들다

              }  
                
            }
        };
        //쓰레드 생성하기. 할일은 생성자 인자로 전달.
        Thread thread = new Thread(runnable);
        // 쓰레드 실행하기
        thread.start();



        long start = System.nanoTime();         //System에서 시간측정 메소드 나노세컨드 10억분의 1, ms 1000분의 1
        //처리할 메소드
        //copyByByteArray();
        copyByByte(); //main 쓰레드 실행
        //copyByByte();
        long end = System.nanoTime();
        System.out.println(String.format("소요시간 : %,d ns",(end-start)));
         thread.interrupt();
         //thread.stop() //비추.소멸된 메소드
    }
    //쓰레드 종료를 위해 인터럽트 발생하기
    




    //순수하게 1바이트씩 입출력 
    public static void copyByByte() {
        int b; int count=0;
        FileInputStream fis = null;
        FileOutputStream fos = null;
      
        try{
            fis = new FileInputStream("C:\\Users\\Administrator\\Downloads\\계곡.jpg");
            fos = new FileOutputStream("계곡복사.jpg");
            while((b=fis.read()) != -1){      
                //1바이트 씩 복사하기(파일입력스트림fis에서 파일출력스트림fos 로)
                fos.write(b);
                count++;
            }
        }catch(IOException e) {
            System.out.println("파일 입출력 예외 : " + e.getMessage());
        }finally {
            try{ fis.close(); fos.close();}
            catch(IOException e) {}
        }
        System.out.println(String.format("\n복사한 파일 크기 : %,d 바이트",count));
    }
    //   복사한 파일 크기 : 2,595,514 바이트
    // 소요시간 : 3,817,889,300 ns

   
        }

        // 복사한 파일 크기 :  2,595,514 바이트
        // 소요시간 :  16,642,500 ns

