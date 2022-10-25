package shop.kokodo.calculateservice.repository.commission;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import shop.kokodo.calculateservice.dto.*;
import shop.kokodo.calculateservice.entity.Commission;
import shop.kokodo.calculateservice.enums.calculate.CalculateType;
import shop.kokodo.calculateservice.enums.calculate.ProvideStatus;
import shop.kokodo.calculateservice.repository.calculate.CalculateRepositoryCustom;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static shop.kokodo.calculateservice.entity.QCalculate.calculate;
import static shop.kokodo.calculateservice.entity.QCommission.commission;

/**
 * packageName    : shop.kokodo.calculateservice.repository.impl
 * fileName       : CalculalteRepositoryImpl
 * author         : namhyeop
 * date           : 2022/10/06
 * description    :
 * Calculate 동적쿼리를 생성하기 위해 QueryDsl을 활용한 Repository
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/10/06        namhyeop       최초 생성
 */
public class CommissionRepositoryImpl implements CommissionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public CommissionRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<SaleListDto> searchSaleList(SaleListSearchCondition condition) {
        return queryFactory
                .select(new QSaleListDto(
                        commission.sellerId,
                        commission.basic,
                        commission.salesPromotion,
                        commission.firstPaymentDelivery,
                        commission.deliverySupport,
                        commission.discountSupport,
                        commission.mediumCompanyCostRefund,
                        commission.etc,
                        commission.calculate.calculateType,
                        commission.calculate.finalPaymentCost
                ))
                .from(commission)
                .where(
                        sellerIdEq(condition.getSellerId()),
                        dateGoeAndLoe(condition.getStartDate(), condition.getEndDate())
                ).fetch();
    }

    private BooleanExpression sellerIdEq(Long sellerId) {
        return sellerId == null ? null : commission.sellerId.eq(sellerId);
    }

    private BooleanExpression dateGoeAndLoe(LocalDateTime startDate, LocalDateTime endDate) {
        return startDate == null ? null : commission.createdDate.between(startDate, endDate);
    }

}
