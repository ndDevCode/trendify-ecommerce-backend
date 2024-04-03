package edu.icet.trendify.service.impl;

import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.dto.user.AdminDto;
import edu.icet.trendify.dto.user.AuthResponseDto;
import edu.icet.trendify.dto.user.LoginDto;
import edu.icet.trendify.dto.user.RoleDto;
import edu.icet.trendify.entity.user.AdminEntity;
import edu.icet.trendify.entity.user.RoleEntity;
import edu.icet.trendify.entity.user.UserEntity;
import edu.icet.trendify.entity.user.UserRoleEntity;
import edu.icet.trendify.repository.user.AdminRepository;
import edu.icet.trendify.repository.user.RoleRepository;
import edu.icet.trendify.repository.user.UserRepository;
import edu.icet.trendify.repository.user.UserRoleRepository;
import edu.icet.trendify.service.AdminService;
import edu.icet.trendify.util.enums.Role;
import edu.icet.trendify.util.mapper.AdminMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    @Transactional
    @Override
    public ResponseEntity<ResponseDto<AdminDto>> registerAdmin(AdminDto adminDto) {
        try {
            // Check if admin already exists in save operation
            if (isAdminExist(adminDto)) {
                return new ResponseEntity<>(ResponseDto.error("Admin already exists"), HttpStatus.CONFLICT);
            }

            // Get RoleEntity from roleRepository
            List<RoleEntity> roleList = adminDto.role().stream().map(roleRepository::findByRole).toList();

            // Create UserEntity
            UserEntity userEntity = UserEntity.builder()
                    .email(adminDto.email())
                    .password(adminDto.password())
                    .isActive(adminDto.isActive())
                    .build();

            // Create AdminEntity
            AdminEntity adminEntity = adminMapper.toEntity(adminDto);

            // Save UserEntity
            UserEntity savedUser = userRepository.save(userEntity);

            // Add UserEntity to RoleEntities
            savedUser.setUserRole(roleList
                    .stream()
                    .map(role -> new UserRoleEntity(savedUser.getId(), role.getId(),role,savedUser))
                    .toList()
            );
            roleList.forEach(
                    roleEntity -> userRoleRepository.saveUserRole(savedUser.getId(), roleEntity.getId())
            );

            // Save AdminEntity and get ResponseDto
            AdminDto responseAdminDto = getResponseAdminDto(adminEntity, savedUser, userEntity);

            return new ResponseEntity<>(
                    ResponseDto.success(responseAdminDto, "Admin created successfully!"),
                    HttpStatus.CREATED
            );
        } catch (Exception exception) {
            String error = "Error while creating admin!";
            log.error(error, exception);
            return new ResponseEntity<>(ResponseDto.error(error), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    protected boolean isAdminExist(AdminDto adminDto) {
        return userRepository.findByEmail(adminDto.email()).isPresent() ||
                adminRepository.findByContact(adminDto.contact()).isPresent();
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseDto<AuthResponseDto>> authenticateAdminLogin(LoginDto loginDto) {
        return null;
    }

    @Override
    @Transactional
    public Optional<AdminDto> getAdminByEmail(String email) {
        try {
            UserEntity userEntity = userRepository.findByEmail(email).orElse(null);
            return getAdminDto(userEntity);
        } catch (Exception e) {
            String error = "Error while fetching admin by email!";
            log.error(error, e);
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public Optional<AdminDto> getAdminById(Long id) {
        try {
            UserEntity userEntity = userRepository.findById(id).orElse(null);
            return getAdminDto(userEntity);
        } catch (Exception e) {
            String error = "Error while fetching admin by id!";
            log.error(error, e);
            return Optional.empty();
        }
    }

    @Transactional
    protected Optional<AdminDto> getAdminDto(UserEntity userEntity) {
        if (userEntity != null) {
            AdminEntity adminEntity = userEntity.getAdmin();
            return Optional.ofNullable(
                    AdminDto.builder()
                            .id(adminEntity.getId())
                            .firstName(adminEntity.getFirstName())
                            .lastName(adminEntity.getLastName())
                            .contact(adminEntity.getContact())
                            .email(userEntity.getEmail())
                            .isActive(userEntity.getIsActive())
                            .role(userRoleRepository.getRoleByUserId(userEntity.getId()))
                            .build()
            );
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseDto<AdminDto>> updateAdmin(AdminDto adminDto) {
        try {
            // Check if admin already exists in update operation
            if ((adminDto.id() == null || !adminRepository.existsById(adminDto.id()))) {
                return new ResponseEntity<>(
                        ResponseDto.error("Admin not found!"),
                        HttpStatus.NOT_FOUND
                );
            }

            UserEntity userEntity = userRepository.findById(adminDto.id()).orElse(null);
            assert userEntity != null;
            userEntity.setEmail(adminDto.email());
            userEntity.setPassword(adminDto.password());
            userEntity.setIsActive(adminDto.isActive());

            // Update AdminEntity
            AdminEntity adminEntity = adminRepository.findById(adminDto.id()).orElse(null);
            assert adminEntity != null;
            adminEntity.setContact(adminDto.contact());
            adminEntity.setFirstName(adminDto.firstName());
            adminEntity.setLastName(adminDto.lastName());

            // Save UserEntity
            UserEntity savedUser = userRepository.save(userEntity);

            // Save AdminEntity and get ResponseDto
            AdminDto responseAdminDto = getResponseAdminDto(adminEntity, savedUser, userEntity);

            return new ResponseEntity<>(
                    ResponseDto.success(responseAdminDto, "Admin updated successfully!"),
                    HttpStatus.CREATED
            );
        } catch (Exception exception) {
            String error = "Error while updating admin!";
            log.error(error, exception);
            return new ResponseEntity<>(ResponseDto.error(error), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional
    protected AdminDto getResponseAdminDto(AdminEntity adminEntity, UserEntity savedUser, UserEntity userEntity) {
        adminEntity.setUser(savedUser);
        AdminEntity savedAdminEntity = adminRepository.save(adminEntity);

        // Create ResponseDto
        return AdminDto.builder()
                .id(savedAdminEntity.getId())
                .email(savedUser.getEmail())
                .firstName(savedAdminEntity.getFirstName())
                .lastName(savedAdminEntity.getLastName())
                .contact(savedAdminEntity.getContact())
                .role(savedUser.getUserRole().stream().map(role -> role.getRole().getRole()).toList())
                .isActive(userEntity.getIsActive())
                .build();
    }

    @Override
    @Transactional
    public RoleDto updateAdminRole(RoleDto roleDto) {
        try {
            List<Role> existingRole = userRoleRepository.getRoleByUserId(roleDto.userId());
            List<Role> updatedRole = roleDto.role();
            List<Role> removedRole = new ArrayList<>(existingRole);
            removedRole.removeAll(updatedRole);

            updatedRole.forEach(role -> {
                if (!userRoleRepository.existsByUserIdAndRoleId
                        (roleDto.userId(), roleRepository.findByRole(role).getId()))
                {
                    userRoleRepository.saveUserRole(roleDto.userId(), roleRepository.findByRole(role).getId());
                }
            });

            removedRole.forEach(role ->
                    userRoleRepository.deleteUserRole(roleDto.userId(), roleRepository.findByRole(role).getId())
            );

            return roleDto;
        } catch (Exception e) {
            String error = "Error while updating admin role!";
            log.error(error, e);
            return null;
        }
    }

    @Override
    @Transactional
    public List<AdminDto> getAllAdmins() {
        try {
            List<Object> allAdmins = adminRepository.findAllAdmins();
            return allAdmins.stream().map(object -> {
                Object[] objects = (Object[]) object;
                return AdminDto.builder()
                        .id((Long) objects[0])
                        .firstName((String) objects[1])
                        .lastName((String) objects[2])
                        .contact((String) objects[3])
                        .email((String) objects[4])
                        .isActive((boolean) objects[5])
                        .role(userRoleRepository.getRoleByUserId((Long) objects[0]))
                        .build();
            }).toList();

        } catch (Exception e) {
            String error = "Error while fetching all admins!";
            log.error(error, e);
        }
        return List.of();
    }
}
