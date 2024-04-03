package edu.icet.trendify.service;

import edu.icet.trendify.dto.user.*;
import edu.icet.trendify.dto.ResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    ResponseEntity<ResponseDto<AdminDto>> registerAdmin(AdminDto adminDto);
    Optional<AdminDto> getAdminByEmail(String email);
    Optional<AdminDto> getAdminById(Long id);
    ResponseEntity<ResponseDto<AdminDto>> updateAdmin(AdminDto adminDto);
    RoleDto updateAdminRole(RoleDto roleDto);
    List<AdminDto> getAllAdmins();
}
