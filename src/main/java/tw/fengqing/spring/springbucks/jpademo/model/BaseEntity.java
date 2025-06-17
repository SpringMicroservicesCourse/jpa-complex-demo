package tw.fengqing.spring.springbucks.jpademo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * 基礎實體類別
 * 提供所有實體共用的基本欄位
 * 使用 @MappedSuperclass 註解，表示此類別不會對應到資料表
 */
@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity implements Serializable {
    /**
     * 實體主鍵
     * 使用自動遞增策略
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 建立時間
     * 不可更新，由系統自動設定
     */
    @Column(updatable = false)
    @CreationTimestamp
    private Date createTime;

    /**
     * 更新時間
     * 由系統自動更新
     */
    @UpdateTimestamp
    private Date updateTime;
}
