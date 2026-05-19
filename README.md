# Dead City: Nightfall v5.1.1 Fixed

Исправленная сборка после ошибок компиляции из v5.0/v5.1.

Фиксы:
- убраны лишние `.value()` у `SoundEvents`, где в Yarn 1.20.1 уже возвращается `SoundEvent`;
- оставлен `.value()` у `SoundEvents.UI_BUTTON_CLICK`, потому что он в этой версии маппингов требует `SoundEvent` из `Reference<SoundEvent>`;
- исправлен `MutantSpitterEntity`: `getTarget()` уже возвращает `LivingEntity`, поэтому pattern matching `instanceof LivingEntity target` заменён на обычную проверку `target != null`;
- сохранены realistic low-poly модели оружия и плоские ресурсы из v5.1.


# Dead City: Nightfall v5.1 Realistic Weapons & Resources

Minecraft: **1.20.1**  
Fabric Loader: **0.16.10+**  
Fabric API: **0.92.9+1.20.1**  
Java: **17**

Большая release-сборка проекта **Dead City: Nightfall**. Это оригинальный Minecraft Fabric-мод, вдохновлённый идеями zombie parkour survival, ночных погонь, UV-защиты, оружия, выживания и машины. В архив не добавлены чужие модели/текстуры/ассеты из Dying Light, Dying Light 2 или The Beast — все ассеты здесь оригинальные заготовки, чтобы проект можно было развивать без копирования чужого контента.

## Что есть в v5.0

- UV Flashlight с батарейкой, HUD и ON/OFF моделью.
- UV Flare, UV Trap, UV Safe Zone Beacon.
- Крюк-кошка.
- Parkour Harness: скорость, прыжок, смягчение падения.
- Glider Rig: планирование при падении с Shift.
- Beast Injector: временный режим усиления игрока.
- HUD в стиле survival-action: UV, патроны, прочность оружия, слот оружия, ночная опасность, Beast Instinct bar.
- Много ближнего оружия: одноручное, двуручное, электрическое, огненное, UV, bleed.
- Огнестрел: пистолеты, револьвер, SMG, compact SMG, дробовики, auto shotgun, винтовки, assault/burst/marksman rifles.
- Патроны и перезарядка: ПКМ стрелять, Shift+ПКМ перезарядить.
- Scout Buggy: deploy kit, rideable buggy entity, топливо, ремонт, UV headlights effect, апгрейды.
- NPC/мобы с отдельными текстурами:
  - Infected Runner
  - Infected Biter
  - Night Volatile
  - Screamer
  - Mutant Spitter
  - Demolisher Titan
  - Alpha Volatile
  - Raider Soldier
  - Raider Brute
  - Civilian
  - Trader Survivor
- Ночной спавн и усиление заражённых.
- Лут-таблицы и рецепты для ключевых новых вещей.
- 3D item-модели почти для всех важных предметов; мелкие ресурсы оставлены плоскими.
- Blockbench-заготовки для багги и оружия.

## v5.1 asset update

Добавлен полный первый проход по ассетам: плоские ресурсы + оригинальные low-poly 3D модели оружия, похожие на реальные формы, но без копирования брендов и чужих ассетов. См. `docs/REALISTIC_ASSETS_V5_1_RU.md`.


## v5.2 Night Beast Fix
- Исправлен звук Noise Maker (`BLOCK_NOTE_BLOCK_BELL.value()`).
- Полоса Beast Instinct теперь копится за убийства заражённых и добивания.
- Добавлены специальные добивания: низкое здоровье врага + спринт/шифт/удар сверху, либо готовый Beast Instinct.
- Ночные прыгуны усилены по HP.
- Добавлены варианты ночных прыгунов по секторам карты: Volatile Stalker и Armored Volatile.
- Ночной спавн теперь зависит от удалённости сектора города от центра.
- Перерисованы текстуры мобов и улучшены 3D item-текстуры, чтобы предметы не выглядели чёрными прямоугольниками.
