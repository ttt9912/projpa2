package ch6.p3_entity_manager_operations.container_managed.transaction_scoped.config;

import ch6.config.JpaConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(JpaConfig.class)
@ComponentScan(basePackages = {"ch6.p3_entity_manager_operations.container_managed.transaction_scoped"})
public class P3TxScopedConfig {
}
