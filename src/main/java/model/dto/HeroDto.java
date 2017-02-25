package model.dto;


import model.entity.HeroEntity;

/**
 *
 * @author skuarch
 */
public class HeroDto {
    
    private long id;
    private String name;

    public HeroDto(HeroEntity heroEntity) {
        this.id = heroEntity.getId();
        this.name = heroEntity.getName();
    }

    public HeroDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
