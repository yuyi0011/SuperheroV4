package DTO;

public class HeroCountPowerDTO {
    private String heroName;
    private String realName;
    private int numberOfPowers;

    public HeroCountPowerDTO(String heroName, String realName, int numberOfPowers) {
        this.heroName = heroName;
        this.realName = realName;
        this.numberOfPowers = numberOfPowers;
    }

    public String getHeroName() {
        return heroName;
    }

    public String getRealName() {
        return realName;
    }

    public int getNumberOfPowers() {
        return numberOfPowers;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setNumberOfPowers(int numberOfPowers) {
        this.numberOfPowers = numberOfPowers;
    }
}
