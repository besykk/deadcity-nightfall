# Dead City: Nightfall v5.1 — realistic weapons + flat resources

Эта версия сделана как следующий шаг после v5.0: сначала приводим в порядок **ресурсы** и **оружие**.

## Главный принцип

- Ресурсы остаются **плоскими 2D иконками**, чтобы не перегружать инвентарь.
- Всё оружие сделано как **оригинальные low-poly 3D item-модели**.
- Модели вдохновлены реальными формами: пистолет, револьвер, SMG, дробовик, винтовка, мачете, топор, кувалда, нож и т.д.
- Это **не копии реальных брендов** и не ассеты из Dying Light. Это свои формы, свои полигоны и свои текстуры.

## Что уже сделано 3D

### Ближнее оружие
- `rusty_pipe`
- `police_baton`
- `nail_plank`
- `reinforced_machete`
- `fire_axe`
- `sledgehammer`
- `kitchen_knife`
- `hatchet`
- `military_knife`
- `long_machete`
- `improvised_cleaver`
- `rebar_hammer`
- `heavy_katana`
- `concrete_maul`
- `rail_spike_mace`
- `uv_blade`
- `rooftop_wrench`
- `tactical_tomahawk`
- `shock_machete`
- `flame_cleaver`
- `beast_claw_knife`
- `titan_crusher`
- `street_sign_halberd`
- `beast_bone_axe`
- `uv_greatsword`
- `throwing_knife`

### Огнестрельное оружие
- `makeshift_pistol`
- `police_pistol`
- `revolver`
- `smg`
- `compact_smg`
- `pump_shotgun`
- `double_barrel`
- `hunting_rifle`
- `assault_rifle`
- `military_rifle`
- `burst_rifle`
- `marksman_rifle`
- `auto_shotgun`

### Важные предметы, тоже 3D
- `uv_flashlight`
- `uv_flashlight_on`
- `medkit`
- `bandage`
- `stamina_booster`
- `lockpick`
- `noise_maker`
- `uv_flare`
- `uv_trap`
- `safe_zone_beacon`
- `grappling_hook`
- `parkour_harness`
- `glider_rig`
- `combat_vest`
- `beast_injector`
- `fuel_can`
- `buggy_controller`
- `buggy_deploy_kit`
- `buggy_repair_kit`
- `safehouse_kit`
- `loot_cache`
- `survivor_radio`
- `quest_board`

## Что оставлено плоским

- `scrap`
- `metal_parts`
- `duct_tape`
- `electronics`
- `infected_tissue`
- `cloth`
- `alcohol`
- `chemicals`
- `wiring`
- `blade_parts`
- `gunpowder_blend`
- `blueprint_fragment`
- `titan_bone`
- `mutagen_sample`
- `circuit_board`
- `buggy_parts`
- `engine_parts`
- `car_battery`
- `reinforced_tires`
- `turbocharger`
- `buggy_armor_plates`
- `suspension_kit`
- `nitro_bottle`
- `offroad_engine`
- `buggy_uv_core`
- `spiked_bumper`
- `uv_headlights`
- `heavy_rounds`
- `bolt_pack`
- `ammo_9mm`
- `shotgun_shells`
- `rifle_rounds`
- `magnum_rounds`
- `assault_rounds`

## Где лежат модели

Minecraft item JSON:

```text
src/main/resources/assets/deadcity/models/item/<название>.json
```

Текстуры:

```text
src/main/resources/assets/deadcity/textures/item/<название>.png
```

Blockbench-заготовки:

```text
blockbench/realistic_assets/<название>.bbmodel
```

Общий обзор всех оружий:

```text
blockbench/realistic_assets/ALL_WEAPONS_OVERVIEW.bbmodel
```

## Как редактировать

1. Открой Blockbench.
2. File → Open Model.
3. Выбери `.bbmodel` из `blockbench/realistic_assets/`.
4. Меняй кубы, форму, размер, текстуру.
5. Export → Export Block/Item Model.
6. Сохрани поверх файла в `src/main/resources/assets/deadcity/models/item/`.
7. Текстуру сохраняй в `src/main/resources/assets/deadcity/textures/item/`.

## Важно

Это всё ещё Minecraft low-poly стиль. Для прям AAA-качества нужны ручные правки в Blockbench: больше деталей, правильные UV-развёртки, отдельные текстуры металла/резины/дерева, анимации для мобов и машины.
