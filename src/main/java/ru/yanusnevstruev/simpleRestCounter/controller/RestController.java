package ru.yanusnevstruev.simpleRestCounter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.yanusnevstruev.simpleRestCounter.service.CounterService;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/counter/")
public class RestController {
    private final CounterService counterService;

    @Autowired
    public RestController(CounterService counterService) {
        this.counterService = counterService;
    }

    @GetMapping("allCounters")
    public ResponseEntity<List<String>> getAllCountersNames() {
        List<String> result = counterService.getAllCountersNames();
        if (result.isEmpty()) {
            return new ResponseEntity("There is no counters!", HttpStatus.I_AM_A_TEAPOT);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("createCounter/{name}")
    public ResponseEntity createCounter(@PathVariable(name = "name") String name) {
        if (counterService.addCounter(name)) {
            return new ResponseEntity<>("Counter with name \"" + name + "\" was created!", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Counter exists, or can't to create!", HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("increment/{name}")
    public ResponseEntity incrementCounter(@PathVariable(name = "name") String name) {
        if (counterService.incrementCounterByName(name)) {
            return new ResponseEntity<>("Counter with name \"" + name + "\" was incremented!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Counter can't be incremented!", HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("value/{name}")
    public ResponseEntity getCounterValue(@PathVariable(name = "name") String name) {
        return new ResponseEntity<>(counterService.valueOfCounterByName(name), HttpStatus.OK);
    }

    @DeleteMapping("deleteCounter/{name}")
    public ResponseEntity deleteCounter(@PathVariable(name = "name") String name) {
        if (counterService.deleteCounter(name)) {
            return new ResponseEntity<>("Counter with name \"" + name + "\" was deleted!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Counter not exists, or can't to delete!", HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("summary")
    public ResponseEntity getCountersSummaryValue() {
        return new ResponseEntity<>(counterService.getCountersSummaryValue(), HttpStatus.OK);
    }


}
