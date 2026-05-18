# Как перенести мод в обычный Minecraft

Есть два разных варианта: **запуск в IntelliJ** и **перенос готового `.jar` в Minecraft Launcher**.

## Вариант 1: запуск в IntelliJ для разработки

1. Распакуй архив проекта.
2. Открой папку проекта через `File -> Open`.
3. Проверь: `Settings -> Build Tools -> Gradle -> Gradle JVM = Java 17`.
4. Дождись импорта Gradle.
5. Запускай `Gradle -> Tasks -> fabric -> runClient`.

Это лучший режим, пока мы разрабатываем мод.

## Вариант 2: собрать jar и перенести в обычный Minecraft

1. В IntelliJ справа открой Gradle.
2. Запусти `Tasks -> build -> build`.
3. После сборки файл появится в:

```text
build/libs/
```

Нужен файл примерно такого вида:

```text
deadcity-nightfall-4.0.0-final-alpha.jar
```

4. Установи Fabric Loader для Minecraft 1.20.1.
5. Скачай Fabric API для 1.20.1.
6. Открой папку Minecraft:

```text
Win + R -> %appdata%\.minecraft
```

7. Перейди в папку:

```text
.minecraft/mods
```

8. Закинь туда:

```text
deadcity-nightfall-4.0.0-final-alpha.jar
fabric-api-0.92.9+1.20.1.jar
```

9. В Minecraft Launcher выбери профиль Fabric 1.20.1 и запускай игру.

## Если mods нет

Создай папку `mods` вручную.

## Если игра крашится

Проверь три вещи:

```text
Minecraft = 1.20.1
Fabric Loader = 0.16.10 или новее
Fabric API = 0.92.9+1.20.1
Java = 17
```
