package ua.booking.entities;

public class BookedRoom {
    private int id;
    private Hotel hotel;
    private User user;
    private Room room;

    public BookedRoom(int id, Hotel hotel, Room room, User user) {
        this.id = id;
        this.hotel = hotel;
        this.user = user;
        this.room = room;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "BookedRoom{" +
                "id=" + id +
                ", hotel=" + hotel.getHotelName() +
                ", user=" + user.getName() +
                ", room=" + room +

                '}';
    }
}
