# Read Me First

It's RESTfull service.


# Getting Started
## server port = 8383
* GET /counter/allCounters - list of counters names
* GET /counter/createCounter/somename - create new counter with name "somename"
* GET /counter/increment/somename - increment counter with name "somename"
* GET /counter/value/somename - get value of counter with name "somename"
* DELETE /counter/deleteCounter/somename - delete counter by name "somename"
* GET /counter/summary - summarize values of all counters
