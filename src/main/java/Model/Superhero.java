package Model;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import java.util.List;
@EntityScan
public class Superhero {

    private int ID;
    private String city;
    private String realName;
    private String superheroName;
    private List<String> superPower;
    private int creationYear;

    public Superhero(int ID, String realName, String superheroName, List<String> superPower, String city, int creationYear) {
        this.ID = ID;
        this.realName = realName;
        this.city = city;
        this.superheroName = superheroName;
        this.superPower = superPower;
        this.creationYear = creationYear;
    }

    public int getID() {
        return ID;
    }

    public void setID() {
        this.ID = ID;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realNameame) {
        this.realName = realName;
    }

    public String getSuperheroName() {
        return superheroName;
    }

    public void setSuperheroName(String superheroName) {
        this.superheroName = superheroName;
    }

    public List<String> getSuperPower() {
        return superPower;
    }

    public void setSuperPower(List<String> superPower) {
        this.superPower = superPower;
    }

    public void addSuperPower(String superpower) {
        superPower.add(superpower);
    }

    public Integer getCreationYear() {
        return creationYear;
    }

    public void setCreationYear(Integer creationYear) {
        this.creationYear = creationYear;
    }

    @Override
    public String toString() {
        String s = "Superheros {"+'\'' +
                "ID=" + '\'' + ID +
                "superheroName='" + superheroName + '\'' +
                ", name='" + realName + '\'' +
                ", superPowers=" + superPower +
                ", creationYear=" + creationYear +
                ", city='" + city + '\'' +
                '}';

        return s;
    }
}
