package edu.icet.trendify.service;

import edu.icet.trendify.dto.user.AuthResponseDto;
import edu.icet.trendify.dto.user.LoginDto;
import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.dto.user.AdminDto;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    ResponseDto<AdminDto> registerAdmin(AdminDto adminDto);
    ResponseDto<AuthResponseDto> authenticateAdminLogin(LoginDto loginDto);
    Optional<AdminDto> getAdminByEmail(String email);
    Optional<AdminDto> getAdminById(Long id);
    ResponseDto<AdminDto> updateAdmin(AdminDto adminDto);
    List<AdminDto> getAllAdmins();
}
