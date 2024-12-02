package com.hotelreservation.Services;

import com.hotelreservation.Model.Hotel;

import com.hotelreservation.Repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Optional<Hotel> getHotelById(Integer id) {
        Optional<Hotel> result = hotelRepository.findById(id);
        System.out.println(result);
        return result;

    }

    public Hotel updateHotel(Integer id, Hotel hotelDetails) {
        Optional<Hotel> existingHotel = hotelRepository.findById(id);
        if (existingHotel.isPresent()) {
            Hotel hotel = existingHotel.get();
            hotel.setName(hotelDetails.getName());
            hotel.setLocation(hotelDetails.getLocation());
            hotel.setDescription(hotelDetails.getDescription());
            hotel.setContactNumber(hotelDetails.getContactNumber());
            return hotelRepository.save(hotel);
        }
        return null;
    }

    public boolean deleteHotel(Integer id) {
        if (hotelRepository.existsById(id)) {
            hotelRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
