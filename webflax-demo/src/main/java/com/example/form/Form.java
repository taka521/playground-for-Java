package com.example.form;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class Form {

    /** メッセージ */
    private final String message;

    /** 当日の日付 */
    private final LocalDate today;

    /**
     * コンストラクタ
     *
     * @param message メッセージ
     * @param today   当日日付
     */
    public Form(@NotNull String message, @NotNull LocalDate today) {
        this.message = message;
        this.today = today;
    }

    /**
     * メッセージを取得します。
     *
     * @return メッセージ
     */
    public String getMessage() {
        return message;
    }

    /**
     * 当日日付を取得します。
     *
     * @return 当日日付
     */
    public LocalDate getToday() {
        return today;
    }
}
