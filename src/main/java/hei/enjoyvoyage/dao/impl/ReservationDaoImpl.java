package hei.enjoyvoyage.dao.impl;

import hei.enjoyvoyage.dao.ReservationDao;
import hei.enjoyvoyage.entities.Hotel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDaoImpl implements ReservationDao {

    public void addReservation(String id_user, String id_hotel)  {
        String sql = "INSERT INTO reservation(id_utilisateur, id_hotel) VALUES (?, ?)";
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
                preparedStatement.setInt(1, Integer.parseInt(id_user));
                preparedStatement.setInt(2, Integer.parseInt(id_hotel));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error", e);
        }
    }

    public void deleteReservation(String id_user, String id_hotel)   {
        String sql = "DELETE FROM reservation WHERE id_utilisateur = ? AND id_hotel = ?";

        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
                preparedStatement.setInt(1, Integer.parseInt(id_user));
                preparedStatement.setInt(2, Integer.parseInt(id_hotel));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error", e);
        }
    }

    @Override
    public List<Hotel> listReservation(String id_user) {
        String sql = "SELECT * FROM reservation JOIN hotel ON reservation.id_hotel=hotel.id_hotel WHERE id_utilisateur=?";
        List<Hotel> res = new ArrayList<>();
        try (Connection cnx = DataSourceProvider.getDataSource().getConnection()) {
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(id_user));

            try (ResultSet results = preparedStatement.executeQuery()) {
                while (results.next()) {
                     Hotel hotel = new Hotel(
                             results.getInt("id_hotel"),
                             results.getString("nom"),
                             results.getString("ville"),
                             results.getString("pays"),
                             results.getDouble("prix"),
                             results.getString("photo"));
                    res.add(hotel);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
