package tw.fengqing.spring.springbucks.jpademo.model;

import org.junit.jupiter.api.Test;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 貨幣轉換器測試
 * 測試 Money 物件與資料庫欄位之間的轉換
 */
public class MoneyConverterTest {

    private final MoneyConverter converter = new MoneyConverter();

    @Test
    public void testConvertToDatabaseColumn() {
        // 測試正常金額轉換
        Money money = Money.of(CurrencyUnit.of("TWD"), 100.0);
        Long result = converter.convertToDatabaseColumn(money);
        assertEquals(10000L, result);

        // 測試零金額轉換
        money = Money.of(CurrencyUnit.of("TWD"), 0.0);
        result = converter.convertToDatabaseColumn(money);
        assertEquals(0L, result);

        // 測試負金額轉換
        money = Money.of(CurrencyUnit.of("TWD"), -50.0);
        result = converter.convertToDatabaseColumn(money);
        assertEquals(-5000L, result);

        // 測試空值轉換
        assertNull(converter.convertToDatabaseColumn(null));
    }

    @Test
    public void testConvertToEntityAttribute() {
        // 測試正常金額轉換
        Money result = converter.convertToEntityAttribute(10000L);
        assertNotNull(result);
        assertEquals("TWD", result.getCurrencyUnit().getCode());
        assertEquals(100.0, result.getAmount().doubleValue());

        // 測試零金額轉換
        result = converter.convertToEntityAttribute(0L);
        assertNotNull(result);
        assertEquals(0.0, result.getAmount().doubleValue());

        // 測試負金額轉換
        result = converter.convertToEntityAttribute(-5000L);
        assertNotNull(result);
        assertEquals(-50.0, result.getAmount().doubleValue());

        // 測試空值轉換
        assertNull(converter.convertToEntityAttribute(null));
    }

    @Test
    public void testRoundTripConversion() {
        // 測試金額轉換的往返一致性
        Money original = Money.of(CurrencyUnit.of("TWD"), 123.45);
        Long dbValue = converter.convertToDatabaseColumn(original);
        Money converted = converter.convertToEntityAttribute(dbValue);
        
        assertEquals(original, converted);
        assertEquals(original.getAmount(), converted.getAmount());
        assertEquals(original.getCurrencyUnit(), converted.getCurrencyUnit());
    }
} 