package ro.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.project.entity.Raspuns;
import ro.project.repository.IntrebareRepository;
import ro.project.repository.RaspunsRepository;

import java.util.List;

@Service
public class RaspunsService {

    @Autowired
    private RaspunsRepository raspunsRepository;

    @Autowired
    private IntrebareRepository intrebareRepository;

    public void persistRaspuns(int intrebareId, String sessionId, int varianta) {
        Raspuns raspuns;
        raspuns = raspunsRepository.findByIntrebareIdAndSesiuneId(intrebareId, sessionId);
        if(raspuns == null) {
            Raspuns newRaspuns = new Raspuns();
            newRaspuns.setIntrebare(intrebareRepository.findById(intrebareId));
            newRaspuns.setVariantaId(varianta);
            newRaspuns.setSesiuneId(sessionId);
            raspunsRepository.save(newRaspuns);
        } else {
            raspuns.setVariantaId(varianta);
            raspunsRepository.save(raspuns);
        }
    }

    public int getCountRespondedQuizBySessionId(String sessionId) {
        List<Raspuns> raspunsList = raspunsRepository.findBySesiuneId(sessionId);
        return raspunsList.size();
    }
}
