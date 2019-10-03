# PCF Metrics, Prometheus & Micrometer Integration demo

This project shows how to monitor a Spring Boot app with
[PCF Metrics]
and [Micrometer](https://micrometer.io).

## How to use it?

Compile this project using a JDK 8:
```bash
$ ./mvnw clean package
```

Run this app:
```bash
$ java -jar target/micrometer-prometheus-*.jar
```

This app is exposing an endpoint at http://localhost:8091:
```bash
$ curl http://localhost:8080/actuator/metrics
```


Thanks to Micrometer, this app also supports
[Prometheus](https://prometheus.io/) metrics,
without a single line of code.
Just add this dependency to your Spring Boot app to enable
Prometheus support:

```xml
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-registry-prometheus</artifactId>
</dependency>
```

Prometheus metrics are available at http://localhost:8080/actuator/prometheus:

```bash
$ curl http://localhost:8091/actuator/prometheus
```

## Run this app in Pivotal Cloud Foundry

Push this app to PCF:
```bash
$ cf push
```

## Expose app metrics

### Using PCF Metrics Registrar
When this feature is enabled,PAS will periodically scrap metrics from your app using a Prometheus
compatible endpoint. You don't need to install anything else when
using Metric Registrar, nor you need to push your metrics to some
central metrics repository: the platform takes care of everything.

Create a [PCF Metrics Registrar](https://docs.pivotal.io/platform/2-7/metric-registrar/using.html) service instance:
```bash
$ cf install-plugin -r CF-Community "metric-registrar"

$  cf register-metrics-endpoint micrometer /actuator/metrics

$  cf register-metrics-endpoint prometheus /actuator/prometheus
```

Bind your app to the metrics registrar instance, and reload it:
```bash
$ cf bind-service micrometer-prometheus metric-registrar
$ cf restage micrometer-prometheus
```

You're done!

## Use PCF Metrics to view app metrics

This app is now exporting its metrics to PCF Metrics.
You can create a metric chart to show these values on the PCF Metrics Dashboard.
I have placed the screen shot of the custom metrics in this folder


### TODO/Issues

Prometheus inside docker is having issues connecting to spring boot app running
outside of docker container. So i have used prometheus outside of docker
using the prometheus.yml present in /docker/prometheus/prometheus.yml.

brew install prometheus
prometheus --config.file=/docker/prometheus/prometheus.yml


