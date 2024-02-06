package teamproject.vo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode

public class PlaceVo{
    private int place_seq;
    private String name;
    private String phone;
    private int rate;
    private String open_time;
    private String close_time;
    private String food_type;
    private String address;

    public PlaceVo(int place_seq, String name, String open_time, String close_time,String address, int rate){

            this.place_seq = place_seq;
            this.name =name;
            this.open_time = open_time;
            this.close_time = close_time;
            this.address = address;
            this.rate = rate;

    }

   public PlaceVo(int place_seq, String name, String open_time, String close_time,String food_type, int rate,String phone){

            this.place_seq = place_seq;
            this.name =name;
            this.open_time = open_time;
            this.close_time = close_time;
            this.rate = rate;
            this.phone = phone;
            this.food_type = food_type;
    }
    public PlaceVo(int place_seq, String name, String open_time, String close_time,String address){
        this.place_seq = place_seq;
        this.name = name;
        this.open_time = open_time;
        this.close_time = close_time;
       this.address = address;
       
    }

@Override
public String toString() {
        return "PlaceVo [place_seq=" + place_seq + ", name=" + name + ", phone=" + phone + ", rate=" + rate
                        + ", open_time=" + open_time + ", close_time=" + close_time + ", food_type=" + food_type
                        + ", address=" + address + "]";
}

   
    }
    
    


    

