package com.taka521.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.io.Serializable;

import static org.assertj.core.api.Assertions.assertThat;

public class TypeReferenceTest {

    static final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void case1() throws Exception {

        {
            final String json = mapper.writeValueAsString(new Todo());
            final Todo todo = this.jsonToTodo(json);
            assertThat(todo).hasFieldOrPropertyWithValue("title", "タイトル").hasFieldOrPropertyWithValue("memo", "メモ");
        }

        {
            final String json = mapper.writeValueAsString(new Book());
            final Book book = this.jsonToBook(json);
            assertThat(book).hasFieldOrPropertyWithValue("title", "はじめてのJava")
                    .hasFieldOrPropertyWithValue("price", 1000);
        }

        {
            final String json = mapper.writeValueAsString(new Todo());
            final Todo todo = this.jsonToObject(json, Todo.class);
            assertThat(todo).hasFieldOrPropertyWithValue("title", "タイトル").hasFieldOrPropertyWithValue("memo", "メモ");
        }

        {
            final String json = mapper.writeValueAsString(new Book());
            final Book todo = this.jsonToObject(json);
            assertThat(todo).hasFieldOrPropertyWithValue("title", "タイトル").hasFieldOrPropertyWithValue("memo", "メモ");
        }

    }

    // TypeReference に対して、静的に型を指定する
    Todo jsonToTodo(String json) throws IOException {
        return mapper.readValue(json, new TypeReference<Todo>() {});
    }

    Book jsonToBook(String json) throws IOException {
        return mapper.readValue(json, new TypeReference<Book>() {});
    }

    // ダメな例（TypeReferenceは静的に型情報を与える必要がある）
    <R> R jsonToObject(String json) throws IOException {
        return mapper.readValue(json, new TypeReference<R>() {});
    }

    // こうすれば解決
    <R> R jsonToObject(String json, Class<R> convertClass) throws IOException {
        return mapper.readValue(json, convertClass);

        // これは
        //   mapper.readValue(json, mapper.getTypeFactory().constructType(convertClass));
        // と一緒
    }

}


class Todo implements Serializable {
    private String title = "タイトル";
    private String memo = "メモ";

    public String getMemo() {
        return memo;
    }

    public void setMemo(final String memo) {
        this.memo = memo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Todo{" + "title='" + title + '\'' + ", memo='" + memo + '\'' + '}';
    }
}

class Book implements Serializable {
    private String title = "はじめてのJava";
    private int price = 1000;

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(final int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" + "title='" + title + '\'' + ", price=" + price + '}';
    }
}