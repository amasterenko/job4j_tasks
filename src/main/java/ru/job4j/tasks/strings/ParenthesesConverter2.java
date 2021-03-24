package ru.job4j.tasks.strings;

import java.util.HashSet;
import java.util.LinkedList;
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
 *
 * The class converts an input string with parentheses to an output string
 * using two stacks.
 * For example: 2[x3[y]]z will be converted to xyyyxyyyz
 *
 * @author AndrewMs
 * @version 1.0
 */

public class ParenthesesConverter2 {
    private final String in;
    private int curIndx = 0;
    private final Set<Character> numChars = new HashSet<>(List.of(
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));
    private final LinkedList<Integer> stackOfRepeats = new LinkedList<>();
    private final LinkedList<String> stackOfSymbols = new LinkedList<>();

    public ParenthesesConverter2(String in) {
        this.in = in;
    }

    private String convert() {
        StringBuilder curSymbols = new StringBuilder();
        StringBuilder repeats = new StringBuilder();
        while (curIndx < in.length()) {
            char ch = in.charAt(curIndx++);
            if (numChars.contains(ch)) {
                repeats.append(ch);
                continue;
            }
            if (ch == '[') {
                stackOfRepeats.push(Integer.parseInt(repeats.toString()));
                stackOfSymbols.push(curSymbols.toString());
                repeats.delete(0, repeats.length());
                curSymbols.delete(0, curSymbols.length());
                continue;
            }
            if (ch == ']') {
                curSymbols = new StringBuilder(stackOfSymbols.pop())
                        .append(curSymbols.toString().repeat(stackOfRepeats.pop()));
                continue;
            }
            curSymbols.append(ch);
        }
        return curSymbols.toString();
    }

    public static void main(String[] args) {
        ParenthesesConverter2 parse = new ParenthesesConverter2("12[xx2[y]]"); //3[x2[y2[z]y]]z
        System.out.println(parse.convert());
    }
}
