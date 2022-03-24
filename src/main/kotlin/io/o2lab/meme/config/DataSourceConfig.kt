package io.o2lab.meme.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.stereotype.Component
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(basePackages = ["io.o2lab.meme.repository"])
@EnableTransactionManagement
class DataSourceConfig(
    val databaseInfo: DatabaseInfo
) {

    @Bean
    fun dataSource(): DataSource {
        val hikariConfig = HikariConfig()
        hikariConfig.jdbcUrl = databaseInfo.jdbcUrl
        hikariConfig.username = databaseInfo.userName
        hikariConfig.password = databaseInfo.password

        return HikariDataSource(hikariConfig)
    }

    @Bean
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
        val jpaVendorAdapter = HibernateJpaVendorAdapter()
        jpaVendorAdapter.setGenerateDdl(true)

        val factory = LocalContainerEntityManagerFactoryBean()
        factory.jpaVendorAdapter = jpaVendorAdapter
        factory.setPackagesToScan("io.o2lab.meme.domain")
        factory.dataSource = dataSource()
        return factory
    }

    @Bean
    fun transactionManager(entityManagerFactory: EntityManagerFactory): PlatformTransactionManager {
        val txManager = JpaTransactionManager()
        txManager.entityManagerFactory = entityManagerFactory
        return txManager
    }
}

@ConstructorBinding
@ConfigurationProperties(prefix = "database")
data class DatabaseInfo(
    val jdbcUrl: String,
    val userName: String,
    val password: String,
)
