package edu.icet.trendify.service.impl;

import edu.icet.trendify.dto.ResponseDto;
import edu.icet.trendify.dto.billing.BillingInfoDto;
import edu.icet.trendify.entity.billing.BillingInfoEntity;
import edu.icet.trendify.repository.billing.BillingInfoRepository;
import edu.icet.trendify.repository.order.OrderRepository;
import edu.icet.trendify.service.BillingInfoService;
import edu.icet.trendify.util.mapper.BillingInfoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BillingInfoServiceImpl implements BillingInfoService {

    private final BillingInfoRepository billingInfoRepository;
    private final OrderRepository orderRepository;
    private final BillingInfoMapper billingInfoMapper;

    @Override
    public ResponseDto<BillingInfoDto> createBill(BillingInfoDto billingInfoDto) {
        try {
            BillingInfoEntity billingInfoEntity = billingInfoRepository.save(billingInfoMapper.toEntity(billingInfoDto));
            return ResponseDto.success(billingInfoMapper.toDto(billingInfoEntity), "Bill created successfully!");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseDto.error("Error while creating bill!");
        }
    }

    @Override
    public Optional<BillingInfoDto> getBillingInfoByOrderId(Long orderId) {
        return billingInfoRepository.findByOrder_Id(orderId).map(billingInfoMapper::toDto);
    }
}
