package edu.icet.trendify.repository.user;

import edu.icet.trendify.dto.user.CustomerAddressDto;
import edu.icet.trendify.entity.id.CustomerAddressId;
import edu.icet.trendify.entity.user.CustomerAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddressEntity, CustomerAddressId>, JpaSpecificationExecutor<CustomerAddressEntity> {
    Boolean deleteByAddressId(Long id);

    @Query(value="SELECT * FROM customer_address WHERE customer_id = :customerId", nativeQuery = true)
    List<CustomerAddressDto> getAllAddressByCustomerId(@Param("customerId") Long id);

    boolean existsByHouseNoAndStreet(String houseNo, String street);

    @Query(value="SELECT * FROM customer_address WHERE address_id = :addressId != 0", nativeQuery = true)
    boolean existsByAddressId(@Param("addressId") Long id);
}