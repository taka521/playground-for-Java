module jigsaw.demo.app {

    // 公開するパッケージ
    exports jigsaw.demo.app.domain;

    // 当モジュールが依存するモジュール。
    // java.base はデフォルトのモジュールとなるため、明記しなくてもいい。
    requires java.base;

    // モジュール化されていないライブラリは Automatic Modules によって解決される。
    // ただし、jarの全てが公開モジュールとなるため、可能な限り避けたほうがいい。
    requires eclipse.collections;
    requires eclipse.collections.api;
    requires commons.lang;
}
