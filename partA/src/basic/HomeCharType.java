package basic;

public class HomeCharType {

  public static void main(String[] args) {
    String message = "2024 Happy New Year~~~";

    int uppercount = 0;
    int lowerCount = 0;
    int numberCount = 0;
    int syobolCount = 0;

    for (int i = 0; i < message.length(); i++) {

      if (message.charAt(i) >= 48 && message.charAt(i) <= 57) {
        numberCount++;
      } else if (message.charAt(i) >= 65 && message.charAt(i) <= 90) {
        uppercount++;
      } else if (message.charAt(i) >= 97 && message.charAt(i) <= 122) {
        lowerCount++;
      } else {
        syobolCount++;
      }

    }
    System.out.println("문자열:" + message);
    System.out.println("대문자:" + uppercount + "소문자" + lowerCount + "숫자" + numberCount + "기호" + syobolCount);

  }

}
