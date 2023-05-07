package com.gabrielspassos.poc.loaders;

import com.gabrielspassos.poc.dto.AccountDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import java.util.Collections;
import java.util.List;

public class InMemoryLoadersTest {

    final static String QUALIFIED_CLASS_NAME = "com.gabrielspassos.poc.loaders.TestClass";

    final static String SOURCE_CODE =
            "package com.gabrielspassos.poc.loaders;\n" +
            "\n" +
            "import com.gabrielspassos.poc.dto.AccountDTO;\n" +
            "import com.gabrielspassos.poc.dto.SavingAccountDTO;\n" +
            "\n" +
            "public class TestClass implements InMemoryClass<AccountDTO, SavingAccountDTO> {\n" +
            "\n" +
            "    public SavingAccountDTO convert(AccountDTO input) {\n" +
            "        SavingAccountDTO output = new SavingAccountDTO();\n" +
            "        output.setAgency(input.getAgency());\n" +
            "        output.setNumber(input.getNumber());\n" +
            "        output.setDigit(input.getDigit());\n" +
            "        return output;\n" +
            "    }\n" +
            "    \n" +
            "}";

    @Test
    public void whenStringIsCompiled_ThenCodeShouldExecute() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        InMemoryFileManager manager = new InMemoryFileManager(compiler.getStandardFileManager(null, null, null));

        List<JavaFileObject> sourceFiles = Collections.singletonList(new JavaSourceFromString(QUALIFIED_CLASS_NAME, SOURCE_CODE));

        JavaCompiler.CompilationTask task = compiler.getTask(null, manager, diagnostics, null, null, sourceFiles);

        boolean result = task.call();

        if (!result) {
            diagnostics.getDiagnostics()
                    .forEach(d -> System.out.println("Error: " + d));
            Assertions.fail();
        } else {
            AccountDTO account = new AccountDTO("0001", "12345", 6L);

            ClassLoader classLoader = manager.getClassLoader(null);
            Class<?> clazz = classLoader.loadClass(QUALIFIED_CLASS_NAME);
            InMemoryClass instanceOfClass = (InMemoryClass) clazz.newInstance();

            Assertions.assertInstanceOf(InMemoryClass.class, instanceOfClass);

            Object convert = instanceOfClass.convert(account);
            Assertions.assertNotNull(convert);
        }
    }
}
