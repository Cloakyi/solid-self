# Solid Self

**Solid Self fixes players rendering see-through and being ignored by shader effects when using [Iris](https://modrinth.com/mod/iris) shaders.**
*(and does nothing more!)*

[![Download on Modrinth](https://img.shields.io/badge/Download-Modrinth-1bd96a?style=for-the-badge&logo=modrinth&logoColor=white)](https://modrinth.com/mod/solid-self)
[![GitHub release](https://img.shields.io/github/v/release/Cloakyi/solid-self?style=for-the-badge&color=4a4a4a&label=Release)](https://github.com/Cloakyi/solid-self/releases/latest)
[![Build](https://img.shields.io/github/actions/workflow/status/Cloakyi/solid-self/build.yml?branch=main&style=for-the-badge&label=Build)](https://github.com/Cloakyi/solid-self/actions/workflows/build.yml)
![Loader: Fabric](https://img.shields.io/badge/Loader-Fabric-dbd0b4?style=for-the-badge)
![Minecraft 26.2](https://img.shields.io/badge/Minecraft-26.2-4a4a4a?style=for-the-badge)
[![License: MIT](https://img.shields.io/badge/License-MIT-4a4a4a?style=for-the-badge)](LICENSE)

![Before / after comparison: without Solid Self the player is see-through and has no outlines, with Solid Self it is solid and outlined](https://cdn.modrinth.com/data/cached_images/82379814b704589b09448fb9cecb33354e686f40.png)

## <img src="https://cdn.modrinth.com/data/cached_images/ab222e2e712612b189883a9529567dcafdc602da.png" width="28" alt="Pixel ladybug icon"> The bug

Since Minecraft 26.x, players rendered with Iris shaders can behave strangely:

- **Players turn semi-transparent** from certain camera angles, most noticeable in third person when looking towards the sky or a bright light source
- **Shader effects skip players entirely**, for example world outlines, which outline every block, mob and cosmetic, but not the player standing right in front of you
- **It doesn't matter which shader**: the issue has been reported across different shader packs

Upstream report: [Iris issue #3105](https://github.com/IrisShaders/Iris/issues/3105).

## <img src="https://cdn.modrinth.com/data/cached_images/501bc86a5da826baf1660f0e99878f8adc064e8b.png" width="28" alt="Pixel check mark icon"> The fix

- Players are **no longer see-through**
- Shader effects like **world outlines** apply to players again
- Works for **your own player and other players**, in singleplayer and on servers
- **Toggle it in-game** with a key of your choice, no restart needed
- Client-side only, no performance cost

Not touched: invisibility and spectator rendering, the first-person hand and every other entity.

## <img src="https://cdn.modrinth.com/data/cached_images/fa84eeb7ab6355f19b77751e779c50a4ed7087b9.png" width="28" alt="Pixel pickaxe icon"> How it works

Vanilla Minecraft renders players with the **translucent** entity render type. With Iris, translucent entities are drawn *after* the shader's deferred passes, so every effect that reads the depth buffer there (such as world outlines) can't see the player, and the player can get blended with whatever is behind it.

Solid Self swaps that render type for **cutout**, which most mobs already use. The whole fix is a single mixin into `LivingEntityRenderer#getRenderType`:

| File | Purpose |
|---|---|
| [`LivingEntityRendererMixin.java`](src/main/java/dev/cloakiy/solidself/mixin/LivingEntityRendererMixin.java) | Replaces the player's translucent render type with cutout |
| [`SolidSelf.java`](src/main/java/dev/cloakiy/solidself/SolidSelf.java) | Toggle keybind and the saved on/off state |

More details in the [wiki](https://github.com/Cloakyi/solid-self/wiki/How-it-works).

## <img src="https://cdn.modrinth.com/data/cached_images/4cc6c28d1657cb99c573ec06f8283fac8a028f5c.png" width="28" alt="Pixel warning sign icon"> Known limitation

Semi-transparent pixels in skins are rendered either fully visible or fully invisible. Fully transparent pixels keep working. Semi-transparent skin pixels are already broken with most shaders anyway ([Iris issue #3135](https://github.com/IrisShaders/Iris/issues/3135)).

## <img src="https://cdn.modrinth.com/data/cached_images/9d0bf07626db022a6789547fbc2b9f52b9dfb055.png" width="28" alt="Pixel chest icon"> Installation

1. Install [Fabric Loader](https://fabricmc.net/use/)
2. Put [Fabric API](https://modrinth.com/mod/fabric-api) and Solid Self into your `mods` folder
3. Start the game

Get Solid Self on **[Modrinth](https://modrinth.com/mod/solid-self)** or from the [GitHub releases](https://github.com/Cloakyi/solid-self/releases).

To toggle it in-game, bind **Toggle Solid Self** under *Options → Controls → Key Binds → Solid Self*.

## <img src="https://cdn.modrinth.com/data/cached_images/d982cde41463b81d29fdff97c71eb20de11fe38a.png" width="28" alt="Pixel lever icon"> Building from source

Requires JDK 25.

```bash
./gradlew build
```

The mod jar ends up in `build/libs/`. See the [wiki](https://github.com/Cloakyi/solid-self/wiki/Building-from-source) for details.

## <img src="https://cdn.modrinth.com/data/cached_images/53844cb821e41d11ea76599fe8387e815ba885ee.png" width="28" alt="Pixel book icon"> Documentation

The [wiki](https://github.com/Cloakyi/solid-self/wiki) covers installation, usage, compatibility, troubleshooting and the technical background.

## <img src="https://cdn.modrinth.com/data/cached_images/28ef5fb89fc49aac86e4a6e941c05dae38d99c4f.png" width="28" alt="Pixel puzzle piece icon"> Contributing

Bug reports, compatibility reports and pull requests are welcome. Please read [CONTRIBUTING.md](CONTRIBUTING.md) first.

- [Report a bug](https://github.com/Cloakyi/solid-self/issues/new?template=bug_report.yml)
- [Report an incompatible mod](https://github.com/Cloakyi/solid-self/issues/new?template=compatibility_report.yml)
- [Suggest a feature](https://github.com/Cloakyi/solid-self/issues/new?template=feature_request.yml)

## <img src="https://cdn.modrinth.com/data/cached_images/66525047fd38fecb8029cc526e4ce1309425abf4.png" width="28" alt="Pixel ender pearl icon"> Links

- **Website:** [cloaki.de](https://cloaki.de)
- **Modrinth:** [Cloaki](https://modrinth.com/user/Cloaki)
- **Support:** [support@cloaki.de](mailto:support@cloaki.de) · [Discord server](https://discord.gg/suwEMTC9b7)

## License

[MIT](LICENSE) © Cloakiy

*Legal (German): [Impressum](https://cloaki.de/impressum/) · [Datenschutzerklärung](https://cloaki.de/datenschutz/) · [Haftungsausschluss](https://cloaki.de/haftung/)*

*NOT AN OFFICIAL MINECRAFT PRODUCT. NOT APPROVED BY OR ASSOCIATED WITH MOJANG OR MICROSOFT.*
