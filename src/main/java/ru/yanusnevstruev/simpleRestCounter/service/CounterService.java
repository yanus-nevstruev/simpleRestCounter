package ru.yanusnevstruev.simpleRestCounter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yanusnevstruev.simpleRestCounter.model.Counter;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CounterService {
    private final List<Counter> counterList;

    @Autowired
    public CounterService(List<Counter> counterList) {
        this.counterList = counterList;
        this.counterList.clear();
    }

    public boolean addCounter(String name) {
        if (!counterExists(name)) {
            counterList.add(new Counter(name));
            return true;
        }
        return false;
    }

    public boolean deleteCounter(String name) {
        if (counterExists(name)) {
            counterList.remove(getCounterByName(name));
            return true;
        }
        return false;
    }

    public List<String> getAllCountersNames() {
        return counterList.stream().map(Counter::getName).collect(Collectors.toList());
    }

    public boolean incrementCounterByName(String name) {
        if (counterExists(name)) {
            getCounterByName(name).incrementCounter();
            return true;
        }
        return false;
    }

    public Integer valueOfCounterByName(String name) {                       //Значение счетчика по имени
        if (counterExists(name)) {
            return getCounterByName(name).getCurrentValue();
        }
        return 0;
    }

    public Integer getCountersSummaryValue() {                                          //Получить сумму всех значений
        return counterList.stream().mapToInt(Counter::getCurrentValue).sum();
    }


    public Counter getCounterByName(String name) {                                           //Получить каунтер по имени
        return counterList.stream().filter(x -> x.getName().equals(name)).findFirst().get();
    }

    public boolean counterExists(String name) {                                          //проверка на присутствие имени
        return counterList.stream().map(Counter::getName).anyMatch(name::equals);
    }


}
