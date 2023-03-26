package dk.kea.superherov4.Controllers;

import DTO.CityHeroDTO;
import DTO.HeroCountPowerDTO;
import DTO.HeroPowerDTO;
import Model.Superhero;
import Services.SuperheroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SuperheroController {
    DBInterface d = new Database();


    @GetMapping("/superhero/all")
    public ResponseEntity<List<Superhero>> superheroes() {
        List<Superhero> list = d.allHeroes();
        return new ResponseEntity<List<Superhero>>(list, HttpStatus.OK);
    }

    @GetMapping("/superhero/{SuperheroName}")
    public ResponseEntity<Superhero> superhero(@PathVariable String SuperheroName) {
        Superhero hero = d.hero(SuperheroName);
        return new ResponseEntity<Superhero>(hero, HttpStatus.OK);
    }

    @GetMapping("/superhero/power")
    public ResponseEntity<List<HeroPowerDTO>> showHeroNumberOfPowersPath(@PathVariable String name) {
        List<HeroPowerDTO> superPowerCounts = SuperheroService.getDefinedHeroesNumberOfPowers(name);
        return new ResponseEntity(superPowerCounts, HttpStatus.OK);
    }


    @GetMapping("/superhero/delete/{hero}")
    public ResponseEntity<String> deleteHero(@PathVariable String hero) {
        String message = "";
        boolean wasDeleted = d.deleteHero(hero);
        if (wasDeleted) {
            message = hero + " has been succesfully deleted";
        } else {
            message = hero + " could not be deleted";
        }

        return new ResponseEntity<String>(message, HttpStatus.OK);

    }


    @GetMapping("/powers")
    public ResponseEntity<List<HeroPowerDTO>> showHeroesSuperpower() {
        List<HeroPowerDTO> superPowerList = SuperheroService.getHeroesPowers();
        return new ResponseEntity(superPowerList, HttpStatus.OK);
    }

    @GetMapping("/powers/{name}")
    public ResponseEntity<List<HeroPowerDTO>> showHeroesSuperpowerPath(@PathVariable String name) {
        List<HeroPowerDTO> superPowerList = SuperheroService.getDefinedPower(name);
        return new ResponseEntity(superPowerList, HttpStatus.OK);
    }

    @GetMapping("/city")
    public ResponseEntity<List<CityHeroDTO>> showHeroesCity() {
        List<CityHeroDTO> cityList = SuperheroService.getCity();
        return new ResponseEntity<>(cityList, HttpStatus.OK);
    }

    @GetMapping("/city/{name}")
    public ResponseEntity<List<CityHeroDTO>> showHeroesCityPath(@PathVariable String name) {
        List<CityHeroDTO> cityList = SuperheroService.getDefinedCity(name);
        return new ResponseEntity<>(cityList, HttpStatus.OK);
    }
}