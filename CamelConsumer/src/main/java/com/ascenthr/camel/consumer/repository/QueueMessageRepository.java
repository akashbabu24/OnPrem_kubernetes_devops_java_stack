package com.ascenthr.camel.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ascenthr.camel.consumer.model.QueueMessage;

/**
 * Repository class to perform database CRUD operations for QueueMessage table
 * @author sureshd
 *
 */
@Repository
public interface QueueMessageRepository extends JpaRepository<QueueMessage, String> {

}
