# Spring Boot JPA 示範專案 - 咖啡訂單系統

## 專案簡介
這是一個使用 Spring Boot 和 JPA 實作的咖啡訂單系統示範專案，主要用於展示物件關聯對應（O-R Mapping）的實作方式。本專案採用 Spring Data JPA 作為資料存取層，並使用 H2 記憶體資料庫進行開發測試。

## 技術架構
- **開發環境**
  - Java 21
  - Spring Boot 3.4.5
  - Spring Data JPA
  - H2 Database
  - Lombok
  - Joda Money 1.0.1

## 主要功能
- 咖啡訂單管理
- 商品資料維護
- 訂單狀態追蹤
- 價格計算與管理

## 專案結構
```
src/main/java
├── controller    // 控制器層，處理 HTTP 請求
├── service      // 服務層，處理業務邏輯
├── repository   // 資料存取層，處理資料庫操作
├── model        // 實體類別，定義資料模型
└── config       // 設定檔，包含系統配置
```

## 開發環境建置
1. 確保已安裝以下工具：
   - JDK 21
   - Maven 3.6+
   - IDE（建議使用 IntelliJ IDEA 或 Eclipse）

2. 克隆專案
   ```bash
   git clone https://github.com/SpringMicroservicesCourse/jpa-complex-demo
   ```

3. 編譯專案
   ```bash
   mvn clean install
   ```

4. 執行專案
   ```bash
   mvn spring-boot:run
   ```

## 重要程式碼說明
- 實體類別（Entity）使用 JPA 註解進行物件關聯對應
- 使用 Lombok 簡化程式碼，減少樣板程式碼
- 採用 Joda Money 處理貨幣計算，確保精確性

## 注意事項
1. 本專案使用 H2 記憶體資料庫，重啟後資料會重置
2. 開發時請注意遵循 RESTful API 設計原則
3. 重要業務邏輯請務必加入適當的註解說明

## 貢獻指南
1. Fork 本專案
2. 建立新的功能分支
3. 提交變更
4. 發起 Pull Request

## 授權說明
本專案採用 MIT 授權條款，詳見 LICENSE 檔案。