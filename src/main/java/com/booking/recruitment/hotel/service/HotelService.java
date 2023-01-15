package com.booking.recruitment.hotel.service;

import com.booking.recruitment.hotel.model.Hotel;

import java.util.List;

public interface HotelService {
  List<Hotel> getAllHotels();
  List<Hotel> getHotelsbyCityId(Long cityId);

  List<Hotel> getHotelsByCity(Long cityId);

  Hotel createNewHotel(Hotel hotel);
  Hotel getHotelbyHotelId(Long id);
  void deleteHotelbyHotelId(Long id);
  List<Hotel> findingBestHotelsforCustomers(Long cityId, Double distance);
}
