package ua.booking.api;

import ua.booking.entities.NotFoundException;
import ua.booking.entities.Hotel;
import ua.booking.entities.Room;

import java.util.List;
import java.util.Map;

public abstract class Api {
    public abstract Map<Hotel, List<Room>> findRoom(Map<String, String> params);
    public abstract List<Hotel> findHotelByName(String name);
    public abstract List<Hotel> findHotelByCity(String city);
    public abstract Hotel findHotelById(int id) throws NotFoundException;
}
