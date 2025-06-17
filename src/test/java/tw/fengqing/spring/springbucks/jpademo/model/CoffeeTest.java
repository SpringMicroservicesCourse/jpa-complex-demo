package tw.fengqing.spring.springbucks.jpademo.model;

import org.junit.jupiter.api.Test;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 咖啡實體類別測試
 * 測試咖啡商品的建立和屬性設定
 */
public class CoffeeTest {

    @Test
    public void testCoffeeCreation() {
        // 建立測試資料
        String name = "拿鐵咖啡";
        Money price = Money.of(CurrencyUnit.of("TWD"), 100.0);

        // 使用建造者模式建立咖啡物件
        Coffee coffee = Coffee.builder()
                .name(name)
                .price(price)
                .build();

        // 驗證物件屬性
        assertNotNull(coffee);
        assertEquals(name, coffee.getName());
        assertEquals(price, coffee.getPrice());
    }

    @Test
    public void testCoffeePriceConversion() {
        // 建立測試資料
        Money price = Money.of(CurrencyUnit.of("TWD"), 150.0);
        Coffee coffee = Coffee.builder()
                .name("美式咖啡")
                .price(price)
                .build();

        // 驗證價格轉換
        assertEquals(15000, coffee.getPrice().getAmountMinorLong());
        assertEquals("TWD", coffee.getPrice().getCurrencyUnit().getCode());
    }

    @Test
    public void testCoffeeEquality() {
        // 建立兩個相同屬性的咖啡物件
        Coffee coffee1 = Coffee.builder()
                .name("拿鐵咖啡")
                .price(Money.of(CurrencyUnit.of("TWD"), 100.0))
                .build();

        Coffee coffee2 = Coffee.builder()
                .name("拿鐵咖啡")
                .price(Money.of(CurrencyUnit.of("TWD"), 100.0))
                .build();

        // 驗證物件相等性
        assertNotSame(coffee1, coffee2);
        assertEquals(coffee1, coffee2);
        assertEquals(coffee1.hashCode(), coffee2.hashCode());
    }
} 