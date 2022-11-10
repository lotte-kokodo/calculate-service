package shop.kokodo.calculateservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.kokodo.calculateservice.dto.ardto.CouponInfo;
import shop.kokodo.calculateservice.entity.ArCoupon;
import shop.kokodo.calculateservice.repository.ArCouponRepository;

import java.util.List;

import static shop.kokodo.calculateservice.entity.ArCoupon.*;

/**
 * packageName    : shop.kokodo.calculateservice.service
 * fileName       : ArCouponService
 * author         : namhyeop
 * date           : 2022/11/02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/11/02        namhyeop       최초 생성
 */

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArCouponService {

    private final ArCouponRepository arCouponRepository;

    @Transactional
    public void createCoupon(CouponInfo couponInfo){
        arCouponRepository.save(createArCoupon(couponInfo));
    }

    public List<ArCoupon> findArCouponList(Long sellerId, Long productId){
        return arCouponRepository.findCouponBySellerId(sellerId);
    }
}
