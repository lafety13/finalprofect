package ua.booking;

import ua.booking.api.Api;
import ua.booking.api.HotelSearchApi;
import ua.booking.dao.Dao;
import ua.booking.dao.ReservationDao;
import ua.booking.dao.UserDao;
import ua.booking.entities.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Controller {
    private static int bookedId = 0;
    private AuthManager userManager = new AuthManager();
    private Dao<User> userDao = new UserDao<>();
    private Dao<BookedRoom> hotelDao = new ReservationDao<>();
    private List<Api> listApi = new ArrayList<>();

    public Controller() {
        listApi.add(new HotelSearchApi());
    }

    public Controller(Api... arrayApi) {
        listApi = Arrays.stream(arrayApi)
                .collect(Collectors.toList());
    }

    public void bookRoom(int hotelId, int roomId) {
        try {
            Hotel hotel = findHotelById(hotelId);
            Room room = hotel.findRoomById(roomId);
            if (!room.isOccupiedRoom()) {
                hotel.book(room, userManager.getUser());
                saveReservation(new BookedRoom(bookedId++, hotel, room, userManager.getUser()));
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.INFO, "The room id: " + room.getId() + " is booked by someone. Chose something else");
            }
        } catch (NotFoundException | AuthException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, e.getMessage());
        }
    }

    private boolean saveReservation(BookedRoom bookedRoom) {
        return hotelDao.save(bookedRoom);
    }

    public void cancelReservation(int hotelId, int roomId) {
        try {
            Hotel hotel = findHotelById(hotelId);
            Room room = hotel.findRoomById(roomId);
            if (room.isOccupiedRoom(userManager.getUser())) {
                hotel.cancelResevation(room, userManager.getUser());
                deleteReservation(hotel, room, userManager.getUser());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.INFO,"The room with id " + roomId + " is not booked by " + userManager.getUser().getName());
            }
        } catch (NotFoundException | AuthException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, e.getMessage());
        }
    }

    private void deleteReservation(Hotel hotel, Room room, User user) {
        hotelDao.getAll().removeIf(bookedRoom ->
                bookedRoom.getHotel().equals(hotel) &&
                        bookedRoom.getRoom().equals(room) &&
                        bookedRoom.getUser().equals(user));
    }

    public Map<Hotel, List<Room>> findRoom(Map<String, String> params) {
        Map<Hotel, List<Room>> hotels = null;
        for (Api api : listApi) {
            hotels = api.findRoom(params);
        }
        return hotels;
    }

    public Hotel findHotelById(int id) throws NotFoundException {
        Hotel hotel = null;
        for (Api api : listApi) {
            hotel = api.findHotelById(id);
        }
        return hotel;
    }

    public List<Hotel> findHotelByName(String name) {
        List<Hotel> hotelList = null;
        for (Api api : listApi) {
            hotelList = api.findHotelByName(name);
        }
        return hotelList;
    }

    public List<Hotel> findHotelByCity(String city) {
        List<Hotel> hotelList = null;
        for (Api api : listApi) {
            hotelList = api.findHotelByCity(city);
        }
        return hotelList;
    }

    public boolean registerUser(User user) {
        return !userDao.isExist(user) && userDao.save(user);
    }

    public boolean authorization(User user) {
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
