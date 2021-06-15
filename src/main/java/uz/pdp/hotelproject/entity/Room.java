package uz.pdp.hotelproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private int number;

    @Column(nullable = false)
    private int floor;

    @Column
    private int size;

    @ManyToOne
    private Hotel hotel;
}
