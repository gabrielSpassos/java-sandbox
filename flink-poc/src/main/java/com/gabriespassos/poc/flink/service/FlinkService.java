package com.gabriespassos.poc.flink.service;

import com.gabriespassos.poc.flink.config.IdKeySelectorAddress;
import com.gabriespassos.poc.flink.config.IdKeySelectorTransaction;
import com.gabriespassos.poc.flink.model.Person;
import org.apache.flink.api.common.operators.Order;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Arrays;
import java.util.List;

public class FlinkService {

    public List<Tuple2<Tuple3<String, Integer, Double>, Tuple3<Integer, String, String>>> joinDataSources() throws Exception {
        ExecutionEnvironment env = getEnv();
        DataSource<Tuple3<Integer, String, String>> addresses = env.fromElements(new Tuple3<>(1, "5th Avenue", "London"), new Tuple3<>(2, "4th Avenue", "Liverpool"));
        DataSource<Tuple3<String, Integer, Double>> transactions = env.fromElements(
                new Tuple3<>("transaction_1", 1, 50.50), new Tuple3<>("transaction_2", 2, 125.80), new Tuple3<>("transaction_3", 1, 20.0));

        return transactions.join(addresses)
                .where(new IdKeySelectorTransaction())
                .equalTo(new IdKeySelectorAddress())
                .collect();
    }

    public List<Tuple2<Tuple3<String, Integer, Double>, Tuple3<Integer, String, String>>> joinCsvFiles() throws Exception {
        ExecutionEnvironment env = getEnv();
        DataSource<Tuple3<Integer, String, String>> addresses = env.readCsvFile("src/main/resources/addresses.csv")
                .types(Integer.class, String.class, String.class);
        DataSource<Tuple3<String, Integer, Double>> transactions = env.readCsvFile("src/main/resources/transactions.csv")
                .types(String.class, Integer.class, Double.class);

        return transactions.join(addresses)
                .where(new IdKeySelectorTransaction())
                .equalTo(new IdKeySelectorAddress())
                .sortPartition(0, Order.DESCENDING)
                .writeAsCsv("src/main/resources/result.csv", "\n", ";")
                .setParallelism(1)
                .getDataSet()
                .collect();
    }

    public List<Tuple3<String, Integer, Double>> sortTransactions() throws Exception {
        ExecutionEnvironment env = getEnv();
        DataSet<Tuple3<String, Integer, Double>> transactions =
                env.fromElements(
                        new Tuple3<>("transaction_4", 4,5.0),
                        new Tuple3<>("transaction_5", 5, 10.0),
                        new Tuple3<>("transaction_200", 200, 33.33),
                        new Tuple3<>("transaction_2", 2, 10000.45)
                );

        return transactions
                .sortPartition(new IdKeySelectorTransaction(), Order.DESCENDING)
                .collect();
    }

    public List<Integer> mapDataSource() throws Exception {
        ExecutionEnvironment env = getEnv();
        DataSource<Person> personDataSource = env.fromCollection(Arrays.asList(
                new Person("Gabriel", 22),
                new Person("Fernanda", 20))
        );

        return personDataSource
                .map(Person::getAge)
                .collect();
    }

    public List<Integer> filterAmounts() throws Exception {
        ExecutionEnvironment env = getEnv();
        DataSet<Integer> amounts = env.fromElements(1, 29, 40, 50, 70);

        int threshold = 30;
        return amounts
                .filter(a -> a > threshold)
                .reduce((integer, t1) -> integer + t1)
                .collect();
    }

    private ExecutionEnvironment getEnv() {
        return ExecutionEnvironment.getExecutionEnvironment();
    }

    private StreamExecutionEnvironment getStreamEnv() {
        return StreamExecutionEnvironment.getExecutionEnvironment();
    }
}
