import container.Container;
import food.Food;
import food.Meat;
import food.Pork;

public class Main {

    private static Food food = new Food(); // 食べ物
    private static Meat meat = new Meat(); // 肉
    private static Pork pork = new Pork(); // 豚肉

    public static void main(String[] args) {
    }

    /*
        不変
            ジェネリクスはデフォルトで不変。
     */
    public static void invariant() {

        Container<Food> foodContainer = new Container<>();
        Container<Meat> meatContainer = new Container<>();
        Container<Pork> porkContainer = new Container<>();

        // 不変なので同一型にしか代入できない。（以下はコンパイルエラー）
        // foodContainer = meatContainer;
        // foodContainer = porkContainer;
        // meatContainer = porkContainer;


        // 値の設定（コンテナへの値を生成する）
        //   値の設定（生成）は行うことができる。
        //   もちろん、引数の型よりも上位の型は代入できない。（これは普通の仕様）

        foodContainer.setValue(food);
        foodContainer.setValue(meat);
        foodContainer.setValue(pork);

        // meatContainer.setValue(food);  // 肉 よりも上位の型である 食べ物 の代入はできない。
        meatContainer.setValue(meat);
        meatContainer.setValue(pork);

        // porkContainer.setValue(food);  // 豚肉 よりも 上位の型である 食べ物 の代入はできない
        // porkContainer.setValue(meat);  // 豚肉 よりも 上位の型である 肉 の代入はできない
        porkContainer.setValue(pork);


        // 値の取得（コンテナから値を消費する）
        //   実型パラメータに指定した型でしか受け取ることができない。

        Food foodToFood = foodContainer.getValue();
        // Meat foodToMeat = foodContainer.getValue();
        // Pork foodToPork = foodContainer.getValue();

        // Food meatToFood = meatContainer.getValue();
        Meat meatToMeat = meatContainer.getValue();
        // Pork meatToPork = meatContainer.getValue();

        // Food porkToFood = porkContainer.getValue();
        // Meat porkToMeat = porkContainer.getValue();
        Pork porkToPork = porkContainer.getValue();


    }

    /*
        共変
            パラメータを <? extends T> とすることで共変になる。
            上限境界ワイルドカード
     */
    public static void covariant() {

        Container<? extends Food> foodContainer = new Container<>();
        Container<? extends Meat> meatContainer = new Container<>();
        Container<? extends Pork> porkContainer = new Container<>();

        // 共変なので、下位から上位への代入は成功する。
        foodContainer = meatContainer;
        foodContainer = porkContainer;
        meatContainer = porkContainer;

        // ただ、ジェネリック型を共変にすると値を設定する（生成する）ことができなくなる。
        // 以下はコンパイルエラー

        /*
            foodContainer.setValue(food);
            foodContainer.setValue(meat);
            foodContainer.setValue(pork);

            meatContainer.setValue(food);
            meatContainer.setValue(meat);
            meatContainer.setValue(pork);

            porkContainer.setValue(food);
            porkContainer.setValue(meat);
            porkContainer.setValue(pork);
        */

        // 仮に値の設定を許すと、以下のようなコードを許してしまうことになるため。

        /*
            meatContainer = porkContainer;      // 豚肉コンテナ を 肉コンテナ として振る舞わせる
            meatContainer.setValue(meat);       // 肉コンテナ に 肉 を設定。（つまり 豚肉コンテナ に 肉 が入ってる）
            Pork p = porkContainer.getValue();  // 豚肉コンテナ から 豚肉 を取得...でも、豚肉コンテナ には 肉 が入ってる...
        */

        // 上記のコードは実行時に ClassCastException が発生する。（実際にはコンパイルエラーなんだけど）
        // ジェネリックの目的は、型に関する実行時エラーを無くす（明示的キャストをさせない）こと。


        // 値を取得する（消費する）ことは許されている。
        //   <? extends T> は「最低でも T の型」であることを保証しているので、
        //   T もしくは、Tよりも上位の型で受け取ることができる。

        Food foodToFood = foodContainer.getValue();
        // Meat foodToMeat = foodContainer.getValue();
        // Pork foodToPork = foodContainer.getValue();

        Food meatToFood = meatContainer.getValue();
        Meat meatToMeat = meatContainer.getValue();
        // Pork meatToPork = meatContainer.getValue();

        Food porkToFood = porkContainer.getValue();
        Meat porkToMeat = porkContainer.getValue();
        Pork porkToPork = porkContainer.getValue();

    }

    /*
        反変
            パラメータを <? super T> とすることで反変になる。
            下限境界ワイルドカード
     */
    public static void contravariant() {

        Container<? super Food> foodContainer = new Container<>();
        Container<? super Meat> meatContainer = new Container<>();
        Container<? super Pork> porkContainer = new Container<>();

        // 反変なので、上位を下位へ代入することができる
        porkContainer = foodContainer;
        porkContainer = meatContainer;
        meatContainer = foodContainer;

        // 反変にすると、値の設定は可能。
        //   <? super T> は、「T、または T よりも上位の型」であることを保証している。
        //  なので、T または Tよりも下位の型を受け取ることができる。

        foodContainer.setValue(food);
        foodContainer.setValue(meat);
        foodContainer.setValue(pork);

        //meatContainer.setValue(food);
        meatContainer.setValue(meat);
        meatContainer.setValue(pork);

        //porkContainer.setValue(food);
        //porkContainer.setValue(meat);
        porkContainer.setValue(pork);


        // ただし、値の取得は不可能。
        //   <? super T> は、あくまで 「T、または T の親」であることを保証しているだけ。
        //   Container#getValue() が Food の可能性もあるし、Object の可能性もある。
        //   Food からアッパーキャストされた Object であれば、ダウンキャストに成功するが何の保証もない。
        // よって、以下のコードは全てコンパイルエラー

        /*
        Food foodToFood = foodContainer.getValue();
        Meat foodToMeat = foodContainer.getValue();
        Pork foodToPork = foodContainer.getValue();

        Food meatToFood = meatContainer.getValue();
        Meat meatToMeat = meatContainer.getValue();
        Pork meatToPork = meatContainer.getValue();

        Food porkToFood = porkContainer.getValue();
        Meat porkToMeat = porkContainer.getValue();
        Pork porkToPork = porkContainer.getValue();
        */
    }

}
