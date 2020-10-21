package cn.zlmisme.prophet.commons.configuration.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 存在数据源时进行初始化
 * </p>
 *
 * @author liming zeng
 * @create 2020-10-20 15:08
 */
@Configuration
@ConditionalOnProperty(value = "spring.datasource.type", havingValue = "com.zaxxer.hikari.HikariDataSource", matchIfMissing = true)
public class MybatisPlusConfiguration {

    @Bean
    public MetaObjectHandler customMetaObjectHandler() {
        return new CustomMetaObjectHandler();
    }

    /**
     * 分页
     *
     * @return 分页拦截器
     */
    @Bean
    public PaginationInnerInterceptor paginationInnerInterceptor() {
        return new PaginationInnerInterceptor();
    }

    /**
     * 乐观锁 配合注解 @Version
     *
     * @return 乐观锁
     */
    @Bean
    public OptimisticLockerInnerInterceptor optimisticLockerInnerInterceptor() {
        return new OptimisticLockerInnerInterceptor();
    }

    /**
     * 针对 update 和 delete 语句 作用: 阻止恶意的全表更新删除
     *
     * @return 拦截器
     */
    @Bean
    public BlockAttackInnerInterceptor blockAttackInnerInterceptor() {
        return new BlockAttackInnerInterceptor();
    }
}
