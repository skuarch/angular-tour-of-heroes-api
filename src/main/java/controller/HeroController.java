package controller;

import java.util.List;
import model.dto.HeroDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import service.HeroService;


@RestController
public class HeroController {

    @Autowired
    private HeroService heroService;

    @ResponseBody
    @RequestMapping(value = "/hero/{id}", method = RequestMethod.GET)
    public HeroDto getHero(@PathVariable("id") long heroId) {        
        return new HeroDto(heroService.getHero(heroId));
    }
    
    
    @ResponseBody
    @RequestMapping(value = "/heroes", method = RequestMethod.GET)
    public List<HeroDto> getHeroes() {        
        return heroService.getHeroesDto();
    }

}
