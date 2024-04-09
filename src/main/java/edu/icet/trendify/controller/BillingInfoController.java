package edu.icet.trendify.controller;

import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.dto.billing.BillingInfoDto;
import edu.icet.trendify.service.BillingInfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/billing-info")
@RequiredArgsConstructor
public class BillingInfoController {
    private final BillingInfoService billingInfoService;

    @PostMapping("")
    public ResponseEntity<ResponseDto<BillingInfoDto>> createBill(@RequestBody @Valid BillingInfoDto billingInfoDto) {
        ResponseDto<BillingInfoDto> bill = billingInfoService.createBill(billingInfoDto);
        if (Boolean.TRUE.equals(bill.getIsSuccess())) {
            return ResponseEntity.ok(bill);
        }
        return ResponseEntity.badRequest().body(bill);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ResponseDto<BillingInfoDto>> getBillingInfoByOrderId(@PathVariable Long orderId) {
        Optional<BillingInfoDto> billingInfoByOrderId = billingInfoService.getBillingInfoByOrderId(orderId);
        return billingInfoByOrderId
                .map(billingInfoDto ->
                        ResponseEntity.ok(
                                ResponseDto.success(billingInfoDto, "Bill fetched successfully!")
                        ))
                .orElseGet(() -> ResponseEntity.badRequest().body(ResponseDto.error("Bill not found!")));
    }
}
