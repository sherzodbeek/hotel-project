package uz.pdp.hotelproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hotelproject.entity.Hotel;
import uz.pdp.hotelproject.repository.HotelRepository;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;

    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @GetMapping("/{id}")
    public Hotel getHotelById(@PathVariable Integer id) {
        return hotelRepository.findById(id).orElseThrow(() -> new IllegalStateException("Hotel not found!"));
    }

    @DeleteMapping("{id}")
    public String deleteHotelById(@PathVariable Integer id) {
        hotelRepository.deleteById(id);
        return "Hotel deleted!";
    }

    @PostMapping
    public String addHotel(@RequestBody Hotel hotel) {
        Hotel hotel1 = new Hotel();
        hotel1.setName(hotel.getName());
        hotelRepository.save(hotel1);
        return "Hotel added!";
    }

    @PutMapping("{id}")
    public String editHotel(@PathVariable Integer id, @RequestBody Hotel hotel) {
        Hotel editingHotel = hotelRepository.findById(id).orElseThrow(() -> new IllegalStateException("Hotel not found!"));
        editingHotel.setName(hotel.getName());
        hotelRepository.save(editingHotel);
        return "Hotel edited!";
    }

}
