package edu.icet.trendify.service;

import edu.icet.trendify.dto.user.AuthResponseDto;
import edu.icet.trendify.dto.user.LoginDto;
import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.dto.user.AdminDto;
import edu.icet.trendify.dto.user.RoleDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    ResponseEntity<ResponseDto<AdminDto>> registerAdmin(AdminDto adminDto);
    ResponseEntity<ResponseDto<AuthResponseDto>> authenticateAdminLogin(LoginDto loginDto);
    Optional<AdminDto> getAdminByEmail(String email);
    Optional<AdminDto> getAdminById(Long id);
    ResponseEntity<ResponseDto<AdminDto>> updateAdmin(AdminDto adminDto);
    RoleDto updateAdminRole(RoleDto roleDto);
    List<AdminDto> getAllAdmins();
}
