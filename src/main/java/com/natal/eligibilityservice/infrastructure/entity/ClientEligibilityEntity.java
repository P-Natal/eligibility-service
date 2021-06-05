package com.natal.eligibilityservice.infrastructure.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "client_eligibility")
public class ClientEligibilityEntity extends EntityClass {

    @Column(name = "client_document", nullable = false)
    private String clientDocument;

    @Column(nullable = false)
    private boolean allow;

    @Column
    private String reason;

    public ClientEligibilityEntity() {
    }

    public ClientEligibilityEntity(String clientDocument, boolean allow, String reason) {
        this.clientDocument = clientDocument;
        this.allow = allow;
        this.reason = reason;
    }

    public void setAllow(boolean allow) {
        this.allow = allow;
    }

    @Override
    public String toString() {
        return "ClientEligibilityEntity{" +
                "id='" + this.getId() + '\'' +
                ", client_document='" + clientDocument + '\'' +
                ", allow='" + allow + '\'' +
                ", reason='" + reason + '\'' +
                ", registry_date='" + this.getRegistryDate() + '\'' +
                ", last_update='" + this.getLastUpdate() + '\'' +
                '}';
    }
}
