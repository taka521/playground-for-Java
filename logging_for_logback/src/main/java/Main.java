import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ログ出力するためのメインクラス
 */
public class Main {

    // SLF4F の LoggerFactory#getLogger を利用し、Loggerインスタンスを取得する。
    // SLF4J の Logger には、debug, info, warn, error, trace 等、ログレベルに応じたメソッドが用意されている。
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    /** ログ出力するメッセージ */
    private static final String LOG_MESSAGE = "Get Wild!!";

    public static void main(String[] args) {
        logger.debug(LOG_MESSAGE);
        logger.info(LOG_MESSAGE);
        logger.warn(LOG_MESSAGE);
        logger.error(LOG_MESSAGE);
        logger.trace(LOG_MESSAGE);
    }
}
