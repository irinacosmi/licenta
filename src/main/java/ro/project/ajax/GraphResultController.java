package ro.project.ajax;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.project.entity.Rezultat;
import ro.project.model.Result;
import ro.project.repository.RezultatRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class GraphResultController
{
    private static final String INVENTIV    = ro.project.enums.Rezultat.INVENTIV.name();
    private static final String DIRECTIV    = ro.project.enums.Rezultat.DIRECTIV.name();
    private static final String ORGANIZAT   = ro.project.enums.Rezultat.ORGANIZAT.name();

    private static final int INVENTIV_ID    = 1;
    private static final int DIRECTIV_ID    = 2;
    private static final int ORGANIZAT_ID   = 3;

    @Autowired
    private RezultatRepository rezultatRepository;

    @ResponseBody
    @RequestMapping(value = "/buildDataGraph", method = RequestMethod.GET, produces = "application/json")
    public Result buildDataGraph(@RequestParam String sessionId)
    {
        Map<String, Integer> resultList = new HashMap<>();
        resultList.put(INVENTIV, 0);
        resultList.put(DIRECTIV, 0);
        resultList.put(ORGANIZAT, 0);
        List<Rezultat> rezultatList = rezultatRepository.findBySesiuneId(sessionId);


        for (Rezultat rezultat: rezultatList) {
            if (rezultat.getRezultatId() == INVENTIV_ID) {
                resultList.put(INVENTIV, resultList.get(INVENTIV) + rezultat.getScor());
            } else if (rezultat.getRezultatId() == DIRECTIV_ID) {
                resultList.put(DIRECTIV, resultList.get(DIRECTIV) + rezultat.getScor());
            }
            if (rezultat.getRezultatId() == ORGANIZAT_ID) {
                resultList.put(ORGANIZAT, resultList.get(ORGANIZAT) + rezultat.getScor());
            }
        }

        Result result = new Result();
        result.setResult(resultList);

        return result;
    }
}
