package edu.icet.trendify.controller;

import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.dto.user.ContactDto;
import edu.icet.trendify.dto.user.CustomerAddressDto;
import edu.icet.trendify.dto.user.CustomerDto;
import edu.icet.trendify.exception.UserNotFoundException;
import edu.icet.trendify.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1")
@CrossOrigin
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/customer")
    public ResponseEntity<ResponseDto<CustomerDto>> registerAdmin(@RequestBody @Valid CustomerDto customerDto) {
        return customerService.registerCustomer(customerDto);
    }

    @PutMapping("/customer")
    public ResponseEntity<ResponseDto<CustomerDto>> updateAdmin(@RequestBody @Valid CustomerDto customerDto) {
        return customerService.updateCustomer(customerDto);
    }

    @GetMapping("/customer")
    public ResponseEntity<ResponseDto<CustomerDto>> getAdmin(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Long id
    ) {
        if (email != null) {
            CustomerDto customerDto = customerService.getCustomerByEmail(email)
                    .orElseThrow(() -> new UserNotFoundException("User not found by email: " + email));
            return new ResponseEntity<>(
                    ResponseDto.success(customerDto, "Admin fetched successfully!"),
                    HttpStatus.OK
            );
        } else if (id != null) {
            CustomerDto customerDto = customerService.getCustomerById(id)
                    .orElseThrow(() -> new UserNotFoundException("User not found by id: " + id));
            return new ResponseEntity<>(
                    ResponseDto.success(customerDto, "Admin fetched successfully!"),
                    HttpStatus.OK
            );
        } else {
            throw new IllegalArgumentException("Either email or id must be provided.");
        }
    }

    @GetMapping("/customers")
    public ResponseEntity<ResponseDto<List<CustomerDto>>> getAllAdmins() {
        List<CustomerDto> customerDto = customerService.getAllCustomer();
        return new ResponseEntity<>(
                ResponseDto.success(customerDto, "Admins fetched successfully!"),
                HttpStatus.OK
        );
    }

    @PostMapping("/customer/contact")
    public ResponseEntity<ResponseDto<ContactDto>> addContact(@RequestBody @Valid ContactDto contactDto) {
        if(Boolean.TRUE.equals(customerService.addCustomerContact(contactDto))){
            return new ResponseEntity<>(
                    ResponseDto.success(contactDto, "Contact added successfully!"),
                    HttpStatus.CREATED
            );
        }

        return new ResponseEntity<>(
                ResponseDto.error("Contact already exists or user not found!"),
                HttpStatus.NOT_ACCEPTABLE
        );
    }

    @DeleteMapping("/customer/contact")
    public ResponseEntity<ResponseDto<ContactDto>> removeContact(@RequestBody @Valid ContactDto contactDto) {
        if(Boolean.TRUE.equals(customerService.removeCustomerContact(contactDto.contact()))){
            return new ResponseEntity<>(
                    ResponseDto.success(null, "Contact removed successfully!"),
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                ResponseDto.error("Contact not found!"), HttpStatus.NOT_FOUND
        );
    }

    @PostMapping("/customer/address")
    public ResponseEntity<ResponseDto<CustomerAddressDto>> addAddress(@RequestBody @Valid CustomerAddressDto addressDto) {
        if(Boolean.TRUE.equals(customerService.addCustomerAddress(addressDto))){
            return new ResponseEntity<>(
                    ResponseDto.success(addressDto, "Address added successfully!"),
                    HttpStatus.CREATED
            );
        }

        return new ResponseEntity<>(
                ResponseDto.error("Address already exists or user not found!"),HttpStatus.NOT_ACCEPTABLE
        );
    }

    @DeleteMapping("/customer/address")
    public ResponseEntity<ResponseDto<CustomerAddressDto>> removeAddress(@RequestBody @Valid CustomerAddressDto addressDto) {
        if(Boolean.TRUE.equals(customerService.removeCustomerAddress(addressDto.addressId()))){
            return new ResponseEntity<>(
                    ResponseDto.success(null, "Address removed successfully!"),
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                ResponseDto.error("Address not found!"),HttpStatus.NOT_FOUND
        );
    }

    @PutMapping("/customer/address")
    public ResponseEntity<ResponseDto<CustomerAddressDto>> updateAddress(@RequestBody @Valid CustomerAddressDto addressDto) {
        if(Boolean.TRUE.equals(customerService.updateCustomerAddress(addressDto))){
            return new ResponseEntity<>(
                    ResponseDto.success(null, "Address updated successfully!"),
                    HttpStatus.OK
            );
        }

        return new ResponseEntity<>(
                ResponseDto.error("Address not found!"),HttpStatus.NOT_FOUND
        );
    }

    @GetMapping("/customer/contacts")
    public ResponseEntity<ResponseDto<List<ContactDto>>> getAllContactByCustomerId(@RequestParam Long id) {
        List<ContactDto> contactDto = customerService.getAllContactByCustomerId(id);
        return new ResponseEntity<>(
                ResponseDto.success(contactDto, "Contacts fetched successfully!"),
                HttpStatus.OK
        );
    }

    @GetMapping("/customer/addresses")
    public ResponseEntity<ResponseDto<List<CustomerAddressDto>>> getAllAddressByCustomerId(@RequestParam Long id) {
        List<CustomerAddressDto> addressDto = customerService.getAllAddressByCustomerId(id);
        return new ResponseEntity<>(
                ResponseDto.success(addressDto, "Addresses fetched successfully!"),
                HttpStatus.OK
        );
    }
}
