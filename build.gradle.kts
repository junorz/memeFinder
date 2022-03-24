import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.4"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("io.gitlab.arturbosch.detekt") version "1.19.0"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
    kotlin("plugin.jpa") version "1.6.10"

    // 注意这个插件要放到最后，否则会报以下错误
    // Extension with name 'kotlin' does not exist. Currently registered extension names: [ext, springBoot, dependencyManagement, kapt]
    // 此插件是Kotlin的Annotation Processor.
    id("org.jetbrains.kotlin.kapt") version "1.6.20-RC2"

    // kapt将来有可能会被ksp替代，不过现在ksp还不支持spring的configuration processor插件
    // https://github.com/spring-projects/spring-boot/issues/28046
    // id("com.google.devtools.ksp") version "1.6.10-1.0.4"
}

group = "io.o2lab"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.zaxxer:HikariCP:5.0.1")
    // 官方文档使用的是annotationProcessor，在Kotlin下应该替换成kapt，就是上面plugins所导入的kapt插件
    kapt("org.springframework.boot:spring-boot-configuration-processor")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// 使用kapt生成的metadata目录并不是在build/classes下面，因此compileJava任务需要配置成依赖processResources的形式
// 这样做可以让它获取到额外的（非标准目录下生成的）metadata文件
// 详见以下：
// https://docs.spring.io/spring-boot/docs/current/reference/html/configuration-metadata.html#configuration-metadata.annotation-processor.configuring
// https://youtrack.jetbrains.com/issue/KT-15040
tasks.compileJava {
    inputs.files(tasks.processResources)
}

detekt {
    toolVersion = "1.19.0"
    config = files("config/detekt/detekt.yml")
    buildUponDefaultConfig = true
}

tasks.withType<Detekt>().configureEach {
    jvmTarget = "17"
}



