package edu.icet.trendify.repository.user;

import edu.icet.trendify.entity.id.ContactId;
import edu.icet.trendify.entity.user.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, ContactId>, JpaSpecificationExecutor<ContactEntity> {
}