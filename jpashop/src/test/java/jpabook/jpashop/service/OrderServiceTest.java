package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.excpetion.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional // Test에 한하여, 기본 rollback이 되기 때문에 insert문 안나감 -> 영속성 컨텍스트가 commit을 안함.
class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @DisplayName("상품주문")
    @Test
    public void order() throws Exception {
        //given
        Member member = createMember();

        Item book = createBook("시골 JPA", 10000, 10);

        int orderCount = 2;

        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals(OrderStatus.ORDER, getOrder.getStatus(), "상품 주문시 상태는 ORDER");
        assertEquals(1, getOrder.getOrderItems().size(), "주문한 상품 종류 수가 정확해야 한다.");
        assertEquals(10000 * orderCount, getOrder.getTotalPrice(), "주문 가격은 가격 * 수량이다.");
        assertEquals(8, book.getStockQuantity(), "주문하면 재고가 줄어야 한다.");

    }

    @DisplayName("상품주문 재고수량초과")
    @Test
    public void orderOverStockQuantity() throws Exception {
        //given
        Member member = createMember();
        Item book = createBook("시골 JPA", 10000, 10);

        int orderCount = 11;


        //then
        assertThrows(NotEnoughStockException.class, () -> orderService.order(member.getId(), book.getId(), orderCount), "재고 수량 부족 예외가 발생해야 한다.");
    }

    @DisplayName("주문취소")
    @Test
    public void cancelOrder() throws Exception {
        //given
        Member member = createMember();
        Item book = createBook("시골 JPA", 10000, 10);

        int orderCount = 1;

        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals(OrderStatus.CANCEL, getOrder.getStatus(), "주문 취소시 상태는 CANCEL");
        assertEquals(10, book.getStockQuantity(), "주문 취소된 상품은 그 만큼 재고가 증가해야 한다.");
    }

    private Item createBook(String name, int price, int stockQuantity) {
        Item book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }




}