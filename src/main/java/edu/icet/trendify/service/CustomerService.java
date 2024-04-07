package edu.icet.trendify.service;

import edu.icet.trendify.dto.user.*;
import edu.icet.trendify.dto.ResponseDto;
import org.springframework.aop.target.LazyInitTargetSource;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    ResponseEntity<ResponseDto<CustomerDto>> registerCustomer(CustomerDto customerDto);
    Optional<CustomerDto> getCustomerByEmail(String email);
    Optional<CustomerDto> getCustomerById(Long id);
    ResponseEntity<ResponseDto<CustomerDto>> updateCustomer(CustomerDto customerDto);
    List<CustomerDto> getAllCustomer();

    Boolean addCustomerContact(ContactDto contactDto);
    Boolean addCustomerAddress(CustomerAddressDto addressDto);
    Boolean removeCustomerContact(String contact);
    Boolean removeCustomerAddress(Long id);
    Boolean updateCustomerAddress(CustomerAddressDto addressDto);
    List<ContactDto> getAllContactByCustomerId(Long id);
    List<CustomerAddressDto> getAllAddressByCustomerId(Long id);
}
