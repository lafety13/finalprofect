package ua.booking.entities;

import java.util.Objects;

public class Room {
    private int id;
    private int price;
    private int person;
    private User booked = null;

    public Room(int id, int price, int person) {
        this.id = id;
        this.price = price;
        this.person = person;
    }

    public boolean toSettle(User user) {
        setBooked(user);
        return true;
    }

    public boolean toEvict() {
        setBooked(null);
        return true;
    }

    public boolean isOccupiedRoom() {
        return !Objects.isNull(booked);
    }

    public boolean isOccupiedRoom(User user) {
        return !Objects.isNull(booked) && booked.equals(user);
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

    public User getBooked() {
        return booked;
    }

    public void setBooked(User booked) {
        this.booked = booked;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", price=" + price +
                ", person=" + person +
                ", booked=" + booked +
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
        return booked != null ? booked.equals(room.booked) : room.booked == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + price;
        result = 31 * result + person;
        result = 31 * result + (booked != null ? booked.hashCode() : 0);
        return result;
    }
}
