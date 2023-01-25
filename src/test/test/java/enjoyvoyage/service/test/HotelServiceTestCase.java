package enjoyvoyage.service.test;

import hei.enjoyvoyage.dao.impl.DataSourceProvider;
import hei.enjoyvoyage.dao.impl.HotelDaoImpl;
import hei.enjoyvoyage.entities.Hotel;
import hei.enjoyvoyage.service.HotelService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

@RunWith(MockitoJUnitRunner.class)
public class HotelServiceTestCase {

    @InjectMocks
    private HotelService hotelService;



    @Mock
    private HotelDaoImpl hotelDao;


    @Test
    public void shouldDernierAjoutHotels() {
        //GIVEN
        //WHEN
        List<Hotel> hotels = hotelService.DernierAjoutHotels();
        //THEN
        Mockito.verify(hotelDao,Mockito.times(1)).DernierAjoutHotels();

    }

    @Test
    public void shouldListRechercheHotel() {
        //GIVEN
        String destination = "test";
        //WHEN
        List<Hotel> hotels = hotelService.listRechercheHotels(destination);
        //THEN
        Mockito.verify(hotelDao,Mockito.times(1)).listRechercheHotels(destination);

    }

    @Test
    public void shouldDeleteHotel() {
        //GIVEN
        String id = "3";
        //WHEN
        hotelService.deleteHotel(id);
        //THEN
        Mockito.verify(hotelDao,Mockito.times(1)).deleteHotel(id);

    }

    @Test
    public void shouldListHotel() {
        //GIVEN
        //WHEN
        List<Hotel> hotels = hotelService.listHotels();
        //THEN
        Mockito.verify(hotelDao,Mockito.times(1)).listHotels();

    }

    @Test
    public void shouldGetHotel() {
        //GIVEN
        Integer id = 1;
        // WHEN
        Hotel hotel = hotelService.getHotel(1);
        // THEN
        Mockito.verify(hotelDao,Mockito.times(1)).getHotel(id);

    }

    @Test
    public void shouldAddHotel() {
        //GIVEN
        Hotel hoteltest = new Hotel(3,"d","d","d","d",1.0,"d");
        // WHEN
        Hotel hotel = hotelService.addHotel(hoteltest);
        // THEN
        Mockito.verify(hotelDao,Mockito.times(1)).addHotel(hoteltest);

    }

    @Test(expected=IllegalArgumentException.class)
    public void shouldThrowWhenHotelNull() throws IllegalArgumentException{
        //GIVEN
        Hotel hoteltest=null;
        //WHEN
        Hotel hotel = hotelService.addHotel(hoteltest);
        //THEN
        
    }

    @Mock
    Hotel hotel;

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenGetNomNull() throws IllegalArgumentException{
        //GIVEN
        Hotel hoteltest = new Hotel(3,null,"d","d","d",1.0,"d");
        //WHEN
        Hotel test = hotelService.addHotel(hoteltest);
        //THEN
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenGetNomEmpty() throws IllegalArgumentException{
        //GIVEN
        Hotel hoteltest = new Hotel(3,"","d","d","d",1.0,"d");
        //WHEN
        Hotel test = hotelService.addHotel(hoteltest);
        //THEN
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenGetVilleNull() throws IllegalArgumentException{
        //GIVEN
        Hotel hoteltest = new Hotel(3,"d",null,"d","d",1.0,"d");
        //WHEN
        Hotel test = hotelService.addHotel(hoteltest);
        //THEN

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenGetVilleEmpty() throws IllegalArgumentException{
        //GIVEN
        Hotel hoteltest = new Hotel(3,"d","","d","d",1.0,"d");
        //WHEN
        Hotel test = hotelService.addHotel(hoteltest);
        //THEN

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenGetPaysNull() throws IllegalArgumentException{
        //GIVEN
        Hotel hoteltest = new Hotel(3,"d","d",null,"d",1.0,"d");
        //WHEN
        Hotel test = hotelService.addHotel(hoteltest);
        //THEN
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenGetDescriptionEmpty() throws IllegalArgumentException{
        //GIVEN
        Hotel hoteltest = new Hotel(3,"d","d","","d",1.0,"d");
        //WHEN
        Hotel test = hotelService.addHotel(hoteltest);
        //THEN

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenGetDescriptionNull() throws IllegalArgumentException{
        //GIVEN
        Hotel hoteltest = new Hotel(3,"d","d","",null,1.0,"d");
        //WHEN
        Hotel test = hotelService.addHotel(hoteltest);
        //THEN

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenGetPrixNull() throws IllegalArgumentException{
        //GIVEN
        Hotel hoteltest = new Hotel(3,"d","d","d","d",null,"d");
        //WHEN
        Hotel test = hotelService.addHotel(hoteltest);
        //THEN
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenGetPrixEmpty() throws IllegalArgumentException{
        //GIVEN
        Hotel hoteltest = new Hotel(3,"d","d","d","",0.0,"d");
        //WHEN
        Hotel test = hotelService.addHotel(hoteltest);
        //THEN
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenGetPhotoNull() throws IllegalArgumentException{
        //GIVEN
        Hotel hoteltest = new Hotel(3,"d","","d","d",1.0,null);
        //WHEN
        Hotel test = hotelService.addHotel(hoteltest);
        //THEN

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenGetPhotoEmpty() throws IllegalArgumentException{
        //GIVEN
        Hotel hoteltest = new Hotel(3,"d","d","d","d",1.0,"");
        //WHEN
        Hotel test = hotelService.addHotel(hoteltest);
        //THEN

    }
    @Test(expected=IllegalArgumentException.class)
    public void shouldThrowWhenHotelNullForModifhotel() throws IllegalArgumentException{
        //GIVEN
        Hotel hoteltest=null;
        Integer id = 1;
        //WHEN
        Hotel hotel = hotelService.modifHotel(hoteltest,id);
        //THEN
        
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenGetNomNullForModifhotel() throws IllegalArgumentException{
        //GIVEN
        Hotel hoteltest = new Hotel(3,null,"d","d","d",1.0,"d");
        Integer id = 3;
        //WHEN
        Hotel test = hotelService.modifHotel(hoteltest,id);
        //THEN
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenGetNomEmptyForModifhotel() throws IllegalArgumentException{
        //GIVEN
        Hotel hoteltest = new Hotel(3,"","d","d","d",1.0,"d");
        Integer id = 3;
        //WHEN
        Hotel test = hotelService.modifHotel(hoteltest,id);
        //THEN
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenGetVilleNullForModifhotel() throws IllegalArgumentException{
        //GIVEN
        Hotel hoteltest = new Hotel(3,"d",null,"d","d",1.0,"d");
        Integer id = 3;
        //WHEN
        Hotel test = hotelService.modifHotel(hoteltest,id);
        //THEN
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenGetVilleEmptyForModifhotel() throws IllegalArgumentException{
        //GIVEN
        Hotel hoteltest = new Hotel(3,"d","","d","d",1.0,"d");
        Integer id = 3;
        //WHEN
        Hotel test = hotelService.modifHotel(hoteltest,id);
        //THEN
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenGetPaysNullForModifhotel() throws IllegalArgumentException{
        //GIVEN
        Hotel hoteltest = new Hotel(3,"d","d",null,"d",1.0,"d");
        Integer id = 3;
        //WHEN
        Hotel test = hotelService.modifHotel(hoteltest,id);
        //THEN
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenGetDescriptionEmptyForModifhotel() throws IllegalArgumentException{
        //GIVEN
        Hotel hoteltest = new Hotel(3,"d","d","","d",1.0,"d");
        Integer id = 3;
        //WHEN
        Hotel test = hotelService.modifHotel(hoteltest,id);
        //THEN
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenGetDescriptionNullForModifhotel() throws IllegalArgumentException{
        //GIVEN
        Hotel hoteltest = new Hotel(3,"d","d","d",null,1.0,"d");
        Integer id = 3;
        //WHEN
        Hotel test = hotelService.modifHotel(hoteltest,id);
        //THEN

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenGetPrixNullForModifhotel() throws IllegalArgumentException{
        //GIVEN
        Hotel hoteltest = new Hotel(3,"d","d","d","d",null,"d");
        Integer id = 3;
        //WHEN
        Hotel test = hotelService.modifHotel(hoteltest,id);
        //THEN
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenGetPrixEmptyForModifhotel() throws IllegalArgumentException{
        //GIVEN
        Hotel hoteltest = new Hotel(3,"d","d","d","",0.0,"d");
        Integer id = 3;
        //WHEN
        Hotel test = hotelService.modifHotel(hoteltest,id);
        //THEN
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenGetPhotoNullForModifhotel() throws IllegalArgumentException{
        //GIVEN
        Hotel hoteltest = new Hotel(3,"d","","d","d",1.0,null);
        Integer id = 3;
        //WHEN
        Hotel test = hotelService.modifHotel(hoteltest,id);
        //THEN

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenGetPhotoEmptyForModifhotel() throws IllegalArgumentException{
        //GIVEN
        Hotel hoteltest = new Hotel(3,"d","d","d","d",1.0,"");
        Integer id = 3;
        //WHEN
        Hotel test = hotelService.modifHotel(hoteltest,id);
        //THEN

    }

    @Test
    public void shouldModifHotel() throws Exception {
        //GIVEN
        Hotel hoteltest = new Hotel(3,"d","d","d","d",1.0,"d");
        Integer id = 3;
        // WHEN
        Hotel hotel = hotelService.modifHotel(hoteltest,id);
        // THEN
        Mockito.verify(hotelDao,Mockito.times(1)).modifHotel(hoteltest,id);

    }


}
