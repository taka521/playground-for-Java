package arrays;


import org.junit.Assert;
import org.junit.Test;

public class ArrayTest {

    /**
     * 配列型変数の宣言には２種類ある。<br/>
     *
     * <code>
     *     int [] array; // 型と変数名の間に []
     *     int array []: // 変数名の後に []
     * </code>
     *
     * 基本的にはこの２種類だが、混合させることもできる。<br/>
     *
     * <code>
     *     int [] array[][]; // これはOK
     *     int [][] array[]; // これもOK
     * </code>
     */
    @Test
    public void test_配列型変数の宣言について(){

        // 普通の変数宣言
        int [] arrayA;
        int arrayB[];

        // ちょっと変わってるけど、コンパル通る宣言
        int [][] arrayC[];
        int [] arrayD[][];
    }

    @Test
    public void test_配列インスタンスの初期化(){

        // 普通の初期化
        int [] arrayA = new int[3];
        int [][] arrayB = new int[3][3];

        // 要素数が0の配列インスタンス
        int [] arrayC = new int[0];

        // 多次元配列の場合、1次元目の要素数は必ず指定する必要がある。
        int [][] arrayD = new int[3][];
        arrayD[0] = new int[3];
        arrayD[1] = new int[2];
        arrayD[2] = new int[0];

        // 以下はNG
        // 単純に、1次元目の要素数が決まっていないので、2次元目の配列インスタンスを格納できない。
        // int [][] arrayE = new int[][3];


    }

    /**
     * <p>
     * 配列インスタンスを作成する場合、最も手っ取り早いのは初期化演算子を用いること。<br/>
     * 初期化演算子とは <code>{}</code> のこと。<br/>
     * 初期化演算子は「変数宣言と同時にしか使用できない」というルールがある。<br/>
     */
    @Test
    public void test_初期化演算子を用いた配列インスタンスの初期化(){

        // 基本的な配列の初期化
        int [] arrayA = new int[3];  // new演算子を使用し、要素数が3の配列インスタンスを作成。
        int [] arrayB = new int[]{}; // new演算子を使用し、要素数が0の配列インスタンスを作成。
        int [] arrayC = {};          // 初期化演算子のみを使用し、要素数が0の配列インスタンスを作成。

        // コンパイルエラー
        // 初期化演算子は要素数を自動計算するため、明示的に要素数を指定してはいけない。
        // int [] arrayD = new int[2]{};
        // int [] arrayE = new int[2]{0, 1};

        // 以下3つは意味としては一緒（要素数0の２次元配列）
        int [][] arrayF = new int[][]{};
        int [][] arrayG = {{}};
        int [][] arrayH = {};

    }

    @Test
    public void test_配列のcloneメソッド(){

        // 配列インスタンスのcloneメソッドは、キャスト不要
        int [] arrayA = {0, 1, 2};
        int [] arrayB = arrayA.clone();

        Assert.assertFalse(arrayA == arrayB);

    }
}
