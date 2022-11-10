package shop.kokodo.calculateservice.repository.calculate;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import shop.kokodo.calculateservice.dto.CalculateDto;
import shop.kokodo.calculateservice.dto.CalculateSearchCondition;
import shop.kokodo.calculateservice.dto.QCalculateDto;
import shop.kokodo.calculateservice.entity.Calculate;
import shop.kokodo.calculateservice.enums.calculate.CalculateType;
import shop.kokodo.calculateservice.enums.calculate.ProvideStatus;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

import static shop.kokodo.calculateservice.entity.QCalculate.calculate;

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
public class CalculateRepositoryImpl implements CalculateRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public CalculateRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<CalculateDto> searchCalculate(CalculateSearchCondition condition, Pageable pageable) {
        List<CalculateDto> content = queryFactory
                .select(new QCalculateDto(
                        calculate.id,
                        calculate.createdDate,
                        calculate.calculateType,
                        calculate.supportRate,
                        calculate.provideStatus,
                        calculate.finalPaymentCost))
                .from(calculate)
                .where(
                        sellerIdEq(condition.getSellerId()),
                        calculateIdEq(condition.getId()),
                        dateGoeAndLoe(condition.getStartDate(), condition.getEndDate()),
                        providStatuseEq(condition.getProvideStatus()),
                        calculateTypeEq(condition.getCalculateType()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Calculate> countQuery = queryFactory
                .select(calculate)
                .from(calculate)
                .where(
                        sellerIdEq(condition.getSellerId()),
                        calculateIdEq(condition.getId()),
                        dateGoeAndLoe(condition.getStartDate(), condition.getEndDate()),
                        providStatuseEq(condition.getProvideStatus()),
                        calculateTypeEq(condition.getCalculateType()));
        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    private BooleanExpression calculateIdEq(Long id) {
        return id == null ? null : calculate.id.eq(id);
    }

    private BooleanExpression sellerIdEq(Long sellerId) {
        return sellerId == null ? null : calculate.sellerId.eq(sellerId);
    }

    private BooleanExpression dateGoeAndLoe(LocalDateTime startDate, LocalDateTime endDate) {
        return startDate == null ? null : calculate.createdDate.between(startDate, endDate);
    }

    private BooleanExpression calculateTypeEq(CalculateType calculateType) {
        return calculateType == null ? null : calculate.calculateType.eq(calculateType);
    }

    private BooleanExpression providStatuseEq(ProvideStatus provideStatus) {
        return provideStatus == null ? null : calculate.provideStatus.eq(provideStatus);
    }
}
