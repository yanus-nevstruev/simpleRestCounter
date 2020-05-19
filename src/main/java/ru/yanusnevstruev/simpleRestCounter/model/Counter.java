package ru.yanusnevstruev.simpleRestCounter.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Data
@NoArgsConstructor
@Scope("prototype")
public class Counter {
    private String name;
    private Integer currentValue;

    public Counter(String name) {
        this.name = name;
        this.currentValue = 0;
    }

    public Counter(String name, Integer currentValue) {
        this.name = name;
        this.currentValue = currentValue;
    }

    public void incrementCounter() {
        this.currentValue++;
    }


}
