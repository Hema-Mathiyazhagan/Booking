package com.booking.recruitment.hotel.service.impl;

import com.booking.recruitment.hotel.exception.BadRequestException;
import com.booking.recruitment.hotel.model.Hotel;
import com.booking.recruitment.hotel.repository.*;
import com.booking.recruitment.hotel.util.HaversineFormula;
import com.booking.recruitment.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
class DefaultHotelService implements HotelService {
  private final HotelRepository hotelRepository;
  CityRepository cityRepository;
  @Autowired
  DefaultHotelService(HotelRepository hotelRepository,CityRepository cityRepository) {
    this.hotelRepository = hotelRepository;
    this.cityRepository=cityRepository;
  }
  
  DefaultHotelService(HotelRepository hotelRepository){
	  this.hotelRepository=hotelRepository;
  }

  @Override
  public List<Hotel> getAllHotels() {
    return hotelRepository.findAll();
  }

  @Override
  public List<Hotel> getHotelsByCity(Long cityId) {
    return hotelRepository.findAll().stream()
        .filter((hotel) -> cityId.equals(hotel.getCity().getId()))
        .collect(Collectors.toList());
  }

  @Override
  public Hotel createNewHotel(Hotel hotel) {
    if (hotel.getId() != null) {
      throw new BadRequestException("The ID must not be provided when creating a new Hotel");
    }

    return hotelRepository.save(hotel);
  }

@Override
public List<Hotel> getHotelsbyCityId(Long cityId) {
	// TODO Auto-generated method stub
	
	return hotelRepository.findAll().stream().filter((hotel)->cityId.equals(hotel.getCity().getId())).collect(Collectors.toList());
}

@Override
public Hotel getHotelbyHotelId(Long id) {
	Optional<Hotel>hotelOptions=hotelRepository.findById(id);
	if(hotelOptions.isPresent())
		return hotelOptions.get();
	
	// TODO Auto-generated method stub
	return Hotel.builder().build();
}

@Override
public void deleteHotelbyHotelId(Long id) {
	// TODO Auto-generated method stub
	Optional<Hotel>hotelOptions=hotelRepository.findById(id);
	if(hotelOptions.isPresent()) {
		Hotel hotel=hotelOptions.get();
		hotel.setDeleted(true);	
		hotelRepository.saveAndFlush(hotel);
	}	
}

@Override
public List<Hotel> findingBestHotelsforCustomers(Long cityId, Double distance) {
	// TODO Auto-generated method stub
	List<Hotel>listofHotel=hotelRepository.findingHotelswithinDistance(cityId);
	List<Hotel>hotelListSorted=listofHotel.stream().filter(hotel->HaversineFormula.calculateDistance(hotel.getLatitude(),hotel.getLongitude(),hotel.getLatitude(),hotel.getLongitude())<distance).sorted((h1,h2)->{
		if((HaversineFormula.calculateDistance(h1.getLatitude(), h1.getLongitude(), h1.getLatitude(),h1.getLongitude()))>(HaversineFormula.calculateDistance(h2.getLatitude(),h2.getLongitude(),h1.getLongitude(),h2.getLongitude())))
			  return 1;
		else
			return -1;
	}).collect(Collectors.toList());
	List<Hotel> top_3_hotels=new ArrayList<>();
	if(!hotelListSorted.isEmpty()) {
		for(int i=0,j=0;i<hotelListSorted.size() && j<3;i++,j++) {
			top_3_hotels.add(hotelListSorted.get(i));
		}
	}
	return top_3_hotels;
}
  
}
