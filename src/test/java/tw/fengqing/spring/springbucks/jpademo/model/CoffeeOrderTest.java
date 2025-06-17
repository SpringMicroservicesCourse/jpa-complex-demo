package tw.fengqing.spring.springbucks.jpademo.model;

import org.junit.jupiter.api.Test;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;

/**
 * 咖啡訂單實體類別測試
 * 測試訂單的建立、商品關聯和狀態管理
 */
public class CoffeeOrderTest {

    @Test
    public void testOrderCreation() {
        // 建立測試資料
        String customer = "王小明";
        Coffee coffee = Coffee.builder()
                .name("拿鐵咖啡")
                .price(Money.of(CurrencyUnit.of("TWD"), 100.0))
                .build();

        // 建立訂單
        CoffeeOrder order = CoffeeOrder.builder()
                .customer(customer)
                .items(Collections.singletonList(coffee))
                .state(OrderState.INIT)
                .build();

        // 驗證訂單屬性
        assertNotNull(order);
        assertEquals(customer, order.getCustomer());
        assertEquals(1, order.getItems().size());
        assertEquals(OrderState.INIT, order.getState());
    }

    @Test
    public void testOrderWithMultipleItems() {
        // 建立測試資料
        Coffee latte = Coffee.builder()
                .name("拿鐵咖啡")
                .price(Money.of(CurrencyUnit.of("TWD"), 100.0))
                .build();

        Coffee espresso = Coffee.builder()
                .name("濃縮咖啡")
                .price(Money.of(CurrencyUnit.of("TWD"), 80.0))
                .build();

        // 建立包含多個商品的訂單
        CoffeeOrder order = CoffeeOrder.builder()
                .customer("李小華")
                .items(Arrays.asList(latte, espresso))
                .state(OrderState.INIT)
                .build();

        // 驗證訂單內容
        assertEquals(2, order.getItems().size());
        assertTrue(order.getItems().contains(latte));
        assertTrue(order.getItems().contains(espresso));
    }

    @Test
    public void testOrderStateTransition() {
        // 建立測試資料
        CoffeeOrder order = CoffeeOrder.builder()
                .customer("張三")
                .items(Collections.emptyList())
                .state(OrderState.INIT)
                .build();

        // 驗證初始狀態
        assertEquals(OrderState.INIT, order.getState());

        // 模擬狀態轉換
        order.setState(OrderState.PAID);
        assertEquals(OrderState.PAID, order.getState());

        order.setState(OrderState.BREWING);
        assertEquals(OrderState.BREWING, order.getState());

        order.setState(OrderState.BREWED);
        assertEquals(OrderState.BREWED, order.getState());

        order.setState(OrderState.TAKEN);
        assertEquals(OrderState.TAKEN, order.getState());
    }

    @Test
    public void testOrderEquality() {
        // 建立兩個相同屬性的訂單
        Coffee coffee = Coffee.builder()
                .name("拿鐵咖啡")
                .price(Money.of(CurrencyUnit.of("TWD"), 100.0))
                .build();

        CoffeeOrder order1 = CoffeeOrder.builder()
                .customer("王小明")
                .items(Collections.singletonList(coffee))
                .state(OrderState.INIT)
                .build();

        CoffeeOrder order2 = CoffeeOrder.builder()
                .customer("王小明")
                .items(Collections.singletonList(coffee))
                .state(OrderState.INIT)
                .build();

        // 驗證物件相等性
        assertNotSame(order1, order2);
        assertEquals(order1, order2);
        assertEquals(order1.hashCode(), order2.hashCode());
    }
} 