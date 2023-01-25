package hei.enjoyvoyage.dao.impl;

import hei.enjoyvoyage.dao.HotelDao;
import hei.enjoyvoyage.entities.Hotel;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class HotelDaoImpl implements HotelDao {

    @Override
    public List<Hotel> listHotels() {
        List<Hotel> result = new ArrayList<>();
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 Statement statement = cnx.createStatement();
                 ResultSet resultSelect = statement.executeQuery("SELECT `photo`,`nom`,`ville`,`pays`,`description`,`prix`,`id_hotel` FROM `hotel` ORDER BY `id_hotel`")) {
                while (resultSelect.next()) {
                    result.add(createHotelFromResultSet(resultSelect));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Hotel> DernierAjoutHotels() {
        List<Hotel> result = new ArrayList<>();
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 Statement statement = cnx.createStatement();
                 ResultSet resultSelect = statement.executeQuery("SELECT `photo`,`nom`,`ville`,`pays`,`description`,`prix`,`id_hotel` FROM `hotel` ORDER BY `id_hotel`DESC LIMIT 5 ")) {
                while (resultSelect.next()) {
                    result.add(createHotelFromResultSet(resultSelect));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Hotel> listRechercheHotels(String destination) {
        String sql = "SELECT `photo`,`nom`,`ville`,`pays`,`description`,`prix`,`id_hotel` FROM `hotel` WHERE `pays`=? OR `ville`=?  ORDER BY `id_hotel`";
        List<Hotel> result = new ArrayList<>();
        try (Connection connection = DataSourceProvider.getDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, destination);
            preparedStatement.setString(2, destination);
            try (ResultSet results = preparedStatement.executeQuery()) {
                while (results.next()) {
                    result.add(createHotelFromResultSet(results));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }



    private Hotel createHotelFromResultSet(ResultSet resultSelect) throws SQLException {
        return new Hotel(
                resultSelect.getInt("id_hotel"),
                resultSelect.getString("nom"),
                resultSelect.getString("ville"),
                resultSelect.getString("pays"),
                resultSelect.getString("description"),
                resultSelect.getDouble("prix"),
                resultSelect.getString("photo"));
    }

    public Hotel getHotel(Integer id_hotel) {
        Hotel hotel = null;
        String sql = "SELECT * FROM hotel  WHERE id_hotel=?";
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 PreparedStatement preparedStatement = cnx.prepareStatement(sql)) {
                preparedStatement.setInt(1, id_hotel);
                try (ResultSet result = preparedStatement.executeQuery()) {
                    if (result.next()) {
                        hotel = createHotelFromResultSet(result);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotel;
    }


    public Hotel addHotel(Hotel hotel) {
        String sql = "INSERT INTO hotel(nom, ville, pays, description, prix, photo) VALUES (?, ?, ?, ?,?,?)";
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 PreparedStatement preparedStatement = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, hotel.getNom());
                preparedStatement.setString(2, hotel.getVille());
                preparedStatement.setString(3, hotel.getPays());
                preparedStatement.setString(4, hotel.getDescription());
                preparedStatement.setDouble(5, hotel.getPrix());
                preparedStatement.setString(6, hotel.getPhoto());

                preparedStatement.executeUpdate();
                ResultSet ids = preparedStatement.getGeneratedKeys();
                if (ids.next()) {
                    hotel.setId(ids.getInt(1));
                    return hotel;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Erreur lors de la mise à jour de l'utilisateur");
    }

    @Override
    //(Integer id_hotel, String nom, String ville, String pays, String description, Integer prix, String photo)
    public Hotel modifHotel(Hotel hotel, Integer id) {
        String sql = "UPDATE hotel SET nom=?, ville=?, pays=?, description=?, prix=?, photo=? WHERE id_hotel=?";
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 PreparedStatement preparedStatement = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, hotel.getNom());
                preparedStatement.setString(2, hotel.getVille());
                preparedStatement.setString(3, hotel.getPays());
                preparedStatement.setString(4, hotel.getDescription());
                preparedStatement.setDouble(5, hotel.getPrix());
                preparedStatement.setString(6,hotel.getPhoto());
                preparedStatement.setInt(7,id);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotel;
    }


    @Override
    public void deleteHotel(String hotel ) {
        String sql = "DELETE FROM hotel WHERE id_hotel = ?";
        try {
            DataSource dataSource = DataSourceProvider.getDataSource();
            try (Connection cnx = dataSource.getConnection();
                 PreparedStatement preparedStatement = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, hotel);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

