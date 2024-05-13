import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) throws Exception {
        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Введите два целых числа (арабских или римских) от 1 до 10 и один оператор между ними (+,-,*,/):");
            String operator = scan.nextLine();
            System.out.println(parse(operator));
            System.out.println();
        }
    }

    public static String parse(String operator) throws Exception {
        int num1;
        int num2;
        String oper;
        String result;
        boolean isRoman;
        String[] operand = operator.split("[+\\-*/]");
        if (operand.length != 2) throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        oper = detectOperator(operator);
        if (Roman.isRoman(operand[0]) && Roman.isRoman(operand[1])) {
            num1 = Roman.convertArab(operand[0]);
            num2 = Roman.convertArab(operand[1]);
            isRoman = true;
        }
        else if (!Roman.isRoman(operand[0]) && !Roman.isRoman(operand[1])) {
            num1 = Integer.parseInt(operand[0]);
            num2 = Integer.parseInt(operand[1]);
            isRoman = false;
        }
        else {
            throw new Exception("Числа должны быть в одном формате");
        }
        if (num1 > 10 || num1 <= 0 || num2 > 10 || num2 <= 0) {
            throw new Exception("Числа должны быть от 1 до 10");
        }
        int arab = calc(num1, oper, num2);
        if (isRoman) {
            if (arab <= 0) {
                throw new Exception("Римское число должно быть больше нуля");
            }
            result = Roman.convertRom(arab);
        } else {
            result = String.valueOf(arab);
        }
        return result;
    }

    static String detectOperator(String expression) {
        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
    }

    static int calc(int a, String oper, int b) {

        return switch (oper) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            default -> a / b;
        };
    }
}

class Roman {
    static String[] romanArray = new String[]{"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
            "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
            "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
            "XCVIII", "XCIX", "C"};

    public static boolean isRoman(String val) {
        for (int i = 0; i < romanArray.length; i++) {
            if (val.equals(romanArray[i])) {
                return true;
            }
        }
        return false;
    }

    public static int convertArab(String roman) {
        for (int i = 0; i < romanArray.length; i++) {
            if (roman.equals(romanArray[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String convertRom(int arab) {
        return romanArray[arab];
    }
}

