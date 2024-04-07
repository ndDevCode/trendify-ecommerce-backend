package edu.icet.trendify.repository.user;

import edu.icet.trendify.dto.user.ContactDto;
import edu.icet.trendify.entity.id.ContactId;
import edu.icet.trendify.entity.user.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, ContactId>, JpaSpecificationExecutor<ContactEntity> {
    Integer deleteByContact(String contact);

    @Modifying
    @Query(value="SELECT * FROM contact WHERE customer_id = :customerId", nativeQuery = true)
    List<ContactEntity> getAllContactByCustomerId(@Param("customerId") Long id);

    boolean existsByContact(String contact);
}