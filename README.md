# micrometer-prometheus-apm
micrometer-prometheus-apm

# Steps to Run

1. Start mongodb 

   docker run -p 27017:27017 mongo
   
2. Start Spring boot application by running MicrometerPrometheusDemoApplication class

3. Start prometheus 

   docker run -d -p 9090:9090 -v <project-location>/micrometer-prometheus-apm/src/main/resources/prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus
    
   http://localhost:9090/

# Query prometheus for APM Data 
(These are only few samples, Query language and data set is very powerful)

1.  **Inbound Request Data (out of box available)**     
    http_server_requests_seconds_count   :To get total request accepted by Service 
    http_server_requests_seconds_count{uri="/fruits"}   :To get total request accepted by specific endpoint 
    http_server_requests_seconds_sum{uri="/fruits"} /  http_server_requests_seconds_count{uri="/fruits"}  : To get average response time of specific endpoint
    rate(http_server_requests_seconds_sum{uri="/fruits"}[1m])/rate(http_server_requests_seconds_count{uri="/fruits"}[1m]) : Average response rate per minute 
    rate(http_server_requests_seconds_count[1m])    :Application Throughput per minute
    http_server_requests_seconds_count{status="500"}  : All request with status 500     
    
2.  **Count any application matrix**     
    green_fruit_counter_total  : Count All post requests for green fruits    

2.  **View any application specific data (concurrent users , cache size etc)**     
    some_cache_size  : to get current cache size
    
3. **Error Monitoring**
    INTERNAL_SERVER_ERROR_total  : Get count of internal server errors.
    rate(logback_events_total{level="error"} [1m])  : error rate(All errors in application) 
    
3. **performance monitoring of any specific method**    
     method_timed_seconds_sum{method="saveFruit"}/method_timed_seconds_count{method="saveFruit"} // Average time to save Fruit  

4. **Outboud http request (find avg response time of any external service)**
    http_client_requests_seconds_sum{clientName="dummy.restapiexample.com",uri="/api/v1/employees"} / http_client_requests_seconds_count{clientName="dummy.restapiexample.com",uri="/api/v1/employees"}
    Above query will give me avg response time of host dummy.restapiexample.com for uri /api/v1/employees
    

    
**Other out of box support is also available for** 

Cache utilization

Datasource utilization, including HikariCP pool metrics

RabbitMQ connection factories

File descriptor usage    
              
    
    
          
