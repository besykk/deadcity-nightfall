# Blockbench: как делать 3D-модельки и наносить текстуры

## 1. Что ставим

Скачай и установи **Blockbench**. Для наших предметов чаще всего нужен формат:

```text
File -> New -> Java Block/Item
```

Для мобов можно использовать:

```text
File -> New -> Modded Entity
```

## 2. Как сделать модель предмета

Пример: UV Flashlight или Grappling Hook.

1. `File -> New -> Java Block/Item`.
2. Название: `uv_flashlight` или `grappling_hook`.
3. Texture size лучше начать с `16x16` или `32x32`.
4. Добавляешь кубы через `Add Cube`.
5. Делаешь форму из кубов:
   - ручка,
   - корпус,
   - лампа/лезвие/ствол,
   - мелкие детали.
6. Справа во вкладке `Outliner` переименуй детали, чтобы не запутаться.

## 3. Как нанести текстуру

1. Открой вкладку `Paint`.
2. Создай текстуру: `Create Texture`.
3. Выбери размер, например `32x32`.
4. Включи режим рисования.
5. Кликаешь по сторонам кубов и красишь.
6. Используй несколько оттенков:
   - тёмный основной цвет,
   - светлая грань сверху,
   - тень снизу,
   - яркий UV-акцент голубым/фиолетовым.

## 4. Как экспортировать предмет

Для item-модели:

```text
File -> Export -> Export Block/Item Model
```

Сохраняешь `.json` сюда:

```text
src/main/resources/assets/deadcity/models/item/item_name.json
```

Текстуру `.png` кладёшь сюда:

```text
src/main/resources/assets/deadcity/textures/item/item_name.png
```

Внутри json путь к текстуре должен быть примерно такой:

```json
"textures": {
  "0": "deadcity:item/item_name",
  "particle": "deadcity:item/item_name"
}
```

## 5. Как проверить модель

1. Сохрани json и png.
2. Перезапусти `runClient`.
3. Возьми предмет в креативе.
4. Проверь:
   - как выглядит в руке,
   - как выглядит в инвентаре,
   - не слишком ли большой,
   - не повернут ли боком.

Настройки положения находятся в секции `display` в json.

## 6. Что делать 3D, а что плоским

3D:

```text
UV Flashlight
Grappling Hook
оружие ближнего боя
огнестрел
аптечки
UV Trap
Safe Zone Beacon
Buggy предметы
```

Плоское:

```text
scrap
metal parts
cloth
chemicals
wiring
infected tissue
ammo
blueprint fragments
```

Так мод выглядит красиво, но не превращается в ад по количеству моделей.
