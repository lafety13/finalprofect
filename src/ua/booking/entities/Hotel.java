package ua.booking.entities;

import java.util.List;
import java.util.Optional;

public class Hotel {
    private int id;
    private String hotelName;
    private String city;
    private List<Room> roomList;

    public Hotel(int id, String hotelName, String city, List<Room> roomList) {
        this.id = id;
        this.hotelName = hotelName;
        this.city = city;
        this.roomList = roomList;
    }

    public Room findRoomById(int id) throws NotFoundException {
        Optional<Room> roomOptional = roomList.stream()
                .filter(room -> room.getId() == id)
                .reduce((room, room2) -> room);
        if (!roomOptional.isPresent())
            throw new NotFoundException("Room was not found");
        return roomOptional.get();
    }

    public void book(Room room, User user) {
        roomList.get(room.getId()).toSettle(user);
    }

    public void cancelResevation(Room room, User user) {
        room.toEvict();
    }

    public List<Room> getAllRoom() {
        return roomList;
    }

    public int getId() {
        return id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", hotelName='" + hotelName + '\'' +
                ", city='" + city + '\'' +
                ", roomList=" + roomList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hotel hotel = (Hotel) o;

        if (id != hotel.id) return false;
        if (hotelName != null ? !hotelName.equals(hotel.hotelName) : hotel.hotelName != null) return false;
        if (city != null ? !city.equals(hotel.city) : hotel.city != null) return false;
        return roomList != null ? roomList.equals(hotel.roomList) : hotel.roomList == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (hotelName != null ? hotelName.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (roomList != null ? roomList.hashCode() : 0);
        return result;
    }
}
