package com.vitu.email.service.api.repository;

import com.vitu.email.service.api.domain.Email;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends MongoRepository<Email, String> {
}
