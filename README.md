Settings
--------------------------------------------------------------------------------

모듈별 yaml파일에서 커스텀 프로퍼티를 사용하기 위한 설정도 포함한다.
ymal파일에 커스텀 프로퍼티만 설정해도 코드상에서 사용에는 문제가 없지만 많은 혜택은 없을 것이다.

### 프로파일링 전략

#### resources/application-core.yml
공통 설정파일 추가

#### resources/application.yml
```yaml
server:
  port: 8080
  error:
    whitelabel:
      enabled: false

spring:
  config:
    import:
      - optional:classpath:/application-core.yml
      - optional:classpath:/application-domain.yml

---

spring:
  config:
    activate:
      on-profile: development
```

### 커스텀 설정을 위한

#### io.opentest.core.Settings
아래에서 생성할 metadata를 위한 클래스

#### resources/META-INF/spring-configuration-metadata.json
yml파일에서 커스텀프로퍼티의 문법지원을 위해서는 필요하다

#### 어플리케이션 모듈에서 Settings클래스를 상속한다
@Getter, @Setter를 모두 설정해야 `build/classes/java/main/META-INF/spring-configuration-metadata.json`
```java
package io.opentest.front;

import io.opentest.core.Settings;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "myapp")
public class FrontSettings extends Settings {
}
```
에디터 상단에 아래 에레가 보일것이다.
> Spring Boot Configuration Annotation Processor not configured

**buildSrc/src/main/kotlin/application-conventions.gradle.kts**에 아래 설정을 추가하고 Gradle을 reload한다.
```kotlin
dependencies {
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
}
```


#### FrontApp
@EnableConfigurationProperties설정이 추가되어야 한다.
```java
@EnableConfigurationProperties(FrontSettings.class)
public class FrontApp {
}
```
