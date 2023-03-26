package Services;

import DTO.HeroCountPowerDTO;
import Repositories.SuperheroRepository_DB;
import org.springframework.stereotype.Service;

@Service
public class SuperheroService {
    SuperheroRepository_DB superHeroRepository;
    public SuperheroService(SuperheroRepository_DB superHeroRepository){
        this.superHeroRepository = superHeroRepository;
    }

    public List<SuperHero> getHeroes(){
        return superHeroRepository.Heroes();
    }

    public List<SuperHero> getDefinedHero(String name){
        return superHeroRepository.definedHero(name);
    }

    public List<HeroCountPowerDTO> getHeroesNumberOfPowers(){
        return superHeroRepository.heroesNumberOfPowers();
    }

    public List<HeroCountPowerDTO>getDefinedHeroesNumberOfPowers(String name){
        return superHeroRepository.definedHeroesNumberOfPowers(name);
    }

    public List<SuperPower> getHeroesPowers(){
        return superHeroRepository.heroesPower();
    }

    public List<SuperPower> getDefinedPower(String name){
        return superHeroRepository.definedPower(name);
    }

    public List<City> getCity(){
        return superHeroRepository.city();
    }

    public List<City> getDefinedCity (String name){
        return superHeroRepository.definedCity(name);
    }
}
