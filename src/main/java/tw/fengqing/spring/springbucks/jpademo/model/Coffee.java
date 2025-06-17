package tw.fengqing.spring.springbucks.jpademo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import org.joda.money.Money;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import java.io.Serializable;

/**
 * 咖啡商品實體類別
 * 使用 JPA 註解進行物件關聯對應
 * 繼承自 BaseEntity 以共用基本欄位
 */
@Entity
@Table(name = "T_MENU")  // 指定資料表名稱
@Builder  // 使用建造者模式
@Data  // 自動生成 getter/setter 等方法
@ToString(callSuper = true)  // 包含父類別欄位
@NoArgsConstructor  // 無參數建構子
@AllArgsConstructor  // 全參數建構子
@EqualsAndHashCode(callSuper = true)  // 包含父類別欄位
public class Coffee extends BaseEntity implements Serializable {
    /**
     * 咖啡名稱
     */
    private String name;
    
    /**
     * 咖啡價格
     * 使用 MoneyConverter 進行貨幣型別轉換
     */
    @Column
    @Convert(converter = MoneyConverter.class)
    private Money price;
}
