package redmine.model;

import io.qameta.allure.Step;

public interface Generatable<T> {

    T read();

    T update();

    T create();

    void delete();

    @Step("Сгенерирована сущность")
    default T generate() {
        if (read() != null) {
            return update();
        } else {
            return create();
        }
    }

}
