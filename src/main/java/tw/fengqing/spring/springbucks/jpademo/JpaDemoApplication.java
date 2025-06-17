package tw.fengqing.spring.springbucks.jpademo;

import tw.fengqing.spring.springbucks.jpademo.model.Coffee;
import tw.fengqing.spring.springbucks.jpademo.model.CoffeeOrder;
import tw.fengqing.spring.springbucks.jpademo.model.OrderState;
import tw.fengqing.spring.springbucks.jpademo.repository.CoffeeOrderRepository;
import tw.fengqing.spring.springbucks.jpademo.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableJpaRepositories  // 啟用 JPA Repository 功能
@EnableTransactionManagement  // 啟用交易管理
@Slf4j  // 啟用 Lombok 日誌功能
public class JpaDemoApplication implements ApplicationRunner {
	// 注入咖啡資料存取物件
	@Autowired
	private CoffeeRepository coffeeRepository;
	// 注入訂單資料存取物件
	@Autowired
	private CoffeeOrderRepository orderRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	/**
	 * 應用程式啟動時執行的初始化方法
	 * @Transactional 確保資料操作的交易完整性
	 */
	@Override
	@Transactional
	public void run(ApplicationArguments args) throws Exception {
		initOrders();  // 初始化訂單資料
		findOrders();  // 查詢訂單資料
	}

	/**
	 * 初始化訂單資料
	 * 建立咖啡商品和訂單資料
	 */
	private void initOrders() {
		// 建立拿鐵咖啡商品
		Coffee latte = Coffee.builder().name("latte")
				.price(Money.of(CurrencyUnit.of("TWD"), 100.0))	
				.build();
		coffeeRepository.save(latte);
		log.info("Coffee: {}", latte);

		// 建立濃縮咖啡商品
		Coffee espresso = Coffee.builder().name("espresso")
				.price(Money.of(CurrencyUnit.of("TWD"), 150.0))
				.build();
		coffeeRepository.save(espresso);
		log.info("Coffee: {}", espresso);

		// 建立第一筆訂單（單一商品）
		CoffeeOrder order = CoffeeOrder.builder()
				.customer("Li Lei")
				.items(Collections.singletonList(espresso))
				.state(OrderState.INIT)
				.build();
		orderRepository.save(order);
		log.info("Order: {}", order);

		// 建立第二筆訂單（多個商品）
		order = CoffeeOrder.builder()
				.customer("Li Lei")
				.items(Arrays.asList(espresso, latte))
				.state(OrderState.INIT)
				.build();
		orderRepository.save(order);
		log.info("Order: {}", order);
	}

	/**
	 * 查詢訂單資料
	 * 展示不同的查詢方法
	 */
	private void findOrders() {
		// 查詢所有咖啡商品，依 ID 降序排序
		coffeeRepository
				.findAll(Sort.by(Sort.Direction.DESC, "id"))
				.forEach(c -> log.info("Loading {}", c));

		// 查詢最近更新的前三筆訂單
		List<CoffeeOrder> list = orderRepository.findTop3ByOrderByUpdateTimeDescIdAsc();
		log.info("findTop3ByOrderByUpdateTimeDescIdAsc: {}", getJoinedOrderId(list));

		// 查詢特定客戶的所有訂單
		list = orderRepository.findByCustomerOrderById("Li Lei");
		log.info("findByCustomerOrderById: {}", getJoinedOrderId(list));

		// 注意：此處需要交易支援，否則會發生 LazyInitializationException
		list.forEach(o -> {
			log.info("Order {}", o.getId());
			o.getItems().forEach(i -> log.info("  Item {}", i));
		});

		// 查詢包含特定咖啡商品的訂單
		list = orderRepository.findByItems_Name("latte");
		log.info("findByItems_Name: {}", getJoinedOrderId(list));
	}

	/**
	 * 將訂單 ID 列表轉換為逗號分隔的字串
	 * @param list 訂單列表
	 * @return 逗號分隔的訂單 ID 字串
	 */
	private String getJoinedOrderId(List<CoffeeOrder> list) {
		return list.stream().map(o -> o.getId().toString())
				.collect(Collectors.joining(","));
	}
}

