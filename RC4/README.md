# RC4
[RC4](https://ru.wikipedia.org/wiki/RC4) (от англ. Rivest cipher 4 или Ron’s code), также известен как ARC4 или ARCFOUR


# Статический метод `start()` класса `RC4` принимает два аргумента
* Первый аргумент - это `ключ`, по которому будет производиться шифрование и дешифрование
* Второй аргумент - это `текст`, который будет зашифрован или деширован

```java
public class Main {
    public static void main(String args[]) {
        String s = RC4.start("KEY", "CONTENT");
        System.out.println(s);
    }
}
```

