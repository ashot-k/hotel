package com.hotel.service;

import com.hotel.entity.room.Room;
import com.hotel.entity.user.Person;
import com.hotel.repo.RoomRepository;
import com.hotel.utils.ExceptionMessages;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService{
    RoomRepository roomRepo;

    public RoomServiceImpl(RoomRepository roomRepo){
        this.roomRepo = roomRepo;
    }

    @Override
    public Room getRoomById(Long id) {
       return roomRepo.findById(id).orElseThrow(() -> new EntityNotFoundException(
               ExceptionMessages.EntityNotFoundMessage(Room.class.getSimpleName(), id)));
    }

    @Override
    public List<Room> findAllRooms() {
        return roomRepo.findAll();
    }

    @Override
    public Room saveRoom(Room room) {
        return roomRepo.save(room);
    }

    @Override
    public String deleteRoom(Long id) {
        return roomRepo.findById(id).map(p -> {
            roomRepo.delete(p);
            return "Deleted Room with id " + id;
        }).orElseGet(() -> "Could not find Room with id " + id);
    }

    @Override
    public Room updateRoom(Long id, Room updatedRoom) {
        return null;
    }
}
