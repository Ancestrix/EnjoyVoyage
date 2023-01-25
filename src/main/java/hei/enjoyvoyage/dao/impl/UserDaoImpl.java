package hei.enjoyvoyage.dao.impl;

import hei.enjoyvoyage.dao.UserDao;
import hei.enjoyvoyage.entities.User;
import hei.enjoyvoyage.exception.UserNotFoundException;
import hei.enjoyvoyage.utils.MotDePasseUtils;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class UserDaoImpl implements UserDao {

    @Override
    public List<User> listUsers() {
        List<User> result = new ArrayList<>();
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 Statement statement = cnx.createStatement();
                 ResultSet resultSelect = statement.executeQuery("SELECT `id_utilisateur`,`nom`,`prenom`,`email`,`mdp`,`administrateur` FROM `utilisateur` ORDER BY `id_utilisateur` ")) {
                while (resultSelect.next()) {
                    result.add(createUserFromResultSet(resultSelect));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public User getUser(Integer id) {
        User user = null;
        String sql = "SELECT * FROM utilisateur  WHERE id_utilisateur=?";
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                try (ResultSet result = preparedStatement.executeQuery()) {
                    if (result.next()) {
                        user = createUserFromResultSet(result);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    private User createUserFromResultSet(ResultSet resultSelect) throws SQLException {
        return new User(
                resultSelect.getInt("id_utilisateur"),
                resultSelect.getString("nom"),
                resultSelect.getString("prenom"),
                resultSelect.getString("email"),
                resultSelect.getString("mdp"),
                resultSelect.getBoolean("administrateur"));
    }

    @Override
    public User addUser(User user) {
        String sql = "INSERT INTO utilisateur(nom, prenom, email, mdp) VALUES (?, ?, ?, ?)";
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 PreparedStatement preparedStatement = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, user.getNom());
                preparedStatement.setString(2, user.getPrenom());
                preparedStatement.setString(3, user.getMail());
                preparedStatement.setString(4, MotDePasseUtils.genererMotDePasse(user.getMdp()));

                preparedStatement.executeUpdate();
                ResultSet ids = preparedStatement.getGeneratedKeys();
                if (ids.next()) {
                    user.setId(ids.getInt(1));
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Erreur lors de la mise à jour de l'utilisateur");
    }

    public User getUserInfo(String id_user) {
        String sql = "SELECT nom, prenom, email, administrateur FROM utilisateur WHERE id_utilisateur=?;";
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
                preparedStatement.setString(1, id_user);
                try (ResultSet results = preparedStatement.executeQuery()) {
                    if (results.next()) {
                        User user = new User(
                                Integer.parseInt(id_user),
                                results.getString("nom"),
                                results.getString("prenom"),
                                results.getString("email"),
                                results.getBoolean("administrateur"));

                        return user;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getUserIdFromEmail(String email) {
        String id = null;
        String sql = "SELECT `id_utilisateur` FROM `utilisateur` WHERE email=?";
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
                preparedStatement.setString(1, email);
                try (ResultSet result = preparedStatement.executeQuery()) {
                    if (result.next()) {
                        id = result.getString("id_utilisateur");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public String getPasswordByEmail(String mail) throws UserNotFoundException {
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            String strSql = "SELECT mdp FROM utilisateur WHERE email=?";
            try (PreparedStatement statement = connection.prepareStatement(strSql)) {
                statement.setString(1, mail);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString("mdp");
                    } else {
                        throw new UserNotFoundException(mail);
                    }
                }
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public boolean checkIfAdmin(String userId) {
        boolean isAdmin = false;
        String sql = "SELECT administrateur FROM utilisateur WHERE id_utilisateur=?;";
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
                preparedStatement.setString(1, userId);
                try (ResultSet result = preparedStatement.executeQuery()) {
                    if (result.next()) {
                        isAdmin = result.getBoolean("administrateur");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isAdmin;
    }

    @Override
    public void modifUser(String nom, String prenom, String email, String id) {
        String sql = "UPDATE utilisateur SET nom=?, prenom=?, email=? WHERE id_utilisateur=?";
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 PreparedStatement preparedStatement = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, nom);
                preparedStatement.setString(2, prenom);
                preparedStatement.setString(3, email);
                preparedStatement.setInt(4, Integer.parseInt(id));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while updating profile", e);
        }
    }
}
