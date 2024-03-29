package io.day14;

import java.awt.FileDialog;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

//socket.png 그림참고 :    클라이언트와 서버 각각 실행하는 메소드가 다릅니다.
//                        서버와 클라이언트 연결 과정이 다르기 때문.
public class Client {
    
    public static void main(String[] args) {
        Socket socket = null;
        InputStream is = null;
        DataInputStream dis = null;
        OutputStream os = null;
        DataOutputStream dos = null;
        FileInputStream fis = null;
        BufferedInputStream bis = null;

        try{
            System.out.println("[클라이언트] 프로그램 입니다.");
            socket = new Socket();
            //시간 중지 타임 설정
            Thread.sleep(3000);  //3초간 중지(3000ms)
            //서버에서 설정한 bind 정보와 같은 객체를 만듭니다.
            socket.connect(new InetSocketAddress("192.168.30.24",5050));
            System.out.println("__연결 성공 하였습니다.");

            //서버가 보낸 메시지 받기
             is = socket.getInputStream();
             dis = new DataInputStream(is);
            String message = dis.readUTF();
            System.out.println(message);

            //서버에게 인사말 보내기 
            os = socket.getOutputStream();
            dos = new DataOutputStream(os);
            System.out.println("서버에게 보낼 메세지를 쓰세요._");
            message = System.console().readLine();
            dos.writeUTF("\tFrom 클라이언트 >> " + message);

            //이미지 파일을 서버에 보내기 (업로드)
           // String filePath = "C:\\Users\\Administrator\\Pictures\\영웅.jpg";
            Map<String,String>map = showDialog();
            String filePath = map.get("folder")+ map.get("filename");
            //파일명을 서버에게 보내서 서버도 원래 파일명을 다운로드 하도록
            dos.writeUTF(map.get("filename"));
            //파일 입력을 받아 소캣으로 출력하기
            fis = new FileInputStream(filePath);
            bis = new BufferedInputStream(fis);
            int b;
            while((b=bis.read())!= -1){ //파일에서 읽어오기
                dos.write(b);           //소켓출력 (바이트단위)
            }
            System.out.println("파일 업로드 완료!!");

        }catch(IOException | InterruptedException e){

        }finally{
            try{socket.close();is.close();dis.close();os.close();dos.close();fis.close();bis.close();} catch(IOException e){}
        }

    }
        public  static Map<String,String> showDialog(){
        
        JFrame jf = new JFrame();
         //jf.setSize(200,200);
        jf.setVisible(false);

        FileDialog fd = new FileDialog(jf,"파일 선택",FileDialog.LOAD);
        fd.setVisible(true);
        // System.out.println("선택한 폴더 : " + fd.getDirectory()); //폴더 = 디렉토리
        // System.out.println("선택한 파일 : " + fd.getFile());
        // System.out.println(fd.getDirectory().replace("\\","\\\\") +  fd.getFile());
        
        Map<String,String>map = new HashMap<>();
        map.put("folder",fd.getDirectory().replace("\\","\\\\"));
        map.put("filename",fd.getFile());
       
        return map;
    
    }



}
