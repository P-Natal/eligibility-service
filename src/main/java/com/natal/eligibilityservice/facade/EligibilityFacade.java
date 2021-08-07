package com.natal.eligibilityservice.facade;

import com.natal.eligibilityservice.controller.ClientEligibityTO;
import com.natal.eligibilityservice.controller.EligibilityResponseTO;
import com.natal.eligibilityservice.infrastructure.entity.ClientEligibilityEntity;
import com.natal.eligibilityservice.infrastructure.repository.ClientEligibilityRepository;
import com.natal.eligibilityservice.service.EligibilityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EligibilityFacade implements EligibilityService {

    @Autowired
    private ClientEligibilityRepository repository;
    
    @Override
    public EligibilityResponseTO getEligibility(String document) {
        log.info("Buscando elegibilidade de cliente com documento {}", document);
        EligibilityResponseTO response = new EligibilityResponseTO();
        ClientEligibilityEntity clientEligibilityEntity = repository.findByClientDocument(document);
        if (clientEligibilityEntity==null || clientEligibilityEntity.isAllow()){
            log.info("Cliente com documento {} elegível", document);
            response.setEligible(true);
        }
        else{
            log.info("Client com documento {} não está elegível", document);
            response.setEligible(false);
        }
        return response;
    }

    @Override
    public void create(ClientEligibityTO clientEligibityTO) {
        try{
            ClientEligibilityEntity clientEligibilityEntityPersisted = repository.findByClientDocument(clientEligibityTO.getClientDocument());
            if (clientEligibilityEntityPersisted == null){
                ClientEligibilityEntity clientEligibilityEntity
                        = new ClientEligibilityEntity(
                        clientEligibityTO.getClientDocument(),
                        clientEligibityTO.isAllow(),
                        clientEligibityTO.getReason()
                );
                repository.save(clientEligibilityEntity);
            }
            else {
                clientEligibilityEntityPersisted.setAllow(clientEligibityTO.isAllow());
                repository.save(clientEligibilityEntityPersisted);
            }
        }
        catch (Exception e){
            log.error("Erro ao salvar elegibilidade de cliente com documento {}", clientEligibityTO.getClientDocument(), e);
        }
    }

//    private ClientEligibityTO findClientEligibilityEntity(String clientDocument) {
//        ClientEligibityTO clientEligibityTO = new ClientEligibityTO();
//        try{
//            ClientEligibilityEntity clientEligibilityPersisted = repository.findByClientDocument(clientEligibityTO.getClientDocument());
//            if (clientEligibilityPersisted !=null){
//                clientEligibityTO.setClientDocument(clientEligibilityPersisted.getClientDocument());
//                clientEligibityTO.setAllow(clientEligibilityPersisted.isAllow());
//                clientEligibityTO.setReason(clientEligibilityPersisted.getReason());
//            }
//            else {
//                log.warn("Registro de elegibilidade do cliente com documento {} não encontrado!", clientDocument);
//                clientEligibityTO = null;
//            }
//        }catch (Exception e){
//            log.error("Falha ao buscar elegibilidade do cliente com documento: {} ", clientDocument, e);
//            clientEligibityTO = null;
//        }
//        return clientEligibityTO;
//    }
}
