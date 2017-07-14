module jigsaw.demo.app {

    // 公開するパッケージ
    exports jigsaw.demo.app.domain;

    // 当モジュールが依存するモジュール。
    // java.base はデフォルトのモジュールとなるため、明記しなくてもいい。
    requires java.base;

    // モジュール化されていないライブラリは Automatic Modules によって解決される。
    // その場合、モジュール名はjarファイル名から変換される。（バージョン情報は含まない）
    // Automatic Module によるモジュール化を行う場合、全てが公開パッケージとなるため極力避ける。
    requires eclipse.collections;     // eclipse-collections-8.1.0.jar
    requires eclipse.collections.api; // eclipse-collections-api-8.1.0.jar
    requires commons.lang3;           // commons-lang3-3.5.jar
}
