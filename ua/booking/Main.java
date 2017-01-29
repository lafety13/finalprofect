package ua.booking;

import ua.booking.api.HotelSearchApi;
import ua.booking.entities.User;

import java.util.HashMap;
import java.util.Map;

public class Main {
    private static int userId = 0;
    private static Controller controller;
    private static User user;

    public void init() {
        controller = new Controller(new HotelSearchApi());
        user = new User(userId++, "Ivan", 25);
    }

    public void run() {
        controller.registerUser(user);
        controller.authorization(user);

        System.out.println("\n____________Searching room_____________\n");
        Map<String, String> params = new HashMap<>();
        params.put("city".toLowerCase(), "Kiev".toLowerCase());
        //params.put("hotelName".toLowerCase(), "Mirror".toLowerCase());
        params.put("price".toLowerCase(), "2000");
        //params.put("person".toLowerCase(), "50");

        controller.findRoom(params).forEach((hotel, roomList) -> {
            System.out.println(hotel.getHotelName());
            roomList.forEach(System.out::println);
        });

        System.out.println("\n_____________find hotel by city_____________\n");
        controller.findHotelByCity("Kiev").forEach(hotel -> {
            System.out.println("Id: " + hotel.getId() + " name: " + hotel.getHotelName() + " city: " + hotel.getCity());
            hotel.getAllRoom().forEach(System.out::println);
        });

        System.out.println("\n_____________Show booked room_____________\n");
        controller.bookRoom(0, 0);
        controller.bookRoom(1, 2);
        controller.showBookedRoom();
        System.out.println();

        System.out.println("\n_____________Show booked room_____________\n");
        controller.cancelReservation(0, 0);
        controller.showBookedRoom();

    }

    public static void main(String[] args) {
        Main app = new Main();
        app.init();
        app.run();
    }
}
