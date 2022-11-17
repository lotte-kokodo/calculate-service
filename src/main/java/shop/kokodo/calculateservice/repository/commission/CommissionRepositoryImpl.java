package shop.kokodo.calculateservice.repository.commission;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import shop.kokodo.calculateservice.dto.QSaleListDto;
import shop.kokodo.calculateservice.dto.SaleListDto;
import shop.kokodo.calculateservice.dto.SaleListSearchCondition;
import shop.kokodo.calculateservice.entity.Commission;

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
    public Page<SaleListDto> searchSaleList(SaleListSearchCondition condition, Pageable pageable) {
        List<SaleListDto> content = queryFactory
                .select(new QSaleListDto(
                        commission.sellerId,
                        commission.basic,
                        commission.salesPromotion,
                        commission.firstPaymentDelivery,
                        commission.deliverySupport,
                        commission.discountSupport,
                        commission.mediumCompanyCostRefund,
                        commission.etc,
                        calculate.calculateType,
                        calculate.finalPaymentCost
                ))
                .from(commission)
                .leftJoin(commission.calculate, calculate)
                .where(
                        sellerIdEq(condition.getSellerId()),
                        dateGoeAndLoe(condition.getStartDate(), condition.getEndDate()),
                        providStatuseEq(condition.getSearchCondition()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Commission> countQuery = queryFactory
                .select(commission)
                .from(commission)
                .leftJoin(commission.calculate, calculate)
                .where(
                        sellerIdEq(condition.getSellerId()),
                        dateGoeAndLoe(condition.getStartDate(), condition.getEndDate()),
                        providStatuseEq(condition.getSearchCondition()));
        return PageableExecutionUtils.getPage(content, pageable, () -> countQuery.fetchCount());
    }

    private BooleanExpression sellerIdEq(Long sellerId) {
        return sellerId == null ? null : commission.sellerId.eq(sellerId);
    }

    private BooleanExpression dateGoeAndLoe(LocalDateTime startDate, LocalDateTime endDate) {
        return startDate == null ? null : commission.createdDate.between(startDate, endDate);
    }
    private BooleanExpression providStatuseEq(String searchCondition) {
        return searchCondition == null ? null : null;
    }
}
