package edu.icet.trendify.controller;

import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.dto.user.AdminDto;
import edu.icet.trendify.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1")
@CrossOrigin
public class AdminController {

    private final AdminService adminService;
    @PostMapping("/admin")
    public ResponseDto<AdminDto> registerAdmin(@RequestBody AdminDto adminDto){
        return adminService.registerAdmin(adminDto);
    }
}
