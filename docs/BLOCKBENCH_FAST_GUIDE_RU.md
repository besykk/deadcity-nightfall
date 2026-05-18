# Как делать модели в Blockbench и ставить текстуры

## 1. Новый предмет
1. Открой Blockbench.
2. `File -> New -> Java Block/Item`.
3. Выбери размер текстуры 32x32 или 64x64.
4. Делай модель из кубов.
5. Для оружия держи модель примерно по диагонали, чтобы в руке она смотрелась красиво.

## 2. Текстура
1. Вкладка Paint.
2. Создай текстуру.
3. Раскрась кубы: основной цвет, тени, блики, детали.
4. Сохрани PNG.

## 3. Экспорт
1. `File -> Export -> Export Java Block/Item Model`.
2. JSON клади сюда:
   `src/main/resources/assets/deadcity/models/item/item_name.json`
3. PNG клади сюда:
   `src/main/resources/assets/deadcity/textures/item/item_name.png`

## 4. Имена
Если предмет называется `uv_flashlight`, то файлы должны быть:

```text
models/item/uv_flashlight.json
textures/item/uv_flashlight.png
```

## 5. Ресурсы
Мелкие ресурсы лучше оставлять плоскими:

```text
scrap, metal_parts, cloth, wiring, infected_tissue, chemicals
```

Для них модель:

```json
{
  "parent": "minecraft:item/generated",
  "textures": {
    "layer0": "deadcity:item/scrap"
  }
}
```
