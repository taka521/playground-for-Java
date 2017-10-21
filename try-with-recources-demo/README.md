# try-with-resources 試してみる

なんだかんだ、try-with-resources 使ったことなかったなぁと思った。

## `try-with-resource` って何?

リソースの解放を自動的に行なってくれる便利な記法、仕組み。  
Java7から導入された。

今までは、リソースの解放忘れでメモリリークとか発生していた。  
(finally句でclose呼び忘れ)  
「オープンしたらクローズするの当たり前やし、クローズは自動的にやったるで」ってのが 
`try-with-resources`。

ここで言うリソースは、ファイルやDBのコネクション、Streamとか。

## 使い方

```java
try (Stream<String> stream = Files.lines(inputFilePath)) {
    stream.forEach(System.out::println);
} catch (IOException e) {
    e.printStackTrace();
}
```

try句の中に、`java.io.Closeable` 
または `java.lang.AutoCloseable` インターフェースを実装しているオブジェクトを指定する。
Java8までは、変数の宣言を括弧内で定義する必要があったが、Java9からは括弧の外で定義できる。

```java
Stream<String> stream = Files.lines(inputFilePath);
try(stream){
    stream.forEach(System.out::println);
} catch (IOException e) {
    e.printStackTrace();
}
```

その場合、closeする必要のある変数はfinal修飾されているか、実質finalでなければならない。


## 所感

try-with-resource、業務で使ったことないな...。  
テストコードで、直接DataSource使うことがあれば使いそう。（うらがみさん談）