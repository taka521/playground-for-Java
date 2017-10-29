# Try commons-text

Apache Commons Textを試しに使ってみるプロジェクト。


## 経緯

commons-langの RandomStringUtils が Deprecate されて、
代わりになりそうなライブラリを探したら、commons-textなるものを発見した。


## 使い方

軽く使ってみたが、ビルダーパターン.

```java
CharacterPredicate ALPHANUMERIC = v -> CharUtils.isAsciiAlphanumeric((char) v);
String result = new RandomStringGenerator.Builder()
    .withinRange('0', 'z')
    .filteredBy(ALPHANUMERIC)
    .build()
    .generate(20);
```