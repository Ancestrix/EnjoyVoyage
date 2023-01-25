package hei.enjoyvoyage.dao;

import hei.enjoyvoyage.entities.Hotel;

import java.util.List;

    public interface ReservationDao {

        public void addReservation(String id_user, String id_hotel) ;

        public void deleteReservation(String id_user, String id_hotel);

        List<Hotel> listReservation(String id_user);
    }

