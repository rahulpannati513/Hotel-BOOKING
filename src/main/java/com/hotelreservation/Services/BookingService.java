package com.hotelreservation.Services;

import com.hotelreservation.Model.Booking;

import com.hotelreservation.Repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public Booking updateBooking(Long id, Booking bookingDetails) {
        Optional<Booking> existingBooking = bookingRepository.findById(id);
        if (existingBooking.isPresent()) {
            Booking booking = existingBooking.get();
            booking.setUserId(bookingDetails.getUserId());
            booking.setRoomId(bookingDetails.getRoomId());
            booking.setRoomNumber(bookingDetails.getRoomNumber());
            booking.setRoomType(bookingDetails.getRoomType());
            booking.setPrice(bookingDetails.getPrice());
            return bookingRepository.save(booking);
        }
        return null;
    }

    public boolean deleteBooking(Long id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
