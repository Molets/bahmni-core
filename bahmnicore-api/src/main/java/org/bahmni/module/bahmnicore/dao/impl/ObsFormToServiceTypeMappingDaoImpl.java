package org.bahmni.module.bahmnicore.dao.impl;

import org.bahmni.module.bahmnicore.dao.ObsFormToServiceTypeMappingDao;
import org.hibernate.SQLQuery;
import org.openmrs.api.db.hibernate.DbSession;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ObsFormToServiceTypeMappingDaoImpl implements ObsFormToServiceTypeMappingDao {

    @Autowired
    private DbSessionFactory sessionFactory;

    public DbSession getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public String getAppointmentServiceTypeFromObsFormUuid(String obsFormConceptUuid) {

        SQLQuery sqlQuery;
        sqlQuery = getSession().createSQLQuery("SELECT appointment_service_type_uuid "
                + " FROM obs_form_service_type_mapping where obs_form_concept_uuid=:obsFormUuid");
        sqlQuery.setString("obsFormUuid",obsFormConceptUuid);

        String serviceTypeUuid;
        serviceTypeUuid = (String) sqlQuery.uniqueResult();


        return serviceTypeUuid;
    }
}
