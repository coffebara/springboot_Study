package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderSearch;
import jpabook.jpashop.domain.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll(OrderSearch orderSearch) {

        return em.createQuery("select o from Order o join o.member m " +
                        " where o.status = :status " +
                        " and m.name like :name", Order.class)
                .setParameter("status", orderSearch.getOrderStatus())
                .setParameter("name", orderSearch.getMemberName())
                .setMaxResults(1000) //최대 1000건
                .getResultList();

    }
    //동적쿼리를 만드는 방법 3가지
    // 1. 문자열 더하기
    //모든 경우의 수를 만들어야 하기 때문에 간단한 조건도 한페이지가 된다.
    //이는 많은 문제를 야기하기 때문에 지양하는 게 좋음, 차라리 MyBatis가 동적쿼리 생성에는 유리함
    // 2. JPA criteria
    // 문자열보다는 낫지만, 한 눈에 쿼리문이 들어오지 않기 때문에 유지보수가 안됨.
    // 3. QueryDSL
    // 베스트
}
