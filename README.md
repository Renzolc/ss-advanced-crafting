# SS Advanced Crafting

NeoForge 1.21.1 addon for [Sophisticated Storage](https://www.curseforge.com/minecraft/mc-mods/sophisticated-storage) that adds an **Advanced Crafting Upgrade** with the **vanilla green recipe book** and dual-source ingredient pull (storage inventory + player inventory).

Sister mod of [SB Advanced Crafting](https://github.com/Renzolc/sb-advanced-crafting) for Sophisticated Backpacks.

## Features

- New upgrade item: **Advanced Crafting Upgrade** (separate from stock `crafting_upgrade`)
- Opens a crafting tab with the **vanilla green `RecipeBookComponent`** (tabs, search, craftable filter, ghost recipe)
- Recipe placement pulls ingredients from **storage inventory and player inventory** (same dual-source path JEI/EMI use)
- **Shift-click** a recipe for max transfer
- Green recipe-book toggle button (vanilla sprites) shows/hides the book panel
- Recipe book stays on-screen: prefers the side of the craft grid with more free space, clamps X/Y with a small margin, and re-anchors on resize
- Conflicts with the stock Crafting Upgrade (only one crafting upgrade per storage)
- Tagged with `sophisticatedstorage:upgrade` so it can be inserted into storage upgrade slots

## Crafting recipe

```
D T D
G C G
D E D
```

| Key | Item |
|-----|------|
| C | `sophisticatedstorage:crafting_upgrade` |
| D | Diamond |
| G | Gold Ingot |
| E | Eye of Ender |
| T | Crafting Table |

More expensive than the stock crafting upgrade (which is iron + crafting table + chest + upgrade base).

## Dependencies

| Mod | Version (tested) |
|-----|------------------|
| Minecraft | 1.21.1 |
| NeoForge | 21.1.251 |
| Sophisticated Core | 1.21.1-1.5.1.2341 |
| Sophisticated Storage | 1.21.1-1.5.91.2127 |

## Build

```bash
./gradlew build
```

Jar: `build/libs/ss_advanced_crafting-1.0.0.jar`

## How the green recipe book works

1. Open the Advanced Crafting upgrade tab on a Sophisticated Storage chest/barrel/etc.
2. The vanilla green recipe book panel floats beside the 3×3 grid (preferring the side with more free space, clamped on-screen).
3. Click the green book button to show/hide the panel (tab stays compact either way).
4. Click a recipe to place it; **Shift-click** places as many crafts as possible (`maxTransfer`).
5. If ingredients are missing, the usual ghost outline appears on the grid.

### Dual-source craftability & placement

- **Craftability** (“can craft” highlighting / craftable filter) counts items in:
  - player inventory, and
  - storage inventory slots (excluding the craft grid and result slot).
- **Placement** sends `ss_advanced_crafting:place_crafting_recipe` to the server, which expands the recipe into a 3×3 template (shaped recipes are **centered** like vanilla) and calls Sophisticated Core’s `CraftingContainerRecipeTransferHandlerServer.setItemsWithStacks` with inventory slot indexes from storage + player (craft grid + result excluded).
- **JEI / EMI** transfer into this container still uses the same dual-source `ICraftingContainer` path.

## Limitations

- The recipe book is embedded beside the upgrade tab (not a full `RecipeBookMenu` screen). Keyboard focus for search is forwarded via screen events while the tab is open.
- Multi-result picker from the stock crafting tab is not duplicated here (`setRecipeUsed` still applies after transfer).
- Ghost recipes for uncraftable clicks are client-side; successful placement clears them when items move.

## License

MIT — Renzo

Sophisticated Core / Storage remain All Rights Reserved; this addon depends on them and extends public APIs without vendoring their sources.
