package ua.booking;

import ua.booking.api.Api;
import ua.booking.api.HotelSearchApi;
import ua.booking.dao.Dao;
import ua.booking.dao.ReservationDao;
import ua.booking.dao.UserDao;
import ua.booking.entities.*;
import java.util.List;
import java.util.Map;

public class Controller {
    private static int bookedId = 0;
    private UserManager userManager = new UserManager();
    private Dao<User> userDao = new UserDao<>();
    private Dao<BookedRoom> hotelDao = new ReservationDao<>();
    private Api api = new HotelSearchApi();

    public void bookRoom(int hotelId, int roomId) {
        try {
            Hotel hotel = findHotelById(hotelId);
            Room room = hotel.findRoomById(roomId);
            hotel.book(room, userManager.getUser());
            saveReservation(new BookedRoom(bookedId++, hotel, room, userManager.getUser()));
        } catch (NotFoundException e) {
            System.err.println(e.getMessage());
        } catch (AuthException e) {
            System.err.println(e.getMessage());
        }
    }

    private boolean saveReservation(BookedRoom bookedRoom) {
        return hotelDao.save(bookedRoom);
    }

    public void cancelReservation(int hotelId, int roomId) {
        try {
            Hotel hotel = findHotelById(hotelId);
            Room room = hotel.findRoomById(roomId);
            if (room.isOccupiedRoom(userManager.getUser()))
                hotel.cancelResevation(room, userManager.getUser());
            deleteReservation(hotel, room, userManager.getUser());

        } catch (NotFoundException e) {
            System.err.println(e.getMessage());
        } catch (AuthException e) {
            System.err.println(e.getMessage());
        }

    }

    private void deleteReservation(Hotel hotel, Room room, User user) {
        hotelDao.getAll().removeIf(bookedRoom ->
                bookedRoom.getHotel().equals(hotel) &&
                        bookedRoom.getRoom().equals(room) &&
                        bookedRoom.getUser().equals(user));
    }

    public Map<Hotel, List<Room>> findRoom(Map<String, String> params) {
        return api.findRoom(params);
    }

    public Hotel findHotelById(int id) throws NotFoundException {
        return api.findHotelById(id);
    }

    public List<Hotel> findHotelByName(String name) {
        return api.findHotelByName(name);
    }

    public List<Hotel> findHotelByCity(String city) {
        return api.findHotelByCity(city);
    }

    public boolean registerUser(User user) {
        return !userDao.isExist(user) && userDao.save(user);
    }

    public boolean Authorization(User user) {
        if (userDao.isExist(user)) {
            userManager.setAuthUser(user);
            return true;
        }
        return false;
    }

    public void showBookedRoom() {
        try {
            User user = userManager.getUser();
            hotelDao.getAll().stream()
                    .filter(bookedRoom -> bookedRoom.getUser().equals(user))
                    .forEach(System.out::println);
        } catch (AuthException e) {
            System.err.println(e.getMessage());
        }

    }

}
