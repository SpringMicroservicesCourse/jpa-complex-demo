package tw.fengqing.spring.springbucks.jpademo.repository; 

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {
    List<T> findTop3ByOrderByUpdateTimeDescIdAsc();
}
