package uz.pdp.hotelproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hotelproject.entity.Room;
import uz.pdp.hotelproject.payload.RoomDto;
import uz.pdp.hotelproject.repository.HotelRepository;
import uz.pdp.hotelproject.repository.RoomRepository;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomRepository roomRepository;

    @Autowired
    HotelRepository hotelRepository;

    @GetMapping
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable Integer id) {
        return roomRepository.findById(id).orElseThrow(() -> new IllegalStateException("Room not found!"));
    }

    @DeleteMapping("/{id}")
    public String deleteRoom(@PathVariable Integer id) {
        roomRepository.deleteById(id);
        return "Room deleted!";
    }

    @PostMapping
    public String addRoom(@RequestBody RoomDto roomDto) {
        Room room = new Room();
        room.setNumber(roomDto.getNumber());
        room.setFloor(roomDto.getFloor());
        room.setSize(roomDto.getSize());
        room.setHotel(hotelRepository.findById(roomDto.getHotelId()).orElseThrow(() -> new IllegalStateException("Hotel not found!")));
        roomRepository.save(room);
        return "Room added";
    }

    @PutMapping("/{id}")
    public String editRoom(@PathVariable Integer id, @RequestBody RoomDto roomDto) {
        Room editingRoom = roomRepository.findById(id).orElseThrow(() -> new IllegalStateException("Room not found!"));
        editingRoom.setNumber(roomDto.getNumber());
        editingRoom.setFloor(roomDto.getFloor());
        editingRoom.setSize(roomDto.getSize());
        editingRoom.setHotel(hotelRepository.findById(roomDto.getHotelId()).orElseThrow(() -> new IllegalStateException("Hotel not found!")));
        roomRepository.save(editingRoom);
        return "Room edited";
    }

    @GetMapping("/getRoomsByHotelId/{hotelId}")
    public Page<Room> getRoomsByHotelId(@PathVariable Integer hotelId, @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 2);
        return roomRepository.findAllByHotelId(hotelId, pageable);
    }
}
