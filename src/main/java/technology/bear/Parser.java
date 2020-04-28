package technology.bear;

public class Parser {
    public static final String PHONE_NUMBER_REGEXP = "(\\+[0-9]+)?[(]?[0-9]{1,4}[)]?[-\\s./0-9]*[0-9]";

    public static final String input = "Всем привет. Меня зовут Даниил, сегодня бла бла бла. " +
            "Мой друг Николай звонит по телефону +79999999999";

    public static void main(String[] args) {
        String fixedInput = input.replaceAll(PHONE_NUMBER_REGEXP, "[censored]");
        System.out.println(fixedInput);
    }
}
