# Java MySQL UUID Compatibility POC

> The goal of this poc is verify the backward compatibility between java (21) and MySQL 8+ UUID()

# Output
```shell
List people
[{"id":1,"uuid":"6465d805-42c2-11ef-bd9e-86b97898346e","firstName":"Gabriel","lastName":"Passos","createdAt":"2024-07-15T18:53:34.000+00:00"},{"id":2,"uuid":"646bb450-42c2-11ef-bd9e-86b97898346e","firstName":"Joao","lastName":"Ninguem","createdAt":"2024-07-15T18:53:34.000+00:00"},{"id":3,"uuid":"60b15f8f-0f9c-4242-b9d7-6dfa1d98f633","firstName":"Joe","lastName":"Doe","createdAt":"2024-07-15T18:53:34.000+00:00"}]\n
Creating person
{"id":4,"uuid":"a6a3b889-87ed-4a42-95d3-b928c10647fe","firstName":"Mary","lastName":"Smith","createdAt":"2024-07-15T15:58:02.787+00:00"}\n
List people
[{"id":1,"uuid":"6465d805-42c2-11ef-bd9e-86b97898346e","firstName":"Gabriel","lastName":"Passos","createdAt":"2024-07-15T18:53:34.000+00:00"},{"id":2,"uuid":"646bb450-42c2-11ef-bd9e-86b97898346e","firstName":"Joao","lastName":"Ninguem","createdAt":"2024-07-15T18:53:34.000+00:00"},{"id":3,"uuid":"60b15f8f-0f9c-4242-b9d7-6dfa1d98f633","firstName":"Joe","lastName":"Doe","createdAt":"2024-07-15T18:53:34.000+00:00"},{"id":4,"uuid":"a6a3b889-87ed-4a42-95d3-b928c10647fe","firstName":"Mary","lastName":"Smith","createdAt":"2024-07-15T15:58:03.000+00:00"}]\n
Get Person from Id 2
{"id":2,"uuid":"646bb450-42c2-11ef-bd9e-86b97898346e","firstName":"Joao","lastName":"Ninguem","createdAt":"2024-07-15T18:53:34.000+00:00"}\n
Get Person from UUID
{"id":3,"uuid":"60b15f8f-0f9c-4242-b9d7-6dfa1d98f633","firstName":"Joe","lastName":"Doe","createdAt":"2024-07-15T18:53:34.000+00:00"}\n
Update person from Id 2
{"id":2,"uuid":"959af7a1-9e20-4a65-a681-e3163cc34666","firstName":"Joao","lastName":"Silva","createdAt":"2024-07-15T18:53:34.000+00:00"}\n
List people
[{"id":1,"uuid":"6465d805-42c2-11ef-bd9e-86b97898346e","firstName":"Gabriel","lastName":"Passos","createdAt":"2024-07-15T18:53:34.000+00:00"},{"id":2,"uuid":"959af7a1-9e20-4a65-a681-e3163cc34666","firstName":"Joao","lastName":"Silva","createdAt":"2024-07-15T18:53:34.000+00:00"},{"id":3,"uuid":"60b15f8f-0f9c-4242-b9d7-6dfa1d98f633","firstName":"Joe","lastName":"Doe","createdAt":"2024-07-15T18:53:34.000+00:00"},{"id":4,"uuid":"a6a3b889-87ed-4a42-95d3-b928c10647fe","firstName":"Mary","lastName":"Smith","createdAt":"2024-07-15T15:58:03.000+00:00"}]\n
Delete person from id 2
{"id":2,"uuid":"959af7a1-9e20-4a65-a681-e3163cc34666","firstName":"Joao","lastName":"Silva","createdAt":"2024-07-15T18:53:34.000+00:00"}\n
List people
[{"id":1,"uuid":"6465d805-42c2-11ef-bd9e-86b97898346e","firstName":"Gabriel","lastName":"Passos","createdAt":"2024-07-15T18:53:34.000+00:00"},{"id":3,"uuid":"60b15f8f-0f9c-4242-b9d7-6dfa1d98f633","firstName":"Joe","lastName":"Doe","createdAt":"2024-07-15T18:53:34.000+00:00"},{"id":4,"uuid":"a6a3b889-87ed-4a42-95d3-b928c10647fe","firstName":"Mary","lastName":"Smith","createdAt":"2024-07-15T15:58:03.000+00:00"}]\n
```

```shell
List pets
[{"id":"98246fb0-4aa3-11ef-a063-bac245ab893f","name":"Toby","createdAt":"2024-07-25T19:33:16.000+00:00"},{"id":"e1e183be-96c3-4946-b598-31422fc041f4","name":"Mel","createdAt":"2024-07-25T19:33:16.000+00:00"}]\n
Creating pet
{"id":"d279e5b2-313e-4071-967d-6244a5c2a349","name":"Buddy","createdAt":"2024-07-30T16:19:16.544+00:00"}\n
List pets
[{"id":"98246fb0-4aa3-11ef-a063-bac245ab893f","name":"Toby","createdAt":"2024-07-25T19:33:16.000+00:00"},{"id":"d279e5b2-313e-4071-967d-6244a5c2a349","name":"Buddy","createdAt":"2024-07-30T16:19:17.000+00:00"},{"id":"e1e183be-96c3-4946-b598-31422fc041f4","name":"Mel","createdAt":"2024-07-25T19:33:16.000+00:00"}]\n
Get pet by id e1e183be-96c3-4946-b598-31422fc041f4
{"id":"e1e183be-96c3-4946-b598-31422fc041f4","name":"Mel","createdAt":"2024-07-25T19:33:16.000+00:00"}\n
Update pet by id e1e183be-96c3-4946-b598-31422fc041f4
{"id":"e1e183be-96c3-4946-b598-31422fc041f4","name":"mel","createdAt":"2024-07-25T19:33:16.000+00:00"}\n
List pets
[{"id":"98246fb0-4aa3-11ef-a063-bac245ab893f","name":"Toby","createdAt":"2024-07-25T19:33:16.000+00:00"},{"id":"d279e5b2-313e-4071-967d-6244a5c2a349","name":"Buddy","createdAt":"2024-07-30T16:19:17.000+00:00"},{"id":"e1e183be-96c3-4946-b598-31422fc041f4","name":"mel","createdAt":"2024-07-25T19:33:16.000+00:00"}]\n
Delete pet by id e1e183be-96c3-4946-b598-31422fc041f4
{"id":"e1e183be-96c3-4946-b598-31422fc041f4","name":"mel","createdAt":"2024-07-25T19:33:16.000+00:00"}\n
List pets
[{"id":"98246fb0-4aa3-11ef-a063-bac245ab893f","name":"Toby","createdAt":"2024-07-25T19:33:16.000+00:00"},{"id":"d279e5b2-313e-4071-967d-6244a5c2a349","name":"Buddy","createdAt":"2024-07-30T16:19:17.000+00:00"}]\n
```

# Usage

1. Run DB
```bash
./run-db.sh
```

2. Start the server

3. Run the curl to manipulate person entity
```bash
./run-person-requests
```

# Outcomes

1. The database column type is `VARCHAR(36)`
2. The insert of the database with mysql `UUID()` worked successfully
3. The insert of the database with value `60b15f8f-0f9c-4242-b9d7-6dfa1d98f633` worked successfully
4. Without custom config on java side we have issues when try to insert with `java.util.UUID.randomUUID()`
```
java.sql.SQLException: Incorrect string value: '\xAC\xED\x00\x05sr...' for column 'uuid' at row 1
	at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:130) ~[mysql-connector-j-8.3.0.jar:8.3.0]
	at com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping.translateException(SQLExceptionsMapping.java:122) ~[mysql-connector-j-8.3.0.jar:8.3.0]
	at com.mysql.cj.jdbc.ClientPreparedStatement.executeInternal(ClientPreparedStatement.java:912) ~[mysql-connector-j-8.3.0.jar:8.3.0]
	at com.mysql.cj.jdbc.ClientPreparedStatement.executeUpdateInternal(ClientPreparedStatement.java:1054) ~[mysql-connector-j-8.3.0.jar:8.3.0]
	at com.mysql.cj.jdbc.ClientPreparedStatement.executeUpdateInternal(ClientPreparedStatement.java:1003) ~[mysql-connector-j-8.3.0.jar:8.3.0]
	at com.mysql.cj.jdbc.ClientPreparedStatement.executeLargeUpdate(ClientPreparedStatement.java:1312) ~[mysql-connector-j-8.3.0.jar:8.3.0]
	at com.mysql.cj.jdbc.ClientPreparedStatement.executeUpdate(ClientPreparedStatement.java:988) ~[mysql-connector-j-8.3.0.jar:8.3.0]
	at com.zaxxer.hikari.pool.ProxyPreparedStatement.executeUpdate(ProxyPreparedStatement.java:61) ~[HikariCP-5.1.0.jar:na]
	at com.zaxxer.hikari.pool.HikariProxyPreparedStatement.executeUpdate(HikariProxyPreparedStatement.java) ~[HikariCP-5.1.0.jar:na]
	at org.springframework.jdbc.core.JdbcTemplate.lambda$update$3(JdbcTemplate.java:1002) ~[spring-jdbc-6.1.10.jar:6.1.10]
	at org.springframework.jdbc.core.JdbcTemplate.execute(JdbcTemplate.java:658) ~[spring-jdbc-6.1.10.jar:6.1.10]
	at org.springframework.jdbc.core.JdbcTemplate.update(JdbcTemplate.java:1001) ~[spring-jdbc-6.1.10.jar:6.1.10]
	at org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate.update(NamedParameterJdbcTemplate.java:365) ~[spring-jdbc-6.1.10.jar:6.1.10]
	at org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate.update(NamedParameterJdbcTemplate.java:349) ~[spring-jdbc-6.1.10.jar:6.1.10]
	at org.springframework.data.jdbc.core.convert.IdGeneratingInsertStrategy.execute(IdGeneratingInsertStrategy.java:68) ~[spring-data-jdbc-3.3.1.jar:3.3.1]
	at org.springframework.data.jdbc.core.convert.DefaultDataAccessStrategy.insert(DefaultDataAccessStrategy.java:110) ~[spring-data-jdbc-3.3.1.jar:3.3.1]
	at org.springframework.data.jdbc.core.JdbcAggregateChangeExecutionContext.executeInsertRoot(JdbcAggregateChangeExecutionContext.java:83) ~[spring-data-jdbc-3.3.1.jar:3.3.1]
	at org.springframework.data.jdbc.core.AggregateChangeExecutor.execute(AggregateChangeExecutor.java:85) ~[spring-data-jdbc-3.3.1.jar:3.3.1]
	at org.springframework.data.jdbc.core.AggregateChangeExecutor.lambda$executeSave$0(AggregateChangeExecutor.java:61) ~[spring-data-jdbc-3.3.1.jar:3.3.1]
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596) ~[na:na]
	at org.springframework.data.relational.core.conversion.SaveBatchingAggregateChange.forEachAction(SaveBatchingAggregateChange.java:74) ~[spring-data-relational-3.3.1.jar:3.3.1]
	at org.springframework.data.jdbc.core.AggregateChangeExecutor.executeSave(AggregateChangeExecutor.java:61) ~[spring-data-jdbc-3.3.1.jar:3.3.1]
	at org.springframework.data.jdbc.core.JdbcAggregateTemplate.performSave(JdbcAggregateTemplate.java:491) ~[spring-data-jdbc-3.3.1.jar:3.3.1]
	at org.springframework.data.jdbc.core.JdbcAggregateTemplate.save(JdbcAggregateTemplate.java:168) ~[spring-data-jdbc-3.3.1.jar:3.3.1]
	at org.springframework.data.jdbc.repository.support.SimpleJdbcRepository.save(SimpleJdbcRepository.java:68) ~[spring-data-jdbc-3.3.1.jar:3.3.1]
	at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:580) ~[na:na]
	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:354) ~[spring-aop-6.1.10.jar:6.1.10]
	at org.springframework.data.repository.core.support.RepositoryMethodInvoker$RepositoryFragmentMethodInvoker.lambda$new$0(RepositoryMethodInvoker.java:277) ~[spring-data-commons-3.3.1.jar:3.3.1]
	at org.springframework.data.repository.core.support.RepositoryMethodInvoker.doInvoke(RepositoryMethodInvoker.java:170) ~[spring-data-commons-3.3.1.jar:3.3.1]
	at org.springframework.data.repository.core.support.RepositoryMethodInvoker.invoke(RepositoryMethodInvoker.java:158) ~[spring-data-commons-3.3.1.jar:3.3.1]
	at org.springframework.data.repository.core.support.RepositoryComposition$RepositoryFragments.invoke(RepositoryComposition.java:516) ~[spring-data-commons-3.3.1.jar:3.3.1]
	at org.springframework.data.repository.core.support.RepositoryComposition.invoke(RepositoryComposition.java:285) ~[spring-data-commons-3.3.1.jar:3.3.1]
	at org.springframework.data.repository.core.support.RepositoryFactorySupport$ImplementationMethodExecutionInterceptor.invoke(RepositoryFactorySupport.java:628) ~[spring-data-commons-3.3.1.jar:3.3.1]
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184) ~[spring-aop-6.1.10.jar:6.1.10]
	at org.springframework.data.repository.core.support.QueryExecutorMethodInterceptor.doInvoke(QueryExecutorMethodInterceptor.java:168) ~[spring-data-commons-3.3.1.jar:3.3.1]
	at org.springframework.data.repository.core.support.QueryExecutorMethodInterceptor.invoke(QueryExecutorMethodInterceptor.java:143) ~[spring-data-commons-3.3.1.jar:3.3.1]
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184) ~[spring-aop-6.1.10.jar:6.1.10]
	at org.springframework.transaction.interceptor.TransactionInterceptor$1.proceedWithInvocation(TransactionInterceptor.java:123) ~[spring-tx-6.1.10.jar:6.1.10]
	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:392) ~[spring-tx-6.1.10.jar:6.1.10]
	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:119) ~[spring-tx-6.1.10.jar:6.1.10]
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184) ~[spring-aop-6.1.10.jar:6.1.10]
	at org.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:138) ~[spring-tx-6.1.10.jar:6.1.10]
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184) ~[spring-aop-6.1.10.jar:6.1.10]
	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:97) ~[spring-aop-6.1.10.jar:6.1.10]
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184) ~[spring-aop-6.1.10.jar:6.1.10]
	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:223) ~[spring-aop-6.1.10.jar:6.1.10]
	at jdk.proxy2/jdk.proxy2.$Proxy81.save(Unknown Source) ~[na:na]
	at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:580) ~[na:na]
	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:354) ~[spring-aop-6.1.10.jar:6.1.10]
	at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:196) ~[spring-aop-6.1.10.jar:6.1.10]
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163) ~[spring-aop-6.1.10.jar:6.1.10]
	at org.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:138) ~[spring-tx-6.1.10.jar:6.1.10]
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184) ~[spring-aop-6.1.10.jar:6.1.10]
	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:223) ~[spring-aop-6.1.10.jar:6.1.10]
	at jdk.proxy2/jdk.proxy2.$Proxy81.save(Unknown Source) ~[na:na]
	at com.gabrielspassos.service.PersonService.createPerson(PersonService.java:33) ~[classes/:na]
	at com.gabrielspassos.api.PersonController.createPerson(PersonController.java:33) ~[classes/:na]
	at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:103) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:580) ~[na:na]
	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:255) ~[spring-web-6.1.10.jar:6.1.10]
	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:188) ~[spring-web-6.1.10.jar:6.1.10]
	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:118) ~[spring-webmvc-6.1.10.jar:6.1.10]
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:926) ~[spring-webmvc-6.1.10.jar:6.1.10]
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:831) ~[spring-webmvc-6.1.10.jar:6.1.10]
	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87) ~[spring-webmvc-6.1.10.jar:6.1.10]
	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1089) ~[spring-webmvc-6.1.10.jar:6.1.10]
	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:979) ~[spring-webmvc-6.1.10.jar:6.1.10]
	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1014) ~[spring-webmvc-6.1.10.jar:6.1.10]
	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:914) ~[spring-webmvc-6.1.10.jar:6.1.10]
	at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:590) ~[tomcat-embed-core-10.1.25.jar:6.0]
	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:885) ~[spring-webmvc-6.1.10.jar:6.1.10]
	at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658) ~[tomcat-embed-core-10.1.25.jar:6.0]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:195) ~[tomcat-embed-core-10.1.25.jar:10.1.25]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140) ~[tomcat-embed-core-10.1.25.jar:10.1.25]
	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:51) ~[tomcat-embed-websocket-10.1.25.jar:10.1.25]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164) ~[tomcat-embed-core-10.1.25.jar:10.1.25]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140) ~[tomcat-embed-core-10.1.25.jar:10.1.25]
	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100) ~[spring-web-6.1.10.jar:6.1.10]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.1.10.jar:6.1.10]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164) ~[tomcat-embed-core-10.1.25.jar:10.1.25]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140) ~[tomcat-embed-core-10.1.25.jar:10.1.25]
	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93) ~[spring-web-6.1.10.jar:6.1.10]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.1.10.jar:6.1.10]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164) ~[tomcat-embed-core-10.1.25.jar:10.1.25]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140) ~[tomcat-embed-core-10.1.25.jar:10.1.25]
	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201) ~[spring-web-6.1.10.jar:6.1.10]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[spring-web-6.1.10.jar:6.1.10]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164) ~[tomcat-embed-core-10.1.25.jar:10.1.25]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140) ~[tomcat-embed-core-10.1.25.jar:10.1.25]
	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:167) ~[tomcat-embed-core-10.1.25.jar:10.1.25]
	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90) ~[tomcat-embed-core-10.1.25.jar:10.1.25]
	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:482) ~[tomcat-embed-core-10.1.25.jar:10.1.25]
	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:115) ~[tomcat-embed-core-10.1.25.jar:10.1.25]
	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93) ~[tomcat-embed-core-10.1.25.jar:10.1.25]
	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74) ~[tomcat-embed-core-10.1.25.jar:10.1.25]
	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:344) ~[tomcat-embed-core-10.1.25.jar:10.1.25]
	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:389) ~[tomcat-embed-core-10.1.25.jar:10.1.25]
	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63) ~[tomcat-embed-core-10.1.25.jar:10.1.25]
	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:904) ~[tomcat-embed-core-10.1.25.jar:10.1.25]
	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1741) ~[tomcat-embed-core-10.1.25.jar:10.1.25]
	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:52) ~[tomcat-embed-core-10.1.25.jar:10.1.25]
	at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1190) ~[tomcat-embed-core-10.1.25.jar:10.1.25]
	at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659) ~[tomcat-embed-core-10.1.25.jar:10.1.25]
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:63) ~[tomcat-embed-core-10.1.25.jar:10.1.25]
	at java.base/java.lang.Thread.run(Thread.java:1583) ~[na:na]
```
5. Handle `UUID` as `String` on java side work successfully, and solve issue #4
6. Issue using the repository.save() using the UUID (string) as ID 
```
org.springframework.dao.IncorrectUpdateSemanticsDataAccessException: Failed to update entity [PetEntity{id=4e40091c-9bc4-468e-b1e7-8172b03505c8, name='Buddy', createdAt=2024-07-30 12:35:09.618796}]; Id [4e40091c-9bc4-468e-b1e7-8172b03505c8] not found in database
```
7. Implementing `Persistable<ID>` with the method `isNew` we can bypass the issue #6

# TODO

- [x] Create database script
- [x] Map Entities
- [x] Create CRUD operations for Person
- [x] Create CRUD operations for Pet
- [ ] Tests