package com.natal.eligibilityservice.facade;

import com.natal.eligibilityservice.communication.SubscriptionClient;
import com.natal.eligibilityservice.communication.SubscriptionClientResponse;
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

    @Autowired
    private SubscriptionClient subscriptionClient;
    
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
    public EligibilityResponseTO create(ClientEligibityTO clientEligibityTO) {
        EligibilityResponseTO eligibilityResponse = new EligibilityResponseTO();
        try{
            ClientEligibilityEntity clientEligibilityEntityPersisted = repository.findByClientDocument(clientEligibityTO.getClientDocument());
            if (clientEligibilityEntityPersisted == null){
                if (clientExists(clientEligibityTO.getClientDocument())){
                    ClientEligibilityEntity clientEligibilityEntity
                            = new ClientEligibilityEntity(
                            clientEligibityTO.getClientDocument(),
                            clientEligibityTO.isAllow(),
                            clientEligibityTO.getReason()
                    );
                    ClientEligibilityEntity eligibilityEntity = repository.save(clientEligibilityEntity);
                    eligibilityResponse.setEligible(eligibilityEntity.isAllow());
                    eligibilityResponse.setReason(eligibilityEntity.getReason());
                }
                else {
                    log.error("Cliente com documento {} não encontrado", clientEligibityTO.getClientDocument());
                }
            }
            else {
                clientEligibilityEntityPersisted.setAllow(clientEligibityTO.isAllow());
                repository.save(clientEligibilityEntityPersisted);
                eligibilityResponse.setEligible(clientEligibilityEntityPersisted.isAllow());
                eligibilityResponse.setReason(clientEligibilityEntityPersisted.getReason());
            }
        }
        catch (Exception e){
            log.error("Erro ao salvar elegibilidade de cliente com documento {}", clientEligibityTO.getClientDocument(), e);
        }
        return eligibilityResponse;
    }

    private boolean clientExists(String clientDocument) {
        SubscriptionClientResponse clientResponse = subscriptionClient.findClientByDocument(clientDocument);
        return clientResponse != null;
    }

}
