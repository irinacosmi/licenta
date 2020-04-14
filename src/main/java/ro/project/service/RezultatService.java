package ro.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.project.entity.Raspuns;
import ro.project.enums.Intrebare;
import ro.project.enums.Rezultat;
import ro.project.repository.RaspunsRepository;
import ro.project.repository.RezultatRepository;

import java.util.List;

@Service
public class RezultatService {

    @Autowired
    private RaspunsRepository raspunsRepository;

    @Autowired
    private RezultatRepository rezultatRepository;

    public void persistRezultat(String sessionId) {
        List<ro.project.entity.Rezultat> rezultatList = rezultatRepository.findBySesiuneId(sessionId);
        if(rezultatList.size() > 0) {
            for (ro.project.entity.Rezultat rezultat: rezultatList) {
                rezultatRepository.delete(rezultat);
            }
        }
        List<Raspuns> raspunsList = raspunsRepository.findBySesiuneId(sessionId);
        saveInventiv(raspunsList, sessionId);
        saveDirectiv(raspunsList, sessionId);
        saveOrganizat(raspunsList, sessionId);
    }

    private void saveOrganizat(List<Raspuns> raspunsList, String sessionId) {
        int total = 0;
        for (Raspuns raspuns: raspunsList) {
            if (raspuns.getIntrebare().getId() == Intrebare.IN_TRECUT_ERAM_ORGANIZATORUL_UNUI_GRUP_DE_PRIETENI_A_UNUI_CLUB.label ||
                    raspuns.getIntrebare().getId() == Intrebare.CAUT_SA_FIU_PREGATIT_PENTRU_ORICE_SOLICITARE.label ||
                    raspuns.getIntrebare().getId() == Intrebare.MI_SE_PARE_DESTUL_DE_IMPORTANT_SA_MI_SE_SPUNA_CE_SE_ASTEAPTA_DE_LA_MINE.label ||
                    raspuns.getIntrebare().getId() == Intrebare.MI_SE_PARE_NORMAL_SA_MA_CONFORMEZ_UNOR_REGULI_DE_MUNCA.label ||
                    raspuns.getIntrebare().getId() == Intrebare.PRIN_FELUL_MEU_DE_A_FI_IMI_PLACE_SA_AM_MULTE_RESPONSABILITATI.label ||
                    raspuns.getIntrebare().getId() == Intrebare.DISCIPLINA_LA_UN_LOC_DE_MUNCA_MI_SE_PARE_DEOSEBIT_DE_IMPORTANTA.label ||
                    raspuns.getIntrebare().getId() == Intrebare.PREFER_SA_NU_PRIMESC_O_ALTA_LUCRARE_PANA_NU_DUC_LA_BUN_SFARSIT_CEEA_CE_AM_INCEPUT.label ||
                    raspuns.getIntrebare().getId() == Intrebare.IMI_ASUM_ESECUL_PENTRU_ORICE_LUCRU_PE_CARE_IL_REALIZEZ.label) {
                total = total + raspuns.getVariantaId();
            }
        }
        save(Rezultat.ORGANIZAT.label, total, sessionId);
    }

    private void saveDirectiv(List<Raspuns> raspunsList, String sessionId) {
        int total = 0;
        for (Raspuns raspuns: raspunsList) {
            if (raspuns.getIntrebare().getId() == Intrebare.IMI_PLACE_SA_MI_ORGANIZEZ_FIECARE_LUCRU_PE_CARE_L_INCEP.label ||
                    raspuns.getIntrebare().getId() == Intrebare.MA_SIMT_IN_FORMA_CEA_MAI_BUNA.label ||
                    raspuns.getIntrebare().getId() == Intrebare.CAUT_SA_MI_REZOLV_CAT_MAI_REPEDE_SARCINILE_DE_LUCRU.label ||
                    raspuns.getIntrebare().getId() == Intrebare.UN_LOC_DE_MUNCA_CE_MI_PERMITE_SA_IAU_DECIZII_MI_SE_POTRIVESTE_CEL_MAI_MULT.label ||
                    raspuns.getIntrebare().getId() == Intrebare.DIN_CATE_MA_CUNOSC_CRED_CA_AS_PUTEA_SA_FIU_UN_SEF_DE_SUCCES.label ||
                    raspuns.getIntrebare().getId() == Intrebare.MA_PROVOACA_IDEEA_SA_FIU_SEF.label ||
                    raspuns.getIntrebare().getId() == Intrebare.DACA_CER_CEVA_CUIVA_SUNT_ATENT_SA_VAD_CE_INTENTII_ARE.label ||
                    raspuns.getIntrebare().getId() == Intrebare.INTR_O_ACTIVITATE_COLECTIVA_IMI_PLACE_SA_I_SPUN_FIECARUIA_CE_ARE_DE_FACUT.label) {
                total = total + raspuns.getVariantaId();
            }
        }
        save(Rezultat.DIRECTIV.label, total, sessionId);
    }

    private void saveInventiv(List<Raspuns> raspunsList, String sessionId) {
        int total = 0;
        for (Raspuns raspuns: raspunsList) {
            if (raspuns.getIntrebare().getId() == Intrebare.CHIAR_SI_IN_TIMPUL_LIBER_MA_GANDESC_LA_O_MULŢIME_DE_PROBLEME.label ||
            raspuns.getIntrebare().getId() == Intrebare.PENTRU_A_CERCETA_LUCRURILE_IN_PROFUNZIME_INCERC_SA_NU_IAU_IN_SEAMA_APARENTELE.label ||
            raspuns.getIntrebare().getId() == Intrebare.IN_REZOLVAREA_UNOR_PROBLEME_CAUT_SA_AFLU_SOLUTIA_CEA_MAI_BUNA.label ||
            raspuns.getIntrebare().getId() == Intrebare.MA_PROVOACA_IDEEA_DE_A_LUA_PARTE_LA_O_CERCETARE_STIINTIFICA.label ||
            raspuns.getIntrebare().getId() == Intrebare.LA_REZOLVAREA_UNOR_PROBLEME_AM_TENDINTA_SA_DESPIC_FIRUL_IN_PATRU_PANA_CAND_GASESC_SOLUTIA_CEA_MAI_BUNA.label ||
            raspuns.getIntrebare().getId() == Intrebare.DECAT_SA_MA_PLIMB_PRINTR_UN_PARC_PREFER_SA_CITESC_UN_STUDIU_DEDICAT_GRIPEI_AVIARE.label ||
            raspuns.getIntrebare().getId() == Intrebare.IMI_PLACE_SA_REZOLV_UN_LUCRU_CARE_PE_ALTII_II_PLICTISESTE.label ||
            raspuns.getIntrebare().getId() == Intrebare.CAND_IMI_REVINE_REZOLVAREA_UNEI_PROBLEME_IMI_TREC_PRIN_MINTE_TOT_FELUL_DE_IDEI.label) {
                total = total + raspuns.getVariantaId();
            }
        }
        save(Rezultat.INVENTIV.label, total, sessionId);
    }

    private void save(int rezultatId, int scor, String sessionId) {
        ro.project.entity.Rezultat rezultat = new ro.project.entity.Rezultat();
        rezultat.setRezultatId(rezultatId);
        rezultat.setScor(scor);
        rezultat.setSesiuneId(sessionId);
        rezultatRepository.save(rezultat);
    }
}
