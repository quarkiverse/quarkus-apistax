# Quarkus APIstax

<!-- ALL-CONTRIBUTORS-BADGE:START - Do not remove or modify this section -->
[![All Contributors](https://img.shields.io/badge/all_contributors-2-orange.svg?style=flat-square)](#contributors-)
<!-- ALL-CONTRIBUTORS-BADGE:END -->
[![Build](https://github.com/quarkiverse/quarkus-apistax/workflows/Build/badge.svg)](https://github.com/quarkiverse/quarkus-apistax/actions?query=workflow%3ABuild)
[![Maven Central](https://img.shields.io/maven-central/v/io.quarkiverse.apistax/quarkus-apistax-parent.svg?label=Maven%20Central)](https://search.maven.org/artifact/io.quarkiverse.apistax/quarkus-apistax-parent)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

Quarkus APIstax is a complete Java client implementation for the [APIstax](https://apistax.io) platform.

## Usage

Add the following dependency to your build file:

### Maven pom.xml

```xml

<dependency>
    <groupId>io.quarkiverse.apistax</groupId>
    <artifactId>quarkus-apistax</artifactId>
    <version>${latest.version}</version>
</dependency>
```

### Gradle build.gradle

```groovy
implementation("io.quarkiverse.apistax:quarkus-apistax:$latestVersion")
```

Get your APIstax API key [here](https://app.apistax.io/api-keys) and add it to your `application.properties` file:

```properties
quarkus.apistax.api-key=API-KEY
```

Inject an `APIstaxClient` and start using it.

```java

@ApplicationScoped
public class VatService {

    @Inject
    APIstaxClient client;

    public boolean isValid(String vatId) {
        VatVerificationResult result = client.verifyVatId(vatId);
        return result.getValid() == true;
    }
}
```

The further information and documentation about the APIs can be found
on [APIstax documentation](https://apistax.io/docs) page.

## Compatibility

Quarkus APIstax provides multiple different version streams.

| Quarkus     | Quarkus APIstax |
|-------------|-----------------|
| 3.2.x (LTS) | 1.x             |
| 3.8.x (LTS) | 2.x             |
| 3.13.x      | 3.x             |

## Contributors ✨

Thanks goes to these wonderful people ([emoji key](https://allcontributors.org/docs/en/emoji-key)):

<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
<!-- prettier-ignore-start -->
<!-- markdownlint-disable -->
<table>
  <tbody>
    <tr>
      <td align="center" valign="top" width="14.28%"><a href="http://instant-it.at"><img src="https://avatars.githubusercontent.com/u/1436448?v=4?s=100" width="100px;" alt="David Andlinger"/><br /><sub><b>David Andlinger</b></sub></a><br /><a href="https://github.com/quarkiverse/quarkus-apistax/commits?author=andlinger" title="Code">💻</a> <a href="#maintenance-andlinger" title="Maintenance">🚧</a></td>
      <td align="center" valign="top" width="14.28%"><a href="http://instant-it.at"><img src="https://avatars.githubusercontent.com/u/3810635?v=4?s=100" width="100px;" alt="Max Holzleitner"/><br /><sub><b>Max Holzleitner</b></sub></a><br /><a href="https://github.com/quarkiverse/quarkus-apistax/commits?author=holzleitner" title="Code">💻</a> <a href="#maintenance-holzleitner" title="Maintenance">🚧</a></td>
      <td align="center" valign="top" width="14.28%"><a href="http://gastaldi.wordpress.com"><img src="https://avatars.githubusercontent.com/u/54133?v=4?s=100" width="100px;" alt="George Gastaldi"/><br /><sub><b>George Gastaldi</b></sub></a><br /><a href="https://github.com/quarkiverse/quarkus-apistax/commits?author=gastaldi" title="Code">💻</a></td>
      <td align="center" valign="top" width="14.28%"><a href="https://lesincroyableslivres.fr/"><img src="https://avatars.githubusercontent.com/u/1279749?v=4?s=100" width="100px;" alt="Guillaume Smet"/><br /><sub><b>Guillaume Smet</b></sub></a><br /><a href="https://github.com/quarkiverse/quarkus-apistax/commits?author=gsmet" title="Code">💻</a></td>
    </tr>
  </tbody>
</table>

<!-- markdownlint-restore -->
<!-- prettier-ignore-end -->

<!-- ALL-CONTRIBUTORS-LIST:END -->

This project follows the [all-contributors](https://github.com/all-contributors/all-contributors) specification.
Contributions of any kind welcome!
