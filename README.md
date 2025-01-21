## DDUNIGMA-JAVA
[온라인 변환기](https://ddunigma.lsam.me)

자바 고수가 될거야!

## 설치
> [!NOTE]  
> 아직 Maven Central Repository에 라이브러리가 게시되지 않았습니다.

```kts
repositories {
    mavenCentral()
}

dependencies {
    implementation("me.lsam:ddunigma:1.0.0")
}
```

## 사용법

```java
import me.lsam.ddunigma.Ddunigma;

import java.nio.charset.StandardCharsets;

public class Main {
    public static public static void main(String[] args) {
        System.out.println(Ddunigma.encode("안녕하세요".getBytes())); // 인코드
        System.out.println(new String(Ddunigma.decode(".우땨땨이?땨뜌.이.뜌이?이!.우우땨이?우뜌.우땨뜌이이.뜌.우땨땨!이이야"), StandardCharsets.UTF_8)); // 디코드
    }
}
```

by [i3l3](https://github.com/i3l3) & [Icetang0123](https://github.com/gooddltmdqls)
