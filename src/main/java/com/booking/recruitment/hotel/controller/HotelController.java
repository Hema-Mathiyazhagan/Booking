import com.booking.recruitment.hotel.model.Hotel;
import com.booking.recruitment.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {
  private final HotelService hotelService;

  @Autowired
  public HotelController(HotelService hotelService) {
    this.hotelService = hotelService;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Hotel> getAllHotels() {
    return hotelService.getAllHotels();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Hotel createHotel(@RequestBody Hotel hotel) {
    return hotelService.createNewHotel(hotel);
  }
  
  @GetMapping("/{id}")
  @ResponseBody
  public Hotel getHotelbyHotelId(@PathVariable("id")Long id) {
	  return hotelService.getHotelbyHotelId(id);
  }
  
  @PatchMapping(value="/{id}")
  @ResponseBody
  public void deleteHotelbyHotelId(@PathVariable("id") Long id) {
	  hotelService.deleteHotelbyHotelId(id);
  }
  
  @GetMapping(value="/search/{cityId}")
  @ResponseBody
  public List<Hotel> findingBestHotelsforCustomers(@PathVariable("cityId")Long cityId,@RequestParam("sortBy") Double distance){
	  return hotelService.findingBestHotelsforCustomers(cityId, distance);
  }
}
