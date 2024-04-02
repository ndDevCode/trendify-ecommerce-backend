package edu.icet.trendify.service.impl;

import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.dto.user.AdminDto;
import edu.icet.trendify.dto.user.AuthResponseDto;
import edu.icet.trendify.dto.user.LoginDto;
import edu.icet.trendify.entity.user.AdminEntity;
import edu.icet.trendify.entity.user.RoleEntity;
import edu.icet.trendify.entity.user.UserEntity;
import edu.icet.trendify.repository.user.AdminRepository;
import edu.icet.trendify.repository.user.RoleRepository;
import edu.icet.trendify.repository.user.UserRepository;
import edu.icet.trendify.service.AdminService;
import edu.icet.trendify.util.mapper.AdminMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public ResponseDto<AdminDto> registerAdmin(AdminDto adminDto) {
        try {
            // Check if Admin already exists
            if(isAdminExist(adminDto)){
                return new ResponseDto<>(
                        false,"Admin already exists",null,HttpStatus.CONFLICT
                );
            }

            // Get RoleEntity from roleRepository
            List<RoleEntity> roleList = adminDto.role().stream().map(roleRepository::findByRole).toList();

            // Create UserEntity
            UserEntity userEntity = UserEntity.builder()
                    .email(adminDto.email())
                    .password(adminDto.password())
                    .isActive(adminDto.isActive())
                    .role(roleList)
                    .build();

            // Save UserEntity
            UserEntity savedUser = userRepository.save(userEntity);

            // Add UserEntity to RoleEntities
            roleList.forEach(roleEntity -> roleEntity.getUser().add(savedUser));

            // Save AdminEntity
            AdminEntity adminEntity = adminMapper.toEntity(adminDto);
            adminEntity.setUser(userEntity);
            AdminEntity savedAdminEntity = adminRepository.save(adminEntity);

            // Create ResponseDto
            AdminDto responseAdminDto = AdminDto.builder()
                    .id(savedAdminEntity.getId())
                    .email(userEntity.getEmail())
                    .firstName(savedAdminEntity.getFirstName())
                    .lastName(savedAdminEntity.getLastName())
                    .contact(savedAdminEntity.getContact())
                    .role(userEntity.getRole().stream().map(RoleEntity::getRole).toList())
                    .isActive(userEntity.getIsActive())
                    .build();

            return new ResponseDto<>(
                    true,
                    "Admin Registered Successfully!",
                    responseAdminDto,
                    HttpStatus.CREATED
            );

        } catch (Exception exception) {
            String error = "Error while registering admin!";
            log.error(error, exception);
            return new ResponseDto<>(false, error, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean isAdminExist(AdminDto adminDto) {
        return userRepository.findByEmail(adminDto.email()).isPresent() ||
                adminRepository.findByContact(adminDto.contact()).isPresent();
    }

    @Override
    public ResponseDto<AuthResponseDto> authenticateAdminLogin(LoginDto loginDto) {
        return null;
    }

    @Override
    public Optional<AdminDto> getAdminByEmail(String email) {
        try {
            UserEntity entity = userRepository.findByEmail(email).orElse(null);
            if (entity != null) {
                return Optional.ofNullable(adminMapper.toDto(entity.getAdmin()));
            }
            return Optional.empty();
        } catch (Exception e) {
            String error = "Error while fetching admin by email!";
            log.error(error, e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<AdminDto> getAdminById(Long id) {
        try {
            AdminEntity entity = adminRepository.findById(id).orElse(null);
            return Optional.ofNullable(adminMapper.toDto(entity));
        } catch (Exception e) {
            String error = "Error while fetching admin by id!";
            log.error(error, e);
            return Optional.empty();
        }
    }

    @Override
    public ResponseDto<AdminDto> updateAdmin(AdminDto adminDto) {
        if (adminDto.id() == null || !adminRepository.existsById(adminDto.id())) {
            return new ResponseDto<>(
                    false,
                    "Admin Id not found!",
                    null,
                    HttpStatus.NOT_FOUND
            );
        }

        try {
            AdminEntity entity = adminMapper.toEntity(adminDto);
            AdminEntity savedEntity = adminRepository.save(entity);
            return new ResponseDto<>(
                    true,
                    "Admin Updated Successfully!",
                    adminMapper.toDto(savedEntity),
                    HttpStatus.CREATED
            );

        } catch (Exception e) {
            String error = "Error while updating admin!";
            log.error(error, e);
            return new ResponseDto<>(false, error, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<AdminDto> getAllAdmins() {
        try {
            return adminRepository.findAll().stream().map(adminMapper::toDto).toList();
        } catch (Exception e) {
            String error = "Error while fetching all admins!";
            log.error(error, e);
        }
        return List.of();
    }
}
