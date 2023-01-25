package hei.enjoyvoyage.dao;

import hei.enjoyvoyage.entities.User;
import hei.enjoyvoyage.exception.UserNotFoundException;
import java.util.List;

public interface UserDao {
	User getUser(Integer id);

	List<User> listUsers();

	User addUser(User user);

	String getPasswordByEmail(String email) throws UserNotFoundException;

	User getUserInfo(String idUser);

	String  getUserIdFromEmail(String email);

	boolean checkIfAdmin(String idUser);

	void modifUser(String nom, String prenom, String email, String id);
}
