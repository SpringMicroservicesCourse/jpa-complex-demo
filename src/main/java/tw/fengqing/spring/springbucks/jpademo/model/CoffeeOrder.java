package tw.fengqing.spring.springbucks.jpademo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * 咖啡訂單實體類別
 * 使用 JPA 註解進行物件關聯對應
 * 與 Coffee 實體建立多對多關聯
 */
@Entity
@Table(name = "T_ORDER")  // 指定資料表名稱
@Data  // 自動生成 getter/setter 等方法
@ToString(callSuper = true)  // 包含父類別欄位
@NoArgsConstructor  // 無參數建構子
@AllArgsConstructor  // 全參數建構子
@Builder  // 使用建造者模式
@EqualsAndHashCode(callSuper = true)  // 包含父類別欄位
public class CoffeeOrder extends BaseEntity implements Serializable {
    /**
     * 訂購客戶名稱
     */
    private String customer;

    /**
     * 訂單中的咖啡商品列表
     * 使用多對多關聯，透過中間表 T_ORDER_COFFEE 建立關聯
     * 依 ID 排序確保商品順序一致
     */
    @ManyToMany
    @JoinTable(name = "T_ORDER_COFFEE")
    @OrderBy("id")
    private List<Coffee> items;

    /**
     * 訂單狀態
     * 使用列舉型別，不允許為空
     */
    @Enumerated
    @Column(nullable = false)
    private OrderState state;
}
