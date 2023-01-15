package com.booking.recruitment.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.booking.recruitment.hotel.model.*;
import org.springframework.data.jpa.repository.Query;
import java.util.*;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
	@Query(value="select * from Hotel where city_id=?1", nativeQuery=true)
	List<Hotel> findingHotelswithinDistance(Long cityId);
}
