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

import java.util.Arrays;
import java.util.List;

public class FlinkService {

    public List<Tuple2<Tuple2<Integer, String>, Tuple3<Integer, String, String>>> joinDataSources() throws Exception {
        ExecutionEnvironment env = getEnv();
        DataSource<Tuple3<Integer, String, String>> addresses = env.fromElements(new Tuple3<>(1, "5th Avenue", "London"), new Tuple3<>(2, "4th Avenue", "Liverpool"));
        DataSource<Tuple2<Integer, String>> transactions = env.fromElements(
                new Tuple2<>(1, "transaction_1"), new Tuple2<>(2, "transaction_2"), new Tuple2<>(1, "transaction_1_2"));

        return transactions.join(addresses)
                .where(new IdKeySelectorTransaction())
                .equalTo(new IdKeySelectorAddress())
                .collect();
    }

    public List<Tuple2<Integer, String>> sortTransactions() throws Exception {
        ExecutionEnvironment env = getEnv();
        DataSet<Tuple2<Integer, String>> transactions =
                env.fromElements(
                        new Tuple2<>(4, "transaction_4"),
                        new Tuple2<>(5, "transaction_5"),
                        new Tuple2<>(200, "transaction_200"),
                        new Tuple2<>(2, "transaction_2")
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
}
