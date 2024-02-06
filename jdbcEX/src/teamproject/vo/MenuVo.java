package teamproject.vo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class MenuVo {
    private int menu_seq;
    private int place_seq;
    private String menu_name;
    private int price;

public MenuVo(int place_seq){
    this.place_seq = place_seq;
}


    @Override
    public String toString() {
        return "menu_seq=" + menu_seq + ", place_seq=" + place_seq + ", menu_name=" + menu_name + ", price="
                + price;
    }
}
    

