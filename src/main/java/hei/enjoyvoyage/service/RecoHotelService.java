package hei.enjoyvoyage.service;

import hei.enjoyvoyage.dao.RecoDao;
import hei.enjoyvoyage.dao.impl.RecoDaoImpl;
import hei.enjoyvoyage.entities.Hotel;

import java.util.List;

public class RecoHotelService {

    private static class RecoHotelLibraryHolder {
        private final static RecoHotelService instance = new RecoHotelService();
    }

    public static RecoHotelService getInstance() {
        return RecoHotelService.RecoHotelLibraryHolder.instance;
    }

    private RecoDao recoDao = new RecoDaoImpl();

    public List<Hotel> listReco() {
        return recoDao.listReco() ;
    }

    public void addReco(String id_hotel) {
        recoDao.addReco(id_hotel);
    }
}
