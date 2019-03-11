package sport.application.controllers;

import sport.application.repositories.PlanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PlanController {

    @Autowired
    PlanRepository planRepository;

//    @PostConstruct
//    public void loaddate(){
//        Plan plan1 = new Plan();
//        Map<String, String> mapa = new LinkedHashMap<>();
//        mapa.put("poniedzialek","klata");
//        mapa.put("wtore","wolne");
//        mapa.put("sroda","plecy");
//        mapa.put("czwartek","barki");
//        mapa.put("piatek","wolne");
//        mapa.put("sobita","wolne");
//        mapa.put("niedziela","bsaen");
//        plan1.setId((long) 1);
//
//        Plan plan2 = new Plan();
//        Map<String, String> mapa2 = new LinkedHashMap<>();
//        mapa2.put("poniedzialek","wole");
//        mapa2.put("wtore","wolne");
//        mapa2.put("sroda","wole");
//        mapa2.put("czwartek","wole");
//        mapa2.put("piatek","wolne");
//        mapa2.put("sobita","wolne");
//        mapa2.put("niedziela","wole");
//        plan2.setId((long) 2);
//
//        plan2.setMapadziencwiczenie(mapa);
//        planRepository.save(plan2);
//    }
}
