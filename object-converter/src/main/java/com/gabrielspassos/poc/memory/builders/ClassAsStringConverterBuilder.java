package com.gabrielspassos.poc.memory.builders;

import com.gabrielspassos.poc.memory.services.ClassService;
import com.gabrielspassos.poc.memory.utils.StringUtils;

import java.lang.reflect.Field;
import java.util.List;

public class ClassAsStringConverterBuilder {

    private static ClassAsStringConverterBuilder instance;

    private ClassService classService;

    private ClassAsStringConverterBuilder() {
        this.classService = ClassService.getInstance();
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

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("package com.gabrielspassos.poc.memory.loaders;\n\n");
        stringBuilder.append("import " + input.getClass().getName() + ";\n");
        stringBuilder.append("import " + output.getName() + ";\n\n");
        stringBuilder.append("public class " + className);
        stringBuilder.append(" implements InMemoryConverterClass<" + inputClassName + ", " + outputClassName +"> {\n");
        stringBuilder.append("\tpublic " + outputClassName + " convert(" + inputClassName + " input) {\n");
        stringBuilder.append("\t\t" + outputClassName + " output = new " + outputClassName + "();\n");

        List<Field> fieldsFromInput = classService.getFieldsFromClass(input.getClass());
        List<Field> fieldsFromOutput = classService.getFieldsFromClass(output);
        String setLine = "\t\toutput.set%s(input.get%s());\n";

        for (Field fieldFromOutput : fieldsFromOutput) {
            for (Field fieldFromInput : fieldsFromInput) {
                List<String> fieldWithSynonyms = classService.getFieldWithSynonyms(fieldFromInput);
                if (fieldWithSynonyms.contains(fieldFromOutput.getName())) {
                    String inputFieldName = getFieldName(fieldFromInput);
                    String outputFieldName = getFieldName(fieldFromOutput);
                    stringBuilder.append(String.format(setLine, outputFieldName, inputFieldName));
                }
            }
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

        return StringUtils.capitalize(fieldName);
    }
}
