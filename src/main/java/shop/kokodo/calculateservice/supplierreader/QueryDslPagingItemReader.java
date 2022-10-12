//TODO: Spring Batch에서 QueryDsl을 사용하기위해 존재하는 객체

//package shop.kokodo.calculateservice.supplierreader;
//
//import org.springframework.batch.item.database.AbstractPagingItemReader;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import java.util.Map;
//
///**
// * packageName    : shop.kokodo.calculateservice.supplierreader
// * fileName       : QueryDslPagingItemReader
// * author         : namhyeop
// * date           : 2022/09/29
// * description    :
// * Spring Batch에서 QueryDsl을 사용하기위해 존재하는 객체
// * ===========================================================
// * DATE              AUTHOR             NOTE
// * -----------------------------------------------------------
// * 2022/09/29        namhyeop       최초 생성
// */
//public class QueryDslPagingItemReader <T> extends AbstractPagingItemReader <T>{
//    public class QuerydslPagingItemReader<T> extends AbstractPagingItemReader<T> {
//
//        protected final Map<String, Object> jpaPropertyMap = new HashMap<>();
//        protected EntityManagerFactory entityManagerFactory;
//        protected EntityManager entityManager;
//        protected Function<JPAQueryFactory, JPAQuery<T>> queryFunction;
//        protected boolean transacted = true;//default value
//
//        protected QuerydslPagingItemReader() {
//            setName(ClassUtils.getShortName(QuerydslPagingItemReader.class));
//        }
//
//        public QuerydslPagingItemReader(EntityManagerFactory entityManagerFactory,
//                                        int pageSize,
//                                        Function<JPAQueryFactory, JPAQuery<T>> queryFunction) {
//            this();
//            this.entityManagerFactory = entityManagerFactory;
//            this.queryFunction = queryFunction;
//            setPageSize(pageSize);
//        }
//
//        public void setTransacted(boolean transacted) {
//            this.transacted = transacted;
//        }
//
//        @Override
//        protected void doOpen() throws Exception {
//            super.doOpen();
//
//            entityManager = entityManagerFactory.createEntityManager(jpaPropertyMap);
//            if (entityManager == null) {
//                throw new DataAccessResourceFailureException("Unable to obtain an EntityManager");
//            }
//        }
//
//        @Override
//        @SuppressWarnings("unchecked")
//        protected void doReadPage() {
//
//            clearIfTransacted();
//
//            JPAQuery<T> query = createQuery()
//                    .offset(getPage() * getPageSize())
//                    .limit(getPageSize());
//
//            initResults();
//
//            fetchQuery(query);
//        }
//
//        protected void clearIfTransacted() {
//            if (transacted) {
//                entityManager.clear();
//            }
//        }
//
//        protected JPAQuery<T> createQuery() {
//            JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
//            return queryFunction.apply(queryFactory);
//        }
//
//        protected void initResults() {
//            if (CollectionUtils.isEmpty(results)) {
//                results = new CopyOnWriteArrayList<>();
//            } else {
//                results.clear();
//            }
//        }
//
//        protected void fetchQuery(JPAQuery<T> query) {
//            if (!transacted) {
//                List<T> queryResult = query.fetch();
//                for (T entity : queryResult) {
//                    entityManager.detach(entity);
//                    results.add(entity);
//                }
//            } else {
//                results.addAll(query.fetch());
//            }
//        }
//
//        @Override
//        protected void doJumpToPage(int itemIndex) {
//        }
//
//        @Override
//        protected void doClose() throws Exception {
//            entityManager.close();
//            super.doClose();
//        }
//    }
//}
