package ua.booking;

import ua.booking.entities.User;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static int userId = 0;
    private static Controller controller;
    private static User user;

    public void init() {
        controller = new Controller();
        user = new User(userId++, "Ivan", 25);
    }

    public void run() {
        System.out.println(controller.registerUser(user));
        System.out.println(controller.Authorization(user));


        controller.findHotelByCity("Kiev").forEach(hotel -> {
            System.out.println(hotel.getHotelName());
            hotel.getAllRoom().forEach(System.out::println);
        });
        controller.bookRoom(0, 0);
        controller.showBookedRoom();

        controller.cancelReservation(0, 0);
        controller.showBookedRoom();

        Map<String, String> params = new HashMap<>();
        params.put("city", "kiev");
        params.put("price", "6000");
        //params.put("person", "50");

        controller.findRoom(params).forEach((hotel, roomList) -> {
            System.out.println(hotel.getHotelName());
            roomList.forEach(System.out::println);
        });
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.init();
        app.run();
    }
}
