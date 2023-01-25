package hei.enjoyvoyage.service;

import hei.enjoyvoyage.dao.HotelDao;
import hei.enjoyvoyage.dao.ReservationDao;
import hei.enjoyvoyage.dao.impl.HotelDaoImpl;
import hei.enjoyvoyage.dao.impl.ReservationDaoImpl;
import hei.enjoyvoyage.entities.Hotel;
import hei.enjoyvoyage.utils.Verification;

import java.util.List;

public class ReservationService {


    private static class ReservationLibraryHolder {
        private final static ReservationService instance = new ReservationService();
    }

    public static ReservationService getInstance() {
        return ReservationService.ReservationLibraryHolder.instance;
    }


    private ReservationDao reservationDao = new ReservationDaoImpl();

    public void addReservation(String id_user, String id_hotel){

        reservationDao.addReservation(id_user, id_hotel);

    }

    public void deleteReservation(String id_user, String id_hotel){
        reservationDao.deleteReservation(id_user,id_hotel);
    }

    public List<Hotel> listReservation(String id_user){
        return reservationDao.listReservation(id_user);
    }
}
