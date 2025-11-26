package com.java.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

@Repository
public class ReceiveRequestVoiceRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * private String destAddr;
     * private String otp; //Optional
     * private String api; //Optional
     * private Integer type; //Optional
     * private String lan; //Optional
     *
     * @return
     * @RequestId = ID after insert
     */

    public Long insertRequestVoice(String username, String destAddr, String otp, String api, Integer type, String lan) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("sp_ReceiveRequestVoice")
                .registerStoredProcedureParameter("username", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("destAddr", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("otp", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("api", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("type", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("lan", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("requestId", Long.class, ParameterMode.OUT);

        query.setParameter("username", username);
        query.setParameter("destAddr", destAddr);
        query.setParameter("otp", otp != null ? otp : "");
        query.setParameter("api", api != null ? api : "");
        query.setParameter("type", type != null ? type : -1);
        query.setParameter("lan", lan != null ? lan : "");
        query.execute();
        return (Long) query.getOutputParameterValue("requestId");
    }

}
