package model.repository;

import java.util.List;
import model.entity.HeroEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author skuarch
 */
@Repository
public interface HeroRepository extends CrudRepository<HeroEntity, Long> {
    @Override
    List<HeroEntity> findAll();
}
