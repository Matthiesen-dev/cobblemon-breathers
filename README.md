# Cobblemon Breathers

This mod adds a new set of Items to your Cobblemon world, ReBreathers. These items come in 3 tiers, each with a different effect. 

The first tier, the ReBreather Mk1 grants the player the Water Breathing Effect while underwater for 5 minutes (Configurable) without finding an air bubble. 
The second tier, the ReBreather Mk2 grants the player the Water Breathing and Night Vision Effects while underwater for 10 minutes (Configurable). without finding an air bubble.
The third tier, the ReBreather Mk3 grants the player the Conduit Power Effect while underwater for 20 minutes (Configurable) without finding an air bubble.

## Requirements
- [Cobblemon 1.7.3+1.21.1](https://modrinth.com/mod/cobblemon)

### Optional Dependencies
- [Accessories](https://modrinth.com/mod/accessories) - For equipping the ReBreathers in an accessory slot.

## Obtaining ReBreathers

The ReBreathers can be found in the Creative Inventory under the "Cobblemon Breathers" tab. They can also be obtained through the crafting recipes listed below. 
Each tier of ReBreather requires the previous tier to craft, so you will need to start with the Mk1 and work your way up to the Mk3.

ReBreather Mk1:
- 2x Copper Ingot
- 1x Glass Bottle
- 1x Water Stone
- 1x String

**Crafting Recipe:**
```
C - Copper Ingot
G - Glass Bottle
W - Water Stone
S - String

  S 
C W C
  G 
```

ReBreather Mk2:
- 2x Diamond
- 1x ReBreather Mk1
- 1x Mystic Water
- 1x Bucket

**Crafting Recipe:**
```
D - Diamond
R - ReBreather Mk1
M - Mystic Water
B - Bucket

  M
D R D
  B
```

ReBreather Mk3:
- 1x Conduit
- 1x ReBreather Mk2
- 1x Netherite Ingot

**Smithing Recipe:**
```
C - Conduit
R - ReBreather Mk2
N - Netherite Ingot

C R N
```

## Enchanting ReBreathers

Along with the ReBreathers, you can also enchant them with a new enchantment called "ReBreather Upgrade" which increases the duration of the effects granted by the ReBreathers.
The enchantment has 3 levels, each increasing the duration by the configured amount.

- **ReBreather Upgrade I:** Increases duration by 200 seconds (Configurable)
- **ReBreather Upgrade II:** Increases duration by 800 seconds (Configurable)
- **ReBreather Upgrade III:** Increases duration by 2000 seconds (Configurable)

## Configuration

This mod is highly configurable, allowing you to adjust many aspects of the ReBreathers and their Enchantments.

**Configuration File Location:** `<minecraft_directory>/config/cobblemon_breathers/config.json`

The default configuration for the ReBreathers is as follows:

```json
{
  "reBreatherItemConfig": {
    "airSupplyRecovery": 50,
    "effectsConfig": {
      "showAmbient": false,
      "visible": false
    },
    "maxAirConfig": {
      "mk1": 300,
      "mk2": 600,
      "mk3": 1200
    }
  },
  "breatherUpgradeEnchantConfig": {
    "disableEnchantmentEffect": false,
    "levelOneAirAddition": 200,
    "levelTwoAirAddition": 800,
    "levelThreeAirAddition": 2000
  }
}
```

### Configuration Options

- **`reBreatherItemConfig`:** This section contains the configuration options for the ReBreather items.
  - **`airSupplyRecovery`:** The amount of air supply recovered when the player finds an air bubble while using a ReBreather. (Default: 50 per second)
  - **`effectsConfig`:** This section contains the configuration options for the effects granted by the ReBreathers.
    - **`showAmbient`:** Whether to show the ambient particles for the effects. (Default: false)
    - **`visible`:** Whether to show the particles for the effects. (Default: false)
  - **`maxAirConfig`:** This section contains the configuration options for the maximum air supply provided by each tier of ReBreather.
    - **`mk1`:** The maximum air supply provided by the ReBreather Mk1. (Default: 300 seconds)
    - **`mk2`:** The maximum air supply provided by the ReBreather Mk2. (Default: 600 seconds)
    - **`mk3`:** The maximum air supply provided by the ReBreather Mk3. (Default: 1200 seconds)
- **`breatherUpgradeEnchantConfig`:** This section contains the configuration options for the ReBreather Upgrade enchantment.
  - **`disableEnchantmentEffect`:** Whether to disable the enchantment. (Default: false)
  - **`levelOneAirAddition`:** The amount of air supply added by the ReBreather Upgrade I enchantment. (Default: 200 seconds)
  - **`levelTwoAirAddition`:** The amount of air supply added by the ReBreather Upgrade II enchantment. (Default: 800 seconds)
  - **`levelThreeAirAddition`:** The amount of air supply added by the ReBreather Upgrade III enchantment. (Default: 2000 seconds)