import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение");
        String input = scanner.nextLine();
        System.out.println(Main.calc(input));

    }

    public static String calc(String input) throws Exception {
        int numberOne;
        int numberTwo;
        boolean getRom;
        String operator;
        String result;


        input = input.replaceAll("\\s","");
        String[] numbers = input.split("[+\\-*/%]");

        if (numbers.length < 2)
            throw new Exception("Cтрока не является математической операцией");
        else if (numbers.length > 3)
            throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        else
            operator = getOperator(input);
        if (operator == null)
            throw new Exception("Неподдерживаемая математическая операция, возможны операторы: +, -, /, *");

        if (Rom.getRom(numbers[0]) && Rom.getRom(numbers[1])) {
            numberOne = Rom.convertArab(numbers[0]);
            numberTwo = Rom.convertArab(numbers[1]);
            getRom = true;
        }
        else if (!Rom.getRom(numbers[0]) && !Rom.getRom(numbers[1])) {
            numberOne = Integer.parseInt(numbers[0]);
            numberTwo = Integer.parseInt(numbers[1]);
            getRom = false;
        }
        else
            throw new Exception("Используются одновременно разные системы счисления");

        if (numberOne < 0 || numberOne > 10 || numberTwo < 0 || numberTwo > 10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }
        int arabNumbers = calculator(numberOne, numberTwo, operator);
        if (getRom) {
            if (arabNumbers <= 0){
                throw new Exception("В римской системе нет отрицательных чисел");
            }
            result = Rom.convertRom(arabNumbers);

        }else {
            result = String.valueOf(arabNumbers);
        }


        return result;
    }
    static String getOperator(String operator){
        if(operator.contains("+"))
            return "+";
        else  if (operator.contains("-"))
            return "-";
        else if (operator.contains("*"))
            return "*";
        else if (operator.contains("/"))
            return "/";
        else
            return null;
    }

    static int calculator(int a, int b, String operator){
        if (operator.equals("+"))
            return a + b;
        else if (operator.equals("-"))
            return a - b;
        else if (operator.equals("*"))
            return a * b;
        else
            return a / b;
    }

}