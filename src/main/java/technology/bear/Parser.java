package technology.bear;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import static java.util.Arrays.stream;

public class Parser {
    public static final String PHONE_NUMBER_REGEXP = "(\\+[0-9]+)?[(]?[0-9]{1,4}[)]?[-\\s./0-9]*[0-9]";
    public static final String NAME_REGEXP = "(?!^)(?<!\\. )[А-Я][а-я]+";
    public static final String REPLACEMENT = "[censored]";

    public static void main(String[] args) throws URISyntaxException, IOException {
        censorText("война_и_мир.txt");
        censorText("номера_телефонов.txt");
    }

    private static void censorText(String textName) throws IOException, URISyntaxException {
        URL resource = Parser.class.getResource("/" + textName);
        String content = readFile(get(resource.toURI()).toFile().toString(), UTF_8);

        System.out.println(textName + "\nИсходный текст:");
        System.out.println(content);

        System.out.println("После преобразования:");
        System.out.println(censorText(content, REPLACEMENT, PHONE_NUMBER_REGEXP, NAME_REGEXP) + "\n");
    }

    private static String readFile(String path, Charset encoding) throws IOException {
        return new String(readAllBytes(get(path)), encoding);
    }

    private static String censorText(String text, String replacement, String... regexps) {
        if (regexps == null || regexps.length == 0) {
            return "";
        }
        StringBuilder regexpBuilder = new StringBuilder("(");
        stream(regexps).forEach(r -> regexpBuilder.append(r).append("|"));
        regexpBuilder.deleteCharAt(regexpBuilder.length() - 1);
        regexpBuilder.append(")");
        return text.replaceAll(regexpBuilder.toString(), replacement);
    }
}
