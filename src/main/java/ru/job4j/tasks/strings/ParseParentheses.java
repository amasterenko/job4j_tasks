package ru.job4j.tasks.strings;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Программа распаковывания строки.
 * На вход поступает строка вида число[строка],
 * на выход - строка, содержащая повторяющиеся подстроки.
 *
 * Пример:
 * Вход: 3[xyz]4[xy]z
 * Выход: xyzxyzxyzxyxyxyxyz
 *
 * Ограничения:
 * - одно повторение может содержать другое. Например: 2[3[x]y]  = xxxyxxxy
 * - допустимые символы на вход: латинские буквы, числа и скобки []
 * - числа означают только число повторений
 * - скобки только для обозначения повторяющихся подстрок
 * - входная строка всегда валидна.
 */

public class ParseParentheses {
    private final String in;
    private int curIndx = 0;
    private final Set<Character> numChars = new HashSet<>(List.of(
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));

    public ParseParentheses(String in) {
        this.in = in;
    }

    public String start() {
        return goInsideParentheses(1);
    }

    private String goInsideParentheses(int numOfRepeating) {
        StringBuilder sb = new StringBuilder();
        StringBuilder num = new StringBuilder();
        while (curIndx < in.length()) {
            char ch = in.charAt(curIndx++);
            if (numChars.contains(ch)) {
                num.append(ch);
                continue;
            }
            if (ch == '[') {
                sb.append(goInsideParentheses(Integer.parseInt(num.toString())));
                num.delete(0, num.length() - 1);
                continue;
            }
            if (ch == ']') {
                return sb.toString().repeat(numOfRepeating);
            }
            sb.append(ch);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ParseParentheses parse = new ParseParentheses("3[x2[y2[z]y]]z");
        System.out.println(parse.start());
    }
}
