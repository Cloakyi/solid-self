# Changelog

All notable changes to Solid Self are documented here.
The format follows [Keep a Changelog](https://keepachangelog.com/en/1.1.0/) and the project uses [Semantic Versioning](https://semver.org/).

## [1.1.0] - 2026-10-04

First public release.

### Added
- Players are rendered with the cutout render type instead of translucent, so Iris shader effects like world outlines include them and they are no longer see-through
- Works for your own player and for other players, in singleplayer and on servers
- Toggle keybind under *Options → Controls → Key Binds → Solid Self* (unbound by default)
- The on/off state is saved in `config/solidself.properties`
- English and German translations

### Notes
- Requires Fabric API
- Semi-transparent skin pixels are shown either fully visible or fully invisible

[1.1.0]: https://github.com/Cloakyi/solid-self/releases/tag/v1.1.0
