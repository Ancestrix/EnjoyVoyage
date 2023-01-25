package hei.enjoyvoyage.service;

import hei.enjoyvoyage.dao.UserDao;
import hei.enjoyvoyage.dao.impl.UserDaoImpl;
import hei.enjoyvoyage.entities.User;
import hei.enjoyvoyage.exception.UserNotFoundException;
import hei.enjoyvoyage.utils.MotDePasseUtils;
import hei.enjoyvoyage.utils.Verification;

import java.util.List;

public class UserService {


    private static class UserLibraryHolder {
        private final static UserService instance = new UserService();
    }
    public static UserService getInstance() {
        return UserService.UserLibraryHolder.instance;
    }

    private UserDao userDao = new UserDaoImpl();

    private UserService() {
    }

    public List<User> listUsers() {
        return userDao.listUsers();
    }

    public User getUser(Integer id) {
        return userDao.getUser(id);
    }

    public String getUserIdFromEmail(String email){
        if (Verification.isEmpty(email)) {
            throw new IllegalArgumentException("Email cannot be null");

        }
        return userDao.getUserIdFromEmail(email);

    }
    

    public User addUser(User user) {
        if(user == null) {
            throw new IllegalArgumentException("The user can not be null.");
        }
        if (user.getNom() == null || "".equals(user.getNom())) {
            throw new IllegalArgumentException("A user must have a nom.");
        }
        if (user.getPrenom() == null || "".equals(user.getPrenom())) {
            throw new IllegalArgumentException("A user must have a prenom.");
        }
        if (user.getMail() == null || "".equals(user.getMail())) {
            throw new IllegalArgumentException("A user must have a Mail.");
        }
        if (user.getMdp() == null || "".equals(user.getMdp())) {
            throw new IllegalArgumentException("A user must have a mdp.");
        }
        return userDao.addUser(user);
    }

    public boolean checkPassword(String email, String mdp){

        //Récupérer via la DAO le mdp correspond à l'email
        try {
            String hash = userDao.getPasswordByEmail(email);
            //Si l'email existe alors
            // Utiliser argon2 pour comparer mdp avec le mot de passe de passe dans la base de donnée
            return MotDePasseUtils.validerMotDePasse(mdp,hash);
            // Retourner le résultat de la comparaison
        }catch (UserNotFoundException e){
            //Sinon retourner faux
            return false;
        }

    }

    public User getUserInfo(String idUser) {
        if (Verification.isEmpty(idUser)) {
            throw new IllegalArgumentException("User id cannot null");
        }

        return userDao.getUserInfo(idUser);
    }

    public boolean checkIfAdmin(String idUser) {
        if (Verification.isEmpty(idUser)) {
            throw new IllegalArgumentException("User id cannot null");
        }

        return userDao.checkIfAdmin(idUser);
    }

    public void modifUser(String nom, String prenom, String email, String id) {
        userDao.modifUser(nom, prenom, email, id);
    }
}
