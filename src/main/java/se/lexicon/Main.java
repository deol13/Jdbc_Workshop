package se.lexicon;

import se.lexicon.DB.MySQLConnection;
import se.lexicon.dao.CityDaoImpl;
import se.lexicon.model.City;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Represents the entry point of the application.
 */
public class Main {
    public static void main(String[] args) {
        //findById();
        //findByCode();
        //findByName();
        //findAll();

        //save();
        //update();
        //deleteById();
    }
    //TODO: Change the id in findById, update and deleteById depending on save if you want to test the code.
    public static void findById(){
        try{
            CityDaoImpl cityDao = new CityDaoImpl(MySQLConnection.getConnection());

            Optional<City> city = cityDao.findById(4081);

            System.out.println(city.toString());
            System.out.println("Operation is Done!");
        } catch (SQLException e) {
            System.out.println("MySQL DB Connection Failed.");
        }
    }
    public static void findByCode(){
        try{
            CityDaoImpl cityDao = new CityDaoImpl(MySQLConnection.getConnection());

            List<City> cityList = cityDao.findByCode("SWE");

            cityList.forEach(i -> System.out.println(i.toString()));
            System.out.println("Operation is Done!");
        } catch (SQLException e) {
            System.out.println("MySQL DB Connection Failed.");
        }    }
    // LIMITED to the first 25
    public static void findByName(){
        try{
            CityDaoImpl cityDao = new CityDaoImpl(MySQLConnection.getConnection());

            List<City> cityList = cityDao.findByName("New Gothenburg2");

            cityList.forEach(i -> System.out.println(i.toString()));
            System.out.println("Operation is Done!");
        } catch (SQLException e) {
            System.out.println("MySQL DB Connection Failed.");
        }
    }
    public static void findAll(){
        try{
            CityDaoImpl cityDao = new CityDaoImpl(MySQLConnection.getConnection());

            List<City> cityList = cityDao.findAll();

            cityList.forEach(i -> System.out.println(i.toString()));
            System.out.println("Operation is Done!");
        } catch (SQLException e) {
            System.out.println("MySQL DB Connection Failed.");
        }
    }

    public static void save(){
        try{
            CityDaoImpl cityDao = new CityDaoImpl(MySQLConnection.getConnection());

            City savedCity = cityDao.save(new City("New Gothenburg2", "SWE", "testDistrict", 1));

            System.out.println("savedItem = " + savedCity.toString());
            System.out.println("Operation is Done!");

        }catch (SQLException e) {
            System.out.println("MySQL DB Connection Failed.");
        }
    }
    public static void update(){
        try{
            CityDaoImpl cityDao = new CityDaoImpl(MySQLConnection.getConnection());

            cityDao.update(new City(4081,"New Gothenburg2", "SWE", "testDistrict", 2));

            System.out.println("Update Operation is Done!");

        }catch (SQLException e) {
            System.out.println("MySQL DB Connection Failed.");
        }
    }
    public static void deleteById(){
        try{
            CityDaoImpl cityDao = new CityDaoImpl(MySQLConnection.getConnection());

            cityDao.deleteById(4081);

            System.out.println("Delete Operation is Done!");

        }catch (SQLException e) {
            System.out.println("MySQL DB Connection Failed.");
        }
    }
}
