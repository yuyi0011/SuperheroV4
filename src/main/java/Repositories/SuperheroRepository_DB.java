package Reponsitories;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Reponsitory

public class SuperheroReponsitory_DB {@Value("${spring.datasource.url}")
private String db_url;

    @Value("${spring.datasource.username}")
    private String uid;

    @Value("${spring.datasource.password}")
    private String pwd;

    private String SQL;
    private Statement stmt;
    private ResultSet rs;
    private PreparedStatement ps;
    private Connection con;

    public Connection connection() {
        try {
            con = DriverManager.getConnection(db_url,uid,pwd);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

    public List<SuperHero> Heroes() {
        List<SuperHero> herolist = new ArrayList<>();
        SuperHero hero;
        try {
            SQL = "SELECT realname, heroname, creationyear, id FROM superhero";
            stmt = connection().createStatement();
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                hero = new SuperHero(rs.getString("realname"), rs.getString("heroname"), rs.getInt("creationyear"), rs.getInt("id"));
                herolist.add(hero);
            }
            return herolist;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<SuperHero> speceficHero(String name) {
        List<SuperHero> herolist = new ArrayList<>();
        SuperHero hero;
        try {
            SQL = "SELECT realname, heroname, creationyear, id From superhero WHERE heroname =?";
            ps = connection().prepareStatement(SQL);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()){
                hero = new SuperHero(rs.getString("realname"), rs.getString("heroname"), rs.getInt("creationyear"), rs.getInt("id"));
                herolist.add(hero);
            }
            return herolist;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<SuperPowerCount> heroesNumberOfPowers(){
        List<SuperPowerCount> herolist = new ArrayList<>();
        SuperPowerCount superpowercount;
        try {
            SQL = "SELECT heroname, realname, COUNT(superpowerid) FROM superhero LEFT JOIN superheropower ON superhero.id = superheropower.superheroid" +
                    " GROUP BY superhero.heroname, superhero.realname";
            stmt = connection().createStatement();
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                superpowercount = new SuperPowerCount(rs.getString("realname"), rs.getString("heroname"), rs.getInt("COUNT(superpowerid)"));
                herolist.add(superpowercount);
            }
            return herolist;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<SuperPowerCount> speceficNumberOfPowers(String name){
        List<SuperPowerCount> herolist = new ArrayList<>();
        SuperPowerCount superpowercount;
        try {
            SQL = "SELECT heroname, realname, COUNT(superpowerid) FROM superhero LEFT JOIN superheropower ON superhero.id = superheropower.superheroid WHERE heroname = ?" +
                    " GROUP BY superhero.heroname, superhero.realname";
            ps = connection().prepareStatement(SQL);
            ps.setString(1, name);
            rs = ps.executeQuery();
            while (rs.next()) {
                superpowercount = new SuperPowerCount(rs.getString("realname"), rs.getString("heroname"), rs.getInt("COUNT(superpowerid)"));
                herolist.add(superpowercount);
            }
            return herolist;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<SuperPower> heroesPower(){
        List<SuperPower> herolist = new ArrayList<>();
        try {
            SQL = "SELECT realname, heroname, power FROM superhero LEFT JOIN superheropower ON superhero.id = superheropower.superheroid LEFT JOIN superpower " +
                    "ON superheropower.superpowerid = superpower.id ORDER BY heroname, power";
            stmt = connection().createStatement();
            rs = stmt.executeQuery(SQL);
            String currenthero = "";
            SuperPower superPower;
            List<String> powerlist = null;
            while (rs.next()) {
                String realname = rs.getString("realname");
                String heroname = rs.getString("heroname");
                String power = rs.getString("power");
                if (heroname.equals(currenthero)){
                    powerlist.add(power);
                } else {
                    powerlist = new ArrayList<>(List.of(power));
                    superPower = new SuperPower(realname, heroname, powerlist);
                    currenthero = heroname;
                    herolist.add(superPower);
                }
            }
            return herolist;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<SuperPower> speceficPower(String name){
        List<SuperPower> herolist = new ArrayList<>();
        try {
            SQL = "SELECT realname, heroname, power FROM superhero LEFT JOIN superheropower ON superhero.id = superheropower.superheroid LEFT JOIN superpower ON superheropower.superpowerid = superpower.id WHERE heroname=? ORDER BY heroname, power";
            ps = connection().prepareStatement(SQL);
            ps.setString(1,name);
            rs = ps.executeQuery();
            String currenthero = "";
            SuperPower superPower;
            List<String> powerlist = null;
            while (rs.next()) {
                String realname = rs.getString("realname");
                String heroname = rs.getString("heroname");
                String power = rs.getString("power");
                if (heroname.equals(currenthero)){
                    powerlist.add(power);
                } else {
                    powerlist = new ArrayList<>(List.of(power));
                    superPower = new SuperPower(realname,heroname,powerlist);
                    currenthero = heroname;
                    herolist.add(superPower);
                }
            }
            return herolist;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<City> city (){
        List<City> cityList = new ArrayList<>();
        try {
            SQL = "SELECT heroname, cityname FROM superhero, city WHERE superhero.cityid = city.id";
            stmt = connection().createStatement();
            rs = stmt.executeQuery(SQL);
            String currentcity = "";
            City city;
            List<String> herolist = null;
            while (rs.next()){
                String heroname = rs.getString("heroname");
                String cityname = rs.getString("cityname");
                if (cityname.equals(currentcity)){
                    herolist.add(heroname);
                } else {
                    herolist = new ArrayList<>(List.of(heroname));
                    city = new City(cityname, herolist);
                    cityList.add(city);
                    currentcity = cityname;
                }
            } return cityList;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<City> speceficCity (String name){
        List<City> cityList = new ArrayList<>();
        try {
            SQL = "SELECT heroname, cityname FROM superhero, city WHERE superhero.cityid = city.id and cityname =?";
            ps = connection().prepareStatement(SQL);
            ps.setString(1,name);
            rs = ps.executeQuery();
            String currentcity = "";
            City city;
            List<String> herolist = null;
            while (rs.next()){
                String heroname = rs.getString("heroname");
                String cityname = rs.getString("cityname");
                if (cityname.equals(currentcity)){
                    herolist.add(heroname);
                } else {
                    herolist = new ArrayList<>(List.of(heroname));
                    city = new City(cityname, herolist);
                    cityList.add(city);
                    currentcity = cityname;
                }
            }
            return cityList;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
