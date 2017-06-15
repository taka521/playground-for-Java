## SLF4J + Logbackでログ出力

Mavenでライブラリを管理。


### SLF4J (Simple Logging Facade For Java)

* ロギング実装を柔軟に切り替えることができるライブラリ。
* 実装（ロギングライブラリ）と利用者（プログラマ）の間に入る、アダプタの役割を担う。
* ロギング実装ではない。

コード上は、SLF4JのAPIだけ使用すればいい。<br/>
そのため、ロギングライブラリを変更してもコードに手を加える必要がなくなる。<br/>

どのロギングライブラリを使用するかは、
SLF4Jが、クラスパスに配置されたjarを自動的に関連付けしてログ出力を行ってくれる。<br/>

Q. ロギングライブラリがクラスパス上に複数ある場合はどうなる？<br/>
A. 実行時に警告が出てくるが、ログ出力はされる。

試しにLogbackとlog4jを配置してみた。

```text
SLF4J: Class path contains multiple SLF4J bindings.
SLF4J: Found binding in [jar:file:/Users/user/.m2/repository/ch/qos/logback/logback-classic/1.1.7/logback-classic-1.1.7.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: Found binding in [jar:file:/Users/user/.m2/repository/org/apache/logging/log4j/log4j-slf4j-impl/2.8.2/log4j-slf4j-impl-2.8.2.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
SLF4J: Actual binding is of type [ch.qos.logback.classic.util.ContextSelectorStaticBinder]
2017-06-15 21:01:46,741 [main] INFO  Main - Get Wild!!
2017-06-15 21:01:46,744 [main] WARN  Main - Get Wild!!
2017-06-15 21:01:46,744 [main] ERROR Main - Get Wild!!
```

ロギングライブラリが重複してた場合、先にクラスパスに追加したものが使用される。<br/>
※Mavenの場合、dependenciesタグ内の追加順。

### Logback

* ロギングライブラリ。
* Log4jの後継。
* Log4jよりも性能的にいいらしい。

### 準備

`pom.xml` に以下を追加。

```xml:pom.xml
<!-- SLF4J -->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>1.7.25</version>
</dependency>
  
<!-- Logback -->
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.1.7</version>
</dependency>
```

バージョンは適当。<br/>
とりあえず、DL数が多いやつ。

### 作るもの

* 設定ファイル（`logback.xml`)
* ログ出力のコード

### logback.xml

`logback.xml`では、Logbackでログ出力に関する設定を行う。<br/>
作成する場所は、クラスパス上。（基本的にresourcesかな？）

logback.xmlに関する記述は、実際のファイルを参照。


### ログ出力のコード

1. SLF4Jの `LoggerFactory.getLogger` メソッドを利用して、`Logger`インスタンスを取得する。
1. ログレベルに応じて、ログ出力メソッドを呼び出す。

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
  
public class Main {
  
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
  
    /** ログ出力するメッセージ */
    private static final String LOG_MESSAGE = "Get Wild!!";
  
    public static void main(String[] args){
        logger.debug(LOG_MESSAGE);
        logger.info(LOG_MESSAGE);
        logger.warn(LOG_MESSAGE);
        logger.error(LOG_MESSAGE);
        logger.trace(LOG_MESSAGE);
    }
}
```

* ログレベルによってメソッドが用意されている。

また、変数を出力する場合にはプレースホルダを利用する。

```java
logger.debug("message = {}", hoge);
```

出力したいログメッセージの中に `{}` を記述して、埋め込みたい変数を引数に渡していくだけ。<br/>
仮に

```java
logger.debug("message =" + hoge);
```

とした場合、`Logger#debug`メソッドを実行する前に、`hoge` の `toString` メソッドが呼ばれるため処理が重くなる。<br/>
ログの出力レベルがINFO以上の場合でも、ログ出力しないにも関わらず `toString` が呼ばれるため効率が悪い。<br/>
プレースホルダを利用することで、実行時（プレースホルダにバインドする時）にのみ `toString` が呼び出されるため、性能的にも良い。

### 参考

* [Java本格入門](http://gihyo.jp/book/2017/978-4-7741-8909-3)
* [SLF4Jとはなにか - 理系学生日記](http://kiririmode.hatenablog.jp/entry/20150526/1432625055)
* [Javaのロギングライブラリの歴史と現状をふんわり把握する（初学者向け）- Qiita](http://qiita.com/nisshiee/items/c5388f1d472ec86295e0)