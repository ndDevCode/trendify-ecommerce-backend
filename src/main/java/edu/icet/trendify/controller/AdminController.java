package edu.icet.trendify.controller;

import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.dto.user.AdminDto;
import edu.icet.trendify.dto.user.RoleDto;
import edu.icet.trendify.exception.UserNotFoundException;
import edu.icet.trendify.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1")
@CrossOrigin
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/admin")
    public ResponseEntity<ResponseDto<AdminDto>> registerAdmin(@RequestBody @Valid AdminDto adminDto) {
        return adminService.registerAdmin(adminDto);
    }

    @PutMapping("/admin")
    public ResponseEntity<ResponseDto<AdminDto>> updateAdmin(@RequestBody @Valid AdminDto adminDto) {
        return adminService.updateAdmin(adminDto);
    }

    @PutMapping("/admin/role")
    public ResponseEntity<ResponseDto<RoleDto>> updateAdminRole(@RequestBody @Valid RoleDto roleDto) {
        return ResponseEntity.of(
                Optional.of(
                        ResponseDto.success(
                                adminService.updateAdminRole(roleDto),
                                "Role updated successfully!")
                )
        );
    }

    @GetMapping("/admin")
    public ResponseEntity<ResponseDto<AdminDto>> getAdmin(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Long id
    ) {
        if (email != null) {
            AdminDto adminDto = adminService.getAdminByEmail(email)
                    .orElseThrow(() -> new UserNotFoundException("User not found by email: " + email));
            return new ResponseEntity<>(
                    ResponseDto.success(adminDto, "Admin fetched successfully!"),
                    HttpStatus.OK
            );
        } else if (id != null) {
            AdminDto adminDto = adminService.getAdminById(id)
                    .orElseThrow(() -> new UserNotFoundException("User not found by id: " + id));
            return new ResponseEntity<>(
                    ResponseDto.success(adminDto, "Admin fetched successfully!"),
                    HttpStatus.OK
            );
        } else {
            throw new IllegalArgumentException("Either email or id must be provided.");
        }
    }


    @GetMapping("/admins")
    public ResponseEntity<ResponseDto<List<AdminDto>>> getAllAdmins() {
        List<AdminDto> adminDto = adminService.getAllAdmins();
        return new ResponseEntity<>(
                ResponseDto.success(adminDto, "Admins fetched successfully!"),
                HttpStatus.OK
        );
    }

}
