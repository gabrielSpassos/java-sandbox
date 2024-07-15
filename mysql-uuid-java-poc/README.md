# Java MySQL UUID Compatibility POC

> The goal of this poc is verify the backward compatibility between java (21) and MySQL 8+ UUID()

# Usage

1. Run DB
```bash
./run-db.sh
```

# Outputs:

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

# TODO

- [X] Create database script
- [x] Map Entities
- [x] Create CRUD operations
- [ ] Tests