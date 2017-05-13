package controller;

import business.service.HeroBusinessService;
import java.io.IOException;
import java.util.List;
import model.dto.HeroDto;
import model.entity.HeroEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import service.HeroService;


@RestController
public class HeroController {

    private static final String PATH = "/heroes";    
    
    @Autowired
    private HeroService service;
    
    @Autowired
    private HeroBusinessService businessService;

    @ResponseBody
    @RequestMapping(value = PATH +"/{id}", method = RequestMethod.GET)
    public HeroDto getHero(@PathVariable("id") long heroId) {
        return new HeroDto(service.getHero(heroId));
    }

    @ResponseBody
    @RequestMapping(value = PATH, method = RequestMethod.GET)
    public List<HeroDto> getHeroes() {
        try {
            Thread.sleep(4000);
        } catch (Exception e) {
        }
        return service.getHeroesDto();
    }
    
    
    @ResponseBody
    @RequestMapping(value = PATH, method = RequestMethod.POST)
    public HeroDto getHero(@RequestBody HeroEntity heroEntity) throws IOException {
        return new HeroDto(businessService.createHero(heroEntity));
    }

}
