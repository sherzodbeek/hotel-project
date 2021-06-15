package uz.pdp.hotelproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.hotelproject.entity.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
}
