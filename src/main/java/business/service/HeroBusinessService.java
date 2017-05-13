package business.service;

import components.WebSocketHandler;
import java.io.IOException;
import model.entity.HeroEntity;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.HeroService;

@Service
public class HeroBusinessService {
    
    @Autowired
    private HeroService service;
    
    @Autowired
    private WebSocketHandler webSocketHandler;
    
    public HeroEntity createHero(HeroEntity entity) throws IOException {
        HeroEntity newHero = service.createHero(entity);
        JSONObject jsono = new JSONObject();
        jsono.put("type", "hero");
        jsono.put("message", "new");
        jsono.put("content", new JSONObject(newHero));
        webSocketHandler.sendText(jsono.toString());
        return newHero;        
    }
    
}
