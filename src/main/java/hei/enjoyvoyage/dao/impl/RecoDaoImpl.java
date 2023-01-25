package hei.enjoyvoyage.dao.impl;

import hei.enjoyvoyage.dao.RecoDao;
import hei.enjoyvoyage.entities.Hotel;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecoDaoImpl implements RecoDao {

    public void addReco(String id_hotel)  {
        String sql = "INSERT INTO reco(id_hotel) VALUES (?)";
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
                preparedStatement.setInt(1, Integer.parseInt(id_hotel));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error", e);
        }
    }


    @Override
    public List<Hotel> listReco() {
        String sql = "SELECT * FROM reco LEFT JOIN hotel ON reco.id_hotel=hotel.id_hotel ORDER BY id DESC LIMIT 5";
        List<Hotel> res = new ArrayList<>();
        try (Connection cnx = DataSourceProvider.getDataSource().getConnection()) {
            PreparedStatement preparedStatement = cnx.prepareStatement(sql);

            try (ResultSet results = preparedStatement.executeQuery()) {
                while (results.next()) {
                    Hotel hotel = new Hotel(
                            results.getInt("id_hotel"),
                            results.getString("nom"),
                            results.getString("ville"),
                            results.getString("pays"),
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
