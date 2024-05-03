package edu.icet.trendify.repository.user;

import edu.icet.trendify.entity.user.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>, JpaSpecificationExecutor<CustomerEntity> {
    @Modifying
    @Query(value = "SELECT customer.id,customer.first_name,customer.last_name,user.email,user.is_active  FROM customer INNER JOIN user ON customer.user_id = user.id", nativeQuery = true)
    List<Object> findAllCustomer();

    Optional<CustomerEntity> findByUser_Email(String email);
}