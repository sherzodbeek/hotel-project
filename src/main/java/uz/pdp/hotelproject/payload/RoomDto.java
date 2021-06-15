package uz.pdp.hotelproject.payload;

import lombok.Data;

@Data
public class RoomDto {
    private Integer id;
    private int number;
    private int floor;
    private int size;
    private int hotelId;
}
