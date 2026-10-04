<div align="center">

# Solid Self

**Solid players with Iris shaders. No more see-through skins, and world outlines finally include you.**

[![Modrinth](https://img.shields.io/badge/Modrinth-Download-1bd96a?style=flat-square&logo=modrinth&logoColor=white)](https://modrinth.com/mod/solid-self)
[![Release](https://img.shields.io/github/v/release/Cloakyi/solid-self?style=flat-square&color=555&label=Release)](https://github.com/Cloakyi/solid-self/releases/latest)
[![Build](https://img.shields.io/github/actions/workflow/status/Cloakyi/solid-self/build.yml?branch=main&style=flat-square&label=Build)](https://github.com/Cloakyi/solid-self/actions/workflows/build.yml)
![Minecraft](https://img.shields.io/badge/Minecraft-26.2-555?style=flat-square)
![Fabric](https://img.shields.io/badge/Loader-Fabric-dbd0b4?style=flat-square)
[![License](https://img.shields.io/badge/License-MIT-555?style=flat-square)](LICENSE)

<img src="https://cdn.modrinth.com/data/cached_images/82379814b704589b09448fb9cecb33354e686f40.png" width="820" alt="Before and after: without Solid Self the player is see-through and has no outlines, with Solid Self it is solid and outlined">

[Download](https://modrinth.com/mod/solid-self) · [Wiki](https://github.com/Cloakyi/solid-self/wiki) · [Changelog](CHANGELOG.md) · [Report a bug](https://github.com/Cloakyi/solid-self/issues/new/choose)

</div>

<br>

## <img src="https://cdn.modrinth.com/data/cached_images/ab222e2e712612b189883a9529567dcafdc602da.png" width="24" alt="Pixel ladybug icon"> The problem

On Minecraft 26.x, players rendered with [Iris](https://modrinth.com/mod/iris) shaders turn partly see-through from some camera angles, especially against a bright sky. Shader effects that work on everything else, like world outlines, skip players completely.

It happens with different shader packs and isn't caused by your skin or resource packs. Upstream report: [Iris #3105](https://github.com/IrisShaders/Iris/issues/3105).

## <img src="https://cdn.modrinth.com/data/cached_images/501bc86a5da826baf1660f0e99878f8adc064e8b.png" width="24" alt="Pixel check mark icon"> What Solid Self does

| | |
|---|---|
| Solid players | Players are no longer see-through |
| Outlines | Shader effects like world outlines include players again |
| Everyone | Works for you and other players, in singleplayer and on servers |
| Toggle | Turn it on and off in game with a key of your choice |
| Lightweight | Client-side only, a single small mixin |

Invisibility, spectator mode, the first-person hand and all other entities stay vanilla.

> [!NOTE]
> Partly transparent pixels in skins are shown either fully visible or fully invisible. Most skins don't have any, and most shaders already show them wrong ([Iris #3135](https://github.com/IrisShaders/Iris/issues/3135)).

## <img src="https://cdn.modrinth.com/data/cached_images/9d0bf07626db022a6789547fbc2b9f52b9dfb055.png" width="24" alt="Pixel chest icon"> Installation

1. Install [Fabric Loader](https://fabricmc.net/use/) for Minecraft 26.2
2. Put [Fabric API](https://modrinth.com/mod/fabric-api) and Solid Self into your `mods` folder
3. Start the game

> [!TIP]
> Bind **Toggle Solid Self** under *Options › Controls › Key Binds › Solid Self* to switch between solid and vanilla players at any time.

## <img src="https://cdn.modrinth.com/data/cached_images/fa84eeb7ab6355f19b77751e779c50a4ed7087b9.png" width="24" alt="Pixel pickaxe icon"> How it works

Vanilla draws players with the translucent entity render type. Iris draws translucent geometry after the shader's deferred passes, so effects in those passes never see the player.

Solid Self swaps that render type for cutout, the one most mobs use, with a single mixin. Players are then drawn together with every other solid entity.

| File | Purpose |
|---|---|
| [`LivingEntityRendererMixin.java`](src/main/java/dev/cloakiy/solidself/mixin/LivingEntityRendererMixin.java) | The fix itself |
| [`SolidSelf.java`](src/main/java/dev/cloakiy/solidself/SolidSelf.java) | Toggle key and saved setting |

The [wiki](https://github.com/Cloakyi/solid-self/wiki/How-it-works) explains it in more detail.

## <img src="https://cdn.modrinth.com/data/cached_images/d982cde41463b81d29fdff97c71eb20de11fe38a.png" width="24" alt="Pixel lever icon"> Building

Requires JDK 25.

```bash
./gradlew build
```

The jar is written to `build/libs/`.

## <img src="https://cdn.modrinth.com/data/cached_images/28ef5fb89fc49aac86e4a6e941c05dae38d99c4f.png" width="24" alt="Pixel puzzle piece icon"> Contributing

Bug reports, compatibility reports and pull requests are welcome. Please read the [contributing guide](CONTRIBUTING.md) first.

[Bug report](https://github.com/Cloakyi/solid-self/issues/new?template=bug_report.yml) · [Compatibility report](https://github.com/Cloakyi/solid-self/issues/new?template=compatibility_report.yml) · [Feature request](https://github.com/Cloakyi/solid-self/issues/new?template=feature_request.yml)

<br>

---

<div align="center">

<sub>

[cloaki.de](https://cloaki.de) · [Modrinth](https://modrinth.com/user/Cloaki) · [Discord](https://discord.gg/suwEMTC9b7) · [support@cloaki.de](mailto:support@cloaki.de)

[MIT License](LICENSE) · [Impressum](https://cloaki.de/impressum/) · [Datenschutz](https://cloaki.de/datenschutz/) · [Haftungsausschluss](https://cloaki.de/haftung/)

Not an official Minecraft product. Not approved by or associated with Mojang or Microsoft.

</sub>

</div>
