package enjoyvoyage.service.test;

import hei.enjoyvoyage.dao.impl.DataSourceProvider;
import hei.enjoyvoyage.entities.Hotel;
import hei.enjoyvoyage.service.RecoHotelService;
import hei.enjoyvoyage.utils.MotDePasseUtils;
import hei.enjoyvoyage.dao.impl.RecoDaoImpl;
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
public class RecoHotelServiceTestCase {

    @InjectMocks
    private RecoHotelService recoHotelService;

    @Mock
    private RecoDaoImpl recoHotelDao;

    @Mock
    private Verification verification;

    @Mock
    private Hotel hotel;




    @Test
    public void shouldListReco() {
        //GIVEN
        //WHEN
        List<Hotel> recoHotels = recoHotelService.listReco();
        //THEN
        Mockito.verify(recoHotelDao,Mockito.times(1)).listReco();



    }


    @Test
    public void shouldAddReco(){
        //GIVEN
        String id_hotel ="1";
        // WHEN
        recoHotelService.addReco(id_hotel);
        // THEN
        Mockito.verify(recoHotelDao,Mockito.times(1)).addReco(id_hotel);

    }

}
