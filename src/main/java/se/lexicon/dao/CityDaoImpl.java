package se.lexicon.dao;

import se.lexicon.DB.MySQLConnection;
import se.lexicon.model.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents the implementation of CityDao for interacting with the 'city' table in the database.
 */
public class CityDaoImpl implements CityDao {
    private Connection connection;

    public CityDaoImpl(Connection connection){ this.connection = connection; }


    @Override
    public Optional<City> findById(int id) {
        Optional<City> city = null;
        String sql = "SELECT * FROM city WHERE id = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setInt(1, id);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int cityId = resultSet.getInt("ID");
                    String cityName = resultSet.getString("Name");
                    String countryCode = resultSet.getString("CountryCode");
                    String district = resultSet.getString("District");
                    int pop = resultSet.getInt("Population");
                    city = Optional.of(new City(cityId, cityName, countryCode, district, pop));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: Failed in finding city by id");
            e.printStackTrace();
        }

        return city;
    }

    @Override
    public List<City> findByCode(String code) {
        List<City> cityList = new ArrayList<>();
        String sql = "SELECT * FROM city WHERE CountryCode = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1, code);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int cityId = resultSet.getInt("ID");
                    String cityName = resultSet.getString("Name");
                    String countryCode = resultSet.getString("CountryCode");
                    String district = resultSet.getString("District");
                    int pop = resultSet.getInt("Population");
                    cityList.add(new City(cityId, cityName, countryCode, district, pop));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: Failed in finding city by code");
            e.printStackTrace();
        }

        return cityList;
    }

    @Override
    public List<City> findByName(String name) {
        List<City> cityList = new ArrayList<>();
        String sql = "SELECT * FROM city WHERE Name = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1, name);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int cityId = resultSet.getInt("ID");
                    String cityName = resultSet.getString("Name");
                    String countryCode = resultSet.getString("CountryCode");
                    String district = resultSet.getString("District");
                    int pop = resultSet.getInt("Population");
                    cityList.add(new City(cityId, cityName, countryCode, district, pop));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: Failed in finding city by code");
            e.printStackTrace();
        }

        return cityList;
    }

    @Override
    public List<City> findAll() {
        List<City> cityList = new ArrayList<>();
        String sql = "SELECT * FROM city LIMIT 25";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int cityId = resultSet.getInt("ID");
                    String cityName = resultSet.getString("Name");
                    String countryCode = resultSet.getString("CountryCode");
                    String district = resultSet.getString("District");
                    int pop = resultSet.getInt("Population");
                    cityList.add(new City(cityId, cityName, countryCode, district, pop));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: Failed in finding city by code");
            e.printStackTrace();
        }

        return cityList;
    }

    @Override
    public City save(City city) {
        String sql = "INSERT INTO city (name, countrycode, district, population) VALUES (?,?,?,?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getCountryCode());
            preparedStatement.setString(3, city.getDistrict());
            preparedStatement.setInt(4, city.getPopulation());

            int affectedRows = preparedStatement.executeUpdate();

            if(affectedRows > 0) {
                try(ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if(resultSet.next())  {
                        int generatedPersonId = resultSet.getInt(1);
                        city.setId(generatedPersonId);
                    }
                }
                System.out.println("Saved city successfully");
            }else System.out.println("Failed in saving city");

        } catch (SQLException e) {
            System.out.println("Error: Failed to save city");
            e.printStackTrace();
        }

        return city;
    }

    @Override
    public void update(City city) {
        String sql = "UPDATE city SET name = ?, countrycode = ?, district = ?, population = ? WHERE id = ? ";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getCountryCode());
            preparedStatement.setString(3, city.getDistrict());
            preparedStatement.setInt(4, city.getPopulation());
            preparedStatement.setInt(5, city.getId());

            int affectedRows = preparedStatement.executeUpdate();

            if(affectedRows > 0) System.out.println("Updated city Successfully");
            else System.out.println("Failed in updating city");

        } catch (SQLException e) {
            System.out.println("Error: Failed to update city");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM city WHERE id = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setInt(1, id);

            int affectedRows = preparedStatement.executeUpdate();

            if(affectedRows > 0) System.out.println("Deleted city successfully");
            else System.out.println("Failed in deleting city");

        }catch (SQLException e) {
            System.out.println("Error: Delete failed");
            e.printStackTrace();
        }
    }
}
