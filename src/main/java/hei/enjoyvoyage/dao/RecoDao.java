package hei.enjoyvoyage.dao;

import hei.enjoyvoyage.entities.Hotel;

import java.util.List;

public interface RecoDao {


    public void addReco(String id_hotel);

    List<Hotel> listReco();
}

