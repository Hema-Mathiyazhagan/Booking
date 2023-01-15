import com.booking.recruitment.hotel.model.Hotel;
import com.booking.recruitment.hotel.service.HotelService;
import com.booking.recruitment.hotel.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getHotelbyHotelId(@PathVariable("id")Long id) {
        Hotel hotel=hotelService.getHotelbyHotelId(id);
        return new ResponseEntity<>(hotel,HttpStatus.OK);
    }

    @PatchMapping(value="/{id}")
    @ResponseBody
    public ResponseEntity<?>  deleteHotelbyHotelId(@PathVariable("id") Long id) {
        hotelService.deleteHotelbyHotelId(id);
        return new ResponseEntity<>("Deleted",HttpStatus.NO_CONTENT);
    }

    @GetMapping(value="/search/{cityId}")
    @ResponseBody
    public ResponseEntity<?> findingBestHotelsforCustomers(@PathVariable("cityId")Long cityId,@RequestParam("sortBy") Double distance){
        List<Hotel>list= hotelService.findingBestHotelsforCustomers(cityId, distance);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}
