package hei.enjoyvoyage.dao;

import hei.enjoyvoyage.entities.Hotel;

import java.util.List;

public interface HotelDao {
    public List<Hotel> listHotels();

    public List<Hotel> DernierAjoutHotels();

    public List<Hotel> listRechercheHotels(String destination);

    public Hotel getHotel(Integer id_hotel);

    public Hotel addHotel(Hotel hotel);

    public Hotel modifHotel(Hotel hotel, Integer id);
    //Integer id_hotel, String nom, String ville, String pays, String description, Integer prix, String photo)

    public void deleteHotel(String hotel);
}