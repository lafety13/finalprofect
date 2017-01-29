package ua.booking.api;

import ua.booking.entities.NotFoundException;
import ua.booking.entities.Hotel;
import ua.booking.entities.Room;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HotelSearchApi extends Api {

    public HotelSearchApi() {
        super();
    }

    @Override
    public Map<Hotel, List<Room>> findRoom(Map<String, String> params) {
        Map<Hotel, List<Room>> foundRooms = getHotelList().stream()
                .filter(hotel ->
                        !Objects.isNull(params.get("hotelname")) &&
                                hotel.getHotelName().toLowerCase().equals(params.get("hotelname")) ||
                                Objects.isNull(params.get("hotelname")))
                .filter(hotel ->
                        !Objects.isNull(params.get("city")) &&
                                hotel.getCity().toLowerCase().equals(params.get("city")) ||
                                Objects.isNull(params.get("city")))
                .collect(Collectors.toMap(Function.identity(), hotel ->
                        hotel.getAllRoom().stream()
                                .filter(room ->
                                        !Objects.isNull(params.get("person")) &&
                                                Integer.valueOf(params.get("person")).equals(room.getPerson()) ||
                                                Objects.isNull(params.get("person")))
                                .filter(room1 ->
                                        !Objects.isNull(params.get("price")) &&
                                                Integer.valueOf(params.get("price")).equals(room1.getPrice()) ||
                                                Objects.isNull(params.get("price")))
                                .collect(Collectors.toList()))
                );

        foundRooms.entrySet().removeIf(hotelListEntry -> hotelListEntry.getValue().size() == 0);

        return foundRooms;
    }

    @Override
    public List<Hotel> findHotelByName(String name) {
        return getHotelList().stream()
                .filter(hotel -> hotel.getHotelName().toLowerCase().equals(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Hotel> findHotelByCity(String city) {
        return getHotelList().stream()
                .filter(hotel -> hotel.getCity().toLowerCase().equals(city.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public Hotel findHotelById(int id) throws NotFoundException {
        Optional<Hotel> hotelOptional = getHotelList().stream()
                .filter(hotel -> hotel.getId() == id)
                .reduce((hotel, hotel2) -> hotel);
        if (!hotelOptional.isPresent()) throw new NotFoundException("Hotel was not found");
        return hotelOptional.get();
    }
}
