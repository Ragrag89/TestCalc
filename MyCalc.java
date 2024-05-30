import java.util.Scanner;

public class MyCalc {
    public static void main(String[] args) throws Exception {
        System.out.println("Введите выражение и знак операции через пробел:");
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        char symbol;
        String[] data;
        if (str.contains(" + ")) {
            data = str.split(" \\+ ");
            symbol = '+';
        } else if (str.contains(" - ")) {
            data = str.split(" - ");
            symbol = '-';
        } else if (str.contains(" * ")) {
            data = str.split(" \\* ");
            symbol = '*';
        } else if (str.contains(" / ")) {
            data = str.split(" / ");
            symbol = '/';
        } else {
            throw new Exception("Введен неверный знак действия");
        }

        if (data[0].contains("\"")) {
            if (data[0].length() > 10 || data[1].length() > 10) {
                throw new Exception("Строки не должны быть длиннее 10 символов");
            } else if (data[1].contains("\"")) {
                for (int i = 0; i < data.length; i++) {
                    data[i] = data[i].replace("\"", "");
                }
                if (symbol == '+') {
                    quotes(data[0] + data[1]);
                } else if (symbol == '-') {
                    int index = data[0].indexOf(data[1]);
                    if (index == -1) {
                        quotes(data[0]);
                    } else {
                        String result = data[0].substring(0, index);
                        result += data[0].substring(index + data[1].length());
                        quotes(result);
                    }
                } else {
                    throw new Exception("Делить и умножать можно только на число");
                }
            } else {
                for (int i = 0; i < data.length; i++) {
                    data[i] = data[i].replace("\"", "");
                }
                if (symbol == '+' || symbol == '-') {
                    throw new Exception("Вычитать и складывать можно только строки");
                } else if (symbol == '*') {
                    int n = Integer.parseInt(data[1]);
                    if (n > 10) {
                        throw new Exception("Числа должны быть от 1 до 10");
                    }
                    String result = "";
                    for (int i = 0; i < n; i++) {
                        result += data[0];
                    }
                    if (result.length() > 40) {
                        String newResult = result.substring(0, 40);
                        quotes(newResult+"...");
                    } else {
                        quotes(result);
                    }
                } else {
                    int n = Integer.parseInt(data[1]);
                    if (n > 10) {
                        throw new Exception("Числа должны быть от 1 до 10");
                    }
                    int newLength = data[0].length()/n;
                    String result = data[0].substring(0, newLength);
                    quotes(result);
                }
            }
        } else {
            throw new Exception("Первым аргументом выражения должна быть строка в \"\"");
        }
    }

    static void quotes(String text) {
        System.out.println("\"" + text + "\"");
    }
}