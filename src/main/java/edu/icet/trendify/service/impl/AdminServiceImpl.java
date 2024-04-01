package edu.icet.trendify.service.impl;

import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.dto.user.AdminDto;
import edu.icet.trendify.dto.user.AuthResponseDto;
import edu.icet.trendify.dto.user.LoginDto;
import edu.icet.trendify.repository.user.AdminRepository;
import edu.icet.trendify.service.AdminService;
import edu.icet.trendify.util.mapper.AdminMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    @Override
    public ResponseDto<AdminDto> registerAdmin(AdminDto adminDto) {
        return null;
    }

    @Override
    public ResponseDto<AuthResponseDto> authenticateAdminLogin(LoginDto loginDto) {
        return null;
    }

    @Override
    public Optional<AdminDto> getAdminByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<AdminDto> getAdminById(Long id) {
        return Optional.empty();
    }

    @Override
    public ResponseDto<AdminDto> updateAdmin(AdminDto adminDto) {
        return null;
    }

    @Override
    public List<AdminDto> getAllAdmins() {
        return null;
    }
}
