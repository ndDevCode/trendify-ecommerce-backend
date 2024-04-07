package edu.icet.trendify.service.impl;

import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.dto.user.ContactDto;
import edu.icet.trendify.dto.user.CustomerAddressDto;
import edu.icet.trendify.dto.user.CustomerDto;
import edu.icet.trendify.entity.user.CustomerEntity;
import edu.icet.trendify.entity.user.RoleEntity;
import edu.icet.trendify.entity.user.UserEntity;
import edu.icet.trendify.entity.user.UserRoleEntity;
import edu.icet.trendify.repository.user.*;
import edu.icet.trendify.service.CustomerService;
import edu.icet.trendify.util.mapper.ContactMapper;
import edu.icet.trendify.util.mapper.CustomerAddressMapper;
import edu.icet.trendify.util.mapper.CustomerMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final ContactRepository contactRepository;
    private final CustomerAddressRepository customerAddressRepository;

    private final CustomerMapper customerMapper;
    private final ContactMapper contactMapper;
    private final CustomerAddressMapper customerAddressMapper;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    @Override
    public ResponseEntity<ResponseDto<CustomerDto>> registerCustomer(CustomerDto customerDto) {
        try {
            // Check if customer already exists in save operation
            if (isCustomerExist(customerDto)) {
                return new ResponseEntity<>(ResponseDto.error("Customer already exists"), HttpStatus.CONFLICT);
            }

            // Get RoleEntity from roleRepository
            List<RoleEntity> roleList = customerDto.role().stream().map(roleRepository::findByRole).toList();

            // Create UserEntity
            UserEntity userEntity = UserEntity.builder()
                    .email(customerDto.email())
                    .password(passwordEncoder.encode(customerDto.password()))
                    .isActive(customerDto.isActive())
                    .build();

            // Create CustomerEntity
            CustomerEntity customerEntity = customerMapper.toEntity(customerDto);

            // Save UserEntity
            UserEntity savedUser = userRepository.save(userEntity);

            // Add UserEntity to RoleEntities
            savedUser.setUserRole(roleList
                    .stream()
                    .map(role -> new UserRoleEntity(savedUser.getId(), role.getId(), role, savedUser))
                    .toList()
            );

            roleList.forEach(
                    roleEntity -> userRoleRepository.saveUserRole(savedUser.getId(), roleEntity.getId())
            );

            // Save CustomerEntity and get ResponseDto
            CustomerDto responseCustomerDto = getResponseCustomerDto(customerEntity, savedUser);

            return new ResponseEntity<>(
                    ResponseDto.success(responseCustomerDto, "Customer Registered successfully!"),
                    HttpStatus.CREATED
            );
        } catch (Exception exception) {
            String error = "Error while registering customer!";
            log.error(error, exception);
            return new ResponseEntity<>(ResponseDto.error(error), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    protected boolean isCustomerExist(CustomerDto customerDto) {
        return userRepository.findByEmail(customerDto.email()).isPresent();
    }

    @Override
    @Transactional
    public Optional<CustomerDto> getCustomerByEmail(String email) {
        try {
            UserEntity userEntity = userRepository.findByEmail(email).orElse(null);
            return getCustomerDto(userEntity);
        } catch (Exception e) {
            String error = "Error while fetching customer by email!";
            log.error(error, e);
            return Optional.empty();
        }
    }

    @Transactional
    protected CustomerDto getResponseCustomerDto(CustomerEntity customerEntity, UserEntity savedUser) {
        customerEntity.setUser(savedUser);
        CustomerEntity savedCustomerEntity = customerRepository.save(customerEntity);

        // Create ResponseDto
        return CustomerDto.builder()
                .id(savedCustomerEntity.getId())
                .email(savedUser.getEmail())
                .firstName(savedCustomerEntity.getFirstName())
                .lastName(savedCustomerEntity.getLastName())
                .role(savedUser.getUserRole().stream().map(role -> role.getRole().getRole()).toList())
                .isActive(savedUser.getIsActive())
                .build();
    }

    @Transactional
    protected Optional<CustomerDto> getCustomerDto(UserEntity userEntity) {
        if (userEntity != null) {
            CustomerEntity customerEntity = userEntity.getCustomer();
            return Optional.ofNullable(
                    CustomerDto.builder()
                            .id(customerEntity.getId())
                            .firstName(customerEntity.getFirstName())
                            .lastName(customerEntity.getLastName())
                            .email(userEntity.getEmail())
                            .isActive(userEntity.getIsActive())
                            .role(userRoleRepository.getRoleByUserId(userEntity.getId()))
                            .build()
            );
        }
        return Optional.empty();
    }

    @Override
    public Optional<CustomerDto> getCustomerById(Long id) {
        Optional<CustomerEntity> customerEntity = customerRepository.findById(id);
        return customerEntity.map(customerMapper::toDto);
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseDto<CustomerDto>> updateCustomer(CustomerDto customerDto) {
        try {
            // Check if customer already exists in update operation
            if ((customerDto.id() == null || !customerRepository.existsById(customerDto.id()))) {
                return new ResponseEntity<>(
                        ResponseDto.error("Customer not found!"),
                        HttpStatus.NOT_FOUND
                );
            }

            UserEntity userEntity = userRepository.findById(customerDto.id()).orElse(null);
            assert userEntity != null;
            userEntity.setEmail(customerDto.email());
            userEntity.setIsActive(customerDto.isActive());

            // Update CustomerEntity
            CustomerEntity customerEntity = customerRepository.findById(customerDto.id()).orElse(null);
            assert customerEntity != null;
            customerEntity.setFirstName(customerDto.firstName());
            customerEntity.setLastName(customerDto.lastName());

            // Save UserEntity
            UserEntity savedUser = userRepository.save(userEntity);

            // Save CustomerEntity and get ResponseDto
            CustomerDto customerResponseDto = getResponseCustomerDto(customerEntity, savedUser);

            return new ResponseEntity<>(
                    ResponseDto.success(customerResponseDto, "Customer updated successfully!"),
                    HttpStatus.CREATED
            );
        } catch (Exception exception) {
            String error = "Error while updating customer!";
            log.error(error, exception);
            return new ResponseEntity<>(ResponseDto.error(error), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public List<CustomerDto> getAllCustomer() {
        try {
            List<Object> allCustomers = customerRepository.findAllCustomer();
            return allCustomers.stream().map(object -> {
                Object[] objects = (Object[]) object;
                return CustomerDto.builder()
                        .id((Long) objects[0])
                        .firstName((String) objects[1])
                        .lastName((String) objects[2])
                        .email((String) objects[3])
                        .isActive((boolean) objects[4])
                        .role(userRoleRepository.getRoleByUserId((Long) objects[0]))
                        .build();
            }).toList();

        } catch (Exception e) {
            String error = "Error while fetching all customers!";
            log.error(error, e);
        }
        return List.of();
    }

    @Override
    public Boolean addCustomerContact(ContactDto contactDto) {
        if(!customerRepository.existsById(contactDto.cusId()) ||
            contactRepository.existsByContact(contactDto.contact()))
        {
            return false;
        }
        contactRepository.save(contactMapper.toEntity(contactDto));
        return true;
    }

    @Override
    public Boolean addCustomerAddress(CustomerAddressDto addressDto) {
        if(!customerRepository.existsById(addressDto.cusId()) ||
            customerAddressRepository.existsByHouseNoAndStreet(addressDto.houseNo(), addressDto.street()))
        {
            return false;
        }
        customerAddressRepository.save(customerAddressMapper.toEntity(addressDto));
        return true;
    }

    @Override
    @Transactional
    public Boolean removeCustomerContact(String contact) {
        if(!contactRepository.existsByContact(contact)) {
            return false;
        }
        return contactRepository.deleteByContact(contact)>0;
    }

    @Override
    @Transactional
    public Boolean removeCustomerAddress(Long id) {
        if(!customerAddressRepository.existsByAddressId(id)) {
            return false;
        }
        return customerAddressRepository.deleteByAddressId(id);
    }

    @Override
    @Transactional
    public Boolean updateCustomerAddress(CustomerAddressDto addressDto) {
        return addCustomerAddress(addressDto);
    }

    @Override
    public List<ContactDto> getAllContactByCustomerId(Long id) {
        return contactRepository.getAllContactByCustomerId(id)
                .stream()
                .map(contactMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerAddressDto> getAllAddressByCustomerId(Long id) {
        return customerAddressRepository.getAllAddressByCustomerId(id);
    }
}
