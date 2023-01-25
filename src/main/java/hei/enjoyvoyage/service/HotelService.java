package hei.enjoyvoyage.service;

import hei.enjoyvoyage.dao.HotelDao;
import hei.enjoyvoyage.dao.impl.HotelDaoImpl;
import hei.enjoyvoyage.entities.Hotel;


import java.util.List;

public class HotelService {

    private static class HotelLibraryHolder {
        private final static HotelService instance = new HotelService();
    }

    public static HotelService getInstance() {
        return HotelService.HotelLibraryHolder.instance;
    }

    private HotelDao hotelDao = new HotelDaoImpl();

    private HotelService() {
    }
    public List<Hotel> listHotels() {
        return hotelDao.listHotels();
    }

    public List<Hotel> DernierAjoutHotels() {
        return hotelDao.DernierAjoutHotels();
    }

    public List<Hotel> listRechercheHotels(String destination) {
        return hotelDao.listRechercheHotels(destination);
    }

    public Hotel getHotel(Integer id_hotel) {
      return hotelDao.getHotel(id_hotel);
    }

    public void deleteHotel(String id_hotel) {
        hotelDao.deleteHotel(id_hotel);
    }


    public Hotel addHotel(Hotel hotel) {
        if (hotel == null) {
            throw new IllegalArgumentException("The hotel can not be null.");
        }
        if (hotel.getNom() == null || "".equals(hotel.getNom())) {
            throw new IllegalArgumentException("A hotel must have a nom.");
        }
        if (hotel.getVille() == null || "".equals(hotel.getVille())) {
            throw new IllegalArgumentException("A Hotel must have a ville.");
        }
        if (hotel.getPays() == null || "".equals(hotel.getPays())) {
            throw new IllegalArgumentException("A hotel must have a Pays.");
        }
        if (hotel.getDescription() == null || "".equals(hotel.getDescription())) {
            throw new IllegalArgumentException("A hotel must have a description.");
        }
        if (hotel.getPrix() == null || hotel.getPrix() == 0.0) {
            throw new IllegalArgumentException("A hotel must have a Prix.");
        }

        if (hotel.getPhoto() == null || "".equals(hotel.getPhoto())) {
            throw new IllegalArgumentException("A hotel must have a photo");
        }

        return hotelDao.addHotel(hotel);
    }

    public Hotel modifHotel(Hotel hotel, Integer id) {
        if (hotel == null) {
            throw new IllegalArgumentException("The hotel can not be null.");
        }
        if (hotel.getNom() == null || "".equals(hotel.getNom())) {
            throw new IllegalArgumentException("A hotel must have a nom.");
        }
        if (hotel.getVille() == null || "".equals(hotel.getVille())) {
            throw new IllegalArgumentException("A Hotel must have a ville.");
        }
        if (hotel.getPays() == null || "".equals(hotel.getPays())) {
            throw new IllegalArgumentException("A hotel must have a Pays.");
        }
        if (hotel.getDescription() == null || "".equals(hotel.getDescription())) {
            throw new IllegalArgumentException("A hotel must have a description.");
        }
        if (hotel.getPrix() == null || hotel.getPrix() == 0.0) {
            throw new IllegalArgumentException("A hotel must have a Prix.");
        }

        if (hotel.getPhoto() == null || "".equals(hotel.getPhoto())) {
            throw new IllegalArgumentException("A hotel must have a photo");
        }

        return hotelDao.modifHotel(hotel,id);
    }

}
