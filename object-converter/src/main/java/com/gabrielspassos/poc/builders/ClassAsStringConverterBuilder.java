package com.gabrielspassos.poc.builders;

import com.gabrielspassos.poc.dtos.PairDTO;
import com.gabrielspassos.poc.services.CacheableClassService;
import com.gabrielspassos.poc.services.IClassService;

import java.lang.reflect.Field;
import java.util.List;

import static com.gabrielspassos.poc.utils.StringUtils.capitalize;

public class ClassAsStringConverterBuilder {

    private static ClassAsStringConverterBuilder instance;

    private IClassService classService;

    private ClassAsStringConverterBuilder() {
        this.classService = CacheableClassService.getCacheableClassService();
    }

    public static synchronized ClassAsStringConverterBuilder getBuilder(){
        if (instance == null) {
            instance = new ClassAsStringConverterBuilder();
        }
        return instance;
    }

    public <O> String getConverterClassName(Object input, Class<O> output) {
        String inputClassName = input.getClass().getSimpleName();
        String outputClassName = output.getSimpleName();
        return "ConverterFrom" + inputClassName + "To" + outputClassName;
    }

    public <O> String createConverterClassAsString(String className, Object input, Class<O> output) {
        String inputClassName = input.getClass().getSimpleName();
        String outputClassName = output.getSimpleName();

        List<PairDTO<Field, Field>> matchingFields = classService.getMatchingFields(input.getClass(), output);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("package com.gabrielspassos.poc.loaders;\n\n");
        stringBuilder.append("import " + input.getClass().getName() + ";\n");
        stringBuilder.append("import " + output.getName() + ";\n\n");
        stringBuilder.append("public class " + className);
        stringBuilder.append(" implements InMemoryClass<" + inputClassName + ", " + outputClassName +"> {\n");
        stringBuilder.append("\tpublic " + outputClassName + " convert(" + inputClassName + " input) {\n");
        stringBuilder.append("\t\t" + outputClassName + " output = new " + outputClassName + "();\n");

        String setLine = "\t\toutput.set%s(input.get%s());\n";
        for (PairDTO<Field, Field> pair: matchingFields) {
            String inputFieldName = getFieldName(pair.getLeft());
            String outputFieldName = getFieldName(pair.getRight());
            stringBuilder.append(String.format(setLine, outputFieldName, inputFieldName));
        }

        stringBuilder.append("\t\treturn output;\n");
        stringBuilder.append("\t}\n");
        stringBuilder.append("}\n");
        return stringBuilder.toString();
    }

    private String getFieldName(Field field) {
        var fieldName = field.getName().startsWith("is")
                ? field.getName().replaceAll("is", "")
                : field.getName();

        return capitalize(fieldName);
    }
}
