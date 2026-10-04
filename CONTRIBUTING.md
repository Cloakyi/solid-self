# Contributing to Solid Self

Thanks for helping out! Solid Self is intentionally tiny: it fixes one rendering problem and does nothing more. Please keep that in mind when suggesting features.

## Reporting bugs

1. Check the [existing issues](https://github.com/Cloakyi/solid-self/issues) and the [Troubleshooting](https://github.com/Cloakyi/solid-self/wiki/Troubleshooting) page first.
2. Open a [bug report](https://github.com/Cloakyi/solid-self/issues/new?template=bug_report.yml) and fill in every field.
3. Attach your `logs/latest.log` and, if possible, a screenshot or video.

Problems with another mod go into a [compatibility report](https://github.com/Cloakyi/solid-self/issues/new?template=compatibility_report.yml).

## Pull requests

1. Fork the repository and create a branch from `main`.
2. Build with `./gradlew build` (JDK 25) and test the jar in game, with and without shaders.
3. Keep changes focused. One fix or feature per pull request.
4. Add an entry to [CHANGELOG.md](CHANGELOG.md) under a new "Unreleased" heading.
5. Fill in the pull request template.

## Code style

- Follow the style of the existing code: tabs in Gradle files, four spaces in Java.
- Prefix mixin handler methods with `solidself$`.
- Keep comments short and explain *why*, not *what*.

## License

By contributing you agree that your contribution is licensed under the [MIT License](LICENSE).
