package com.hotelreservation.Services;

import com.hotelreservation.Model.Room;
import com.hotelreservation.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    // Create a new room
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    // Get all rooms
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // Get available rooms
    public List<Room> getAvailableRooms() {
        return roomRepository.findByAvailableTrue();
    }

    // Get a room by ID
    public Optional<Room> getRoomById(Long id) {
        return roomRepository.findById(id);
    }

    // Update a room
    public Room updateRoom(Long id, Room roomDetails) {
        if (roomRepository.existsById(id)) {
            roomDetails.setId(id);
            return roomRepository.save(roomDetails);
        }
        return null; // Room not found
    }

    // Delete a room
    public boolean deleteRoom(Long id) {
        if (roomRepository.existsById(id)) {
            roomRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
