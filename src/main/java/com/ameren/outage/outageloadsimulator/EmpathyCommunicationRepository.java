package com.ameren.outage.outageloadsimulator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpathyCommunicationRepository extends CrudRepository<EmpathyCommunication, String> {}