package ua.booking.entities;

import java.util.Objects;

public class Room {
    private int id;
    private int price;
    private int person;
    private User userReserved = null;

    public Room(int id, int price, int person) {
        this.id = id;
        this.price = price;
        this.person = person;
    }

    public void toSettle(User user) {
        setUserReserved(user);
    }

    public void toEvict() {
        setUserReserved(null);
    }

    public boolean isOccupiedRoom() {
        return !Objects.isNull(userReserved);
    }

    public boolean isOccupiedRoom(User user) {
        return !Objects.isNull(userReserved) && userReserved.equals(user);
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    public User getUserReserved() {
        return userReserved;
    }

    public void setUserReserved(User userReserved) {
        this.userReserved = userReserved;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", price=" + price +
                ", person=" + person +
                ", userReserved=" + userReserved +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (id != room.id) return false;
        if (price != room.price) return false;
        if (person != room.person) return false;
        return userReserved != null ? userReserved.equals(room.userReserved) : room.userReserved == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + price;
        result = 31 * result + person;
        result = 31 * result + (userReserved != null ? userReserved.hashCode() : 0);
        return result;
    }
}
