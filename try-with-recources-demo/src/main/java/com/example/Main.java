package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {

    /** 入力ファイル名 */
    private static final String INPUT_FILE = "input.txt";

    /** 出力ファイル名 */
    private static final String OUTPUT_FILE = "output.txt";

    /** 入力ファイルURL */
    private static final URL INPUT_FILE_URL = getURL(INPUT_FILE);

    /** 出力ファイルURL */
    private static final URL OUTPUT_FILE_URL = getURL(OUTPUT_FILE);

    /**
     * テキストファイルから１行づつ読み込んで、ファイルへ書き出す処理を
     * try-with-resources を使って実装してみたやつ。
     * 愚直にコード書いてしまった...。
     *
     * @param args
     */
    public static void main(String[] args) {

        // 準備
        Path inputFilePath = null;
        Path outputFilePath = null;
        try {
            // 事前にファイルを作成しておかないとヌルポで落ちるよ
            inputFilePath = Paths.get(INPUT_FILE_URL.toURI());
            outputFilePath = Paths.get(OUTPUT_FILE_URL.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        // BufferedReader で読み込み、BufferedWriterで書き出す。
        try (BufferedReader reader = Files.newBufferedReader(inputFilePath);
             BufferedWriter writer = Files.newBufferedWriter(outputFilePath)) {

            for (String line; (line = reader.readLine()) != null; ) {
                writer.append(line);
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Streamで読み込みを行いたい場合。
        // Streamは親クラスのBaseStreamがAutoCloseableを実装している。
        try (Stream<String> stream = Files.lines(inputFilePath)) {
            stream.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 指定されたファイル名のURLを取得します。<br/>
     *
     * @param fileName ファイル名
     *
     * @return 指定されたファイルのURL
     */
    private static URL getURL(final String fileName) {
        return ClassLoader.getSystemResource(fileName);
    }

}
