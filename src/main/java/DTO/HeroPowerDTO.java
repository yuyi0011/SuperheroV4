package DTO;

import java.util.List;

public class HeroPowerDTO {
    private String heroName;
    private String realName;
    private List<String> heroPowers;

    public HeroPowerDTO(String heroName, String realName, List<String> heroPowers) {
        this.heroName = heroName;
        this.realName = realName;
        this.heroPowers = heroPowers;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public List<String> getHeroPowers() {
        return heroPowers;
    }

    public void setHeroPowers(List<String> heroPowers) {
        this.heroPowers = heroPowers;
    }
}
