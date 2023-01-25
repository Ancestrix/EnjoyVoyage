package enjoyvoyage.service.test;

import hei.enjoyvoyage.dao.impl.DataSourceProvider;
import hei.enjoyvoyage.entities.User;
import hei.enjoyvoyage.exception.UserNotFoundException;
import hei.enjoyvoyage.service.UserService;
import hei.enjoyvoyage.utils.MotDePasseUtils;
import hei.enjoyvoyage.dao.impl.UserDaoImpl;
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
public class UserServiceTestCase {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserDaoImpl userDao;

    @Mock
    private Verification verification;

    @Mock
    private User user;





    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenGetUserIdFromEmail() throws IllegalArgumentException{
        //GIVEN
        String email = "";
        Mockito.when(verification.isEmpty(email)).thenReturn(true);
        //WHEN
        String userIdFromEmail = userDao.getUserIdFromEmail(email);
        //THEN
    }

    @Test
    public void shouldGetUserIdFromEmail(){
        //GIVEN
        String email = "test@test.fr";
        //WHEN
        String userIdFromEmail = userService.getUserIdFromEmail(email);
        //THEN
        Mockito.verify(userDao,Mockito.times(1)).getUserIdFromEmail(email);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenGetUserInfo() throws IllegalArgumentException{
        //GIVEN
        String idUserTest = "";
        //WHEN
        User userInfo = userService.getUserInfo(idUserTest);
        //THEN
    }

    @Test
    public void shouldGetUserInfo(){
        //GIVEN
        String idUserTest = "test@test.fr";
        //WHEN
        User userInfo = userService.getUserInfo(idUserTest);
        //THEN
        Mockito.verify(userDao,Mockito.times(1)).getUserInfo(idUserTest);
    }


    @Test
    public void shouldListUser() {
        //GIVEN
        //WHEN
        List<User> users = userService.listUsers();
        //THEN
        Mockito.verify(userDao,Mockito.times(1)).listUsers();



    }

    @Test
    public void shouldGetUser() {
        //GIVEN
        Integer id = 1;
        // WHEN
        User user = userService.getUser(1);
        // THEN
        Mockito.verify(userDao,Mockito.times(1)).getUser(id);

    }

    @Test(expected=IllegalArgumentException.class)
    public void shouldThrowWhenUserNull() throws IllegalArgumentException{
        //GIVEN
        User usertest=null;
        //WHEN
        User user = userService.addUser(usertest);
        //THEN

    }



    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenGetNomNull() throws IllegalArgumentException{
        //GIVEN
        User usertest = new User(3,null,"d","d","d",false);
        //WHEN
        User test = userService.addUser(usertest);
        //THEN

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenGetNomEmpty() throws IllegalArgumentException{
        //GIVEN
        User usertest = new User(3,"","d","d","d",false);
        //WHEN
        User test = userService.addUser(usertest);
        //THEN
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenGetPrenomNull() throws IllegalArgumentException{
        //GIVEN
        User usertest = new User(3,"d",null,"d","d",false);
        //WHEN
        User test = userService.addUser(usertest);
        //THEN
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenGetPrenomEmpty() throws IllegalArgumentException{
        //GIVEN
        User usertest = new User(3,"d","","d","d",false);
        //WHEN
        User test = userService.addUser(usertest);
        //THEN
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenGetMailNull() throws IllegalArgumentException{
        //GIVEN
        User usertest = new User(3,"d","d",null,"d",false);
        //WHEN
        User test = userService.addUser(usertest);
        //THEN
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenGetMailEmpty() throws IllegalArgumentException{
        //GIVEN
        User usertest = new User(3,"d","d","","d",false);
        //WHEN
        User test = userService.addUser(usertest);
        //THEN
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenGetMdpNull() throws IllegalArgumentException{
        //GIVEN
        User usertest = new User(3,"d","d","d",null,false);
        //WHEN
        User test = userService.addUser(usertest);
        //THEN
        
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhenGetMdpEmpty() throws IllegalArgumentException{
        //GIVEN
        User usertest = new User(3,"d","d","d","",false);
        //WHEN
        userService.addUser(usertest);
        //THEN

    }


    @Test
    public void shouldAddUser() throws UserNotFoundException,Exception {
        //GIVEN
        User usertest = new User(3,"d","d","d","d",false);
        // WHEN
        User user = userService.addUser(usertest);
        // THEN
        Mockito.verify(userDao,Mockito.times(1)).addUser(usertest);

    }

    @Mock
    MotDePasseUtils mdpu;

    @Test
    public void shouldCheckPasswordFalse() throws UserNotFoundException {
        //GIVEN
        String email="test@test.fr";
        String mdp="test";
        String hash = "testno";
        Mockito.when(userDao.getPasswordByEmail(email)).thenReturn(hash);
        //WHEN
        mdpu.validerMotDePasse(mdp,hash);
        //THEN
        assertThat(userService.checkPassword(email,mdp)).isFalse();
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowWhencheckIfAdmin() throws IllegalArgumentException{
        //GIVEN
        String idUser = "";
        //WHEN
        userDao.checkIfAdmin(idUser);
        //THEN

    }

    @Test
    public void shouldcheckIfAdmin(){
        //GIVEN
        String idUser = "idUser";
        //WHEN
        userDao.checkIfAdmin(idUser);
        //THEN
        Mockito.verify(userDao,Mockito.times(1)).checkIfAdmin(idUser);
    }

    @Test
    public void shouldmodifUser(){
        //GIVEN
        String nom ="nom";
        String prenom="prenom";
        String email ="email";
        String id ="1";
        //WHEN
        userDao.modifUser(nom,prenom,email,id);
        //THEN
        Mockito.verify(userDao,Mockito.times(1)).modifUser(nom,prenom,email,id);

    }

}
