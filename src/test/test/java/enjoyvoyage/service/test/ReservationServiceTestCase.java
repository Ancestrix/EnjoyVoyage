package enjoyvoyage.service.test;

import hei.enjoyvoyage.dao.impl.DataSourceProvider;
import hei.enjoyvoyage.entities.Hotel;
import hei.enjoyvoyage.service.ReservationService;
import hei.enjoyvoyage.utils.MotDePasseUtils;
import hei.enjoyvoyage.dao.impl.ReservationDaoImpl;
import hei.enjoyvoyage.utils.Verification;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.Assert.fail;

@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceTestCase {

    @InjectMocks
    private ReservationService reservationService;

    @Mock
    private ReservationDaoImpl reservationDao;

    @Mock
    private Verification verification;

    @Mock
    private Hotel hotel;


    

    @Test
    public void shouldListReservation() {
        //GIVEN
        String id_user="1";
        //WHEN
        List<Hotel> reservations = reservationService.listReservation(id_user);
        //THEN
        Mockito.verify(reservationDao,Mockito.times(1)).listReservation(id_user);



    }


    @Test
    public void shouldAddReservation() throws Exception {
        //GIVEN
        String id_user = "3";
        String id_hotel ="1";
        // WHEN
        reservationService.addReservation(id_user,id_hotel);
        // THEN
        Mockito.verify(reservationDao,Mockito.times(1)).addReservation(id_user,id_hotel);

    }

    @Test
    public void shouldDeleteReservation() {
        //GIVEN
        String id_user = "3";
        String id_hotel ="1";
        //WHEN
        reservationService.deleteReservation(id_user,id_hotel);
        //THEN
        Mockito.verify(reservationDao,Mockito.times(1)).deleteReservation(id_user,id_hotel);

    }

}
