package service;

import java.util.ArrayList;
import java.util.List;
import model.dto.HeroDto;
import model.entity.HeroEntity;
import model.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author skuarch
 */
@Service
public class HeroService {

    @Autowired
    private HeroRepository heroRepository;
    

    public HeroEntity getHero(long heroId) {

        if (heroId < 1) {
            throw new IllegalArgumentException("heroId is less than 1");
        }

        return heroRepository.findOne(heroId);

    }
    

    public List<HeroDto> getHeroesDto() {        

        List<HeroEntity> heroesEntity = heroRepository.findAll();
        List<HeroDto> heroes = new ArrayList<>();

        heroesEntity.stream().forEach((heroEntity) -> {
            heroes.add(new HeroDto(heroEntity));
        });

        return heroes;
    }

    
    public List<HeroEntity> getHeroes() {
        return heroRepository.findAll();
    }   
    
    
    public HeroEntity createHero(HeroEntity heroEntity) {         
        return heroRepository.save(heroEntity);
    }

}
