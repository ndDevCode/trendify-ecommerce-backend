package edu.icet.trendify.service;

import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.dto.billing.BillingInfoDto;

import javax.swing.text.html.Option;
import java.net.http.HttpResponse;
import java.util.Optional;

public interface BillingInfoService {
    ResponseDto<BillingInfoDto> createBill(BillingInfoDto billingInfoDto);
    Optional<BillingInfoDto> getBillingInfoByOrderId(Long orderId);
}
