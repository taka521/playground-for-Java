package loopExpression;

import org.junit.Test;

public class LoopExpression {

    @Test
    public void test_ループ構文() {

        // 基本的なfor文
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
        }

        /*
            for文の基本的な構文は以下の通り。

            for(初期化文; 条件文; 更新文){
                // 処理
            }

            初期化文：一時変数を宣言可能。複数の変数を「,」区切りで定義可能だが、型は同じでなければならない。
            条件文　：ブロック内の処理を実行するか否かを判定する条件文を記載する。なお、複数定義は不可能。
            更新文　：ブロック内の処理を実行後に実施される処理。「,」区切りで複数定義可能。

            for(int i = 0, y = 0; i < 2; i++, y++){
                // 処理
            }

            for(int i = 0, long j = 0; ;) // 異なる型を初期化することはできない。
            for(; true, false; )          // 条件文は複数定義不可能。
         */

    }

    @Test
    public void test_ラベル() {

        int a;

        labelA:
        a = 100;

        labelB:
        for (int i = 0; i < a; i++) {
            labelC:
            for (int j = 0; j < i; j++) {

                if (j % 2 == 0) break labelB;
                if (j < 5) continue labelC;

                labelD:
                return;
            }
        }

        /*
            Javaではラベルを付けることが可能。

            * コードブロック
            * すべてのループ文と分岐
            * 式
            * 代入
            * return文
            * tryブロック
            * throw句

            基本的に使わないけど...。
         */
    }
}
