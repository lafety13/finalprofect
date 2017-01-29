package ua.booking.api;

import ua.booking.entities.Hotel;
import ua.booking.entities.NotFoundException;
import ua.booking.entities.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Api {
    private static int hotelId = 0;
    private List<Hotel> hotelList = new ArrayList<>();

    public Api() {
        int roomId = 0;
        List<Room> roomList = new ArrayList<>();
        roomList.add(new Room(roomId++, 2000, 2));
        roomList.add(new Room(roomId++, 500, 1));
        roomList.add(new Room(roomId++, 6000, 5));

        roomId = 0;
        List<Room> roomList2 = new ArrayList<>();
        roomList2.add(new Room(roomId++, 2000, 2));
        roomList2.add(new Room(roomId++, 150, 1));
        roomList2.add(new Room(roomId++, 3333, 5));

        Hotel hotel = new Hotel(hotelId++, "Mirror", "Kiev", roomList);
        Hotel hote2 = new Hotel(hotelId++, "Tasty", "Kiev", roomList2);
        hotelList.add(hotel);
        hotelList.add(hote2);

    }

    public List<Hotel> getHotelList() {
        return hotelList;
    }

    public abstract Map<Hotel, List<Room>> findRoom(Map<String, String> params);

    public abstract List<Hotel> findHotelByName(String name);

    public abstract List<Hotel> findHotelByCity(String city);

    public abstract Hotel findHotelById(int id) throws NotFoundException;
}
