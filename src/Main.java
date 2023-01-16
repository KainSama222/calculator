import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        while (true) {
// объявление переменных
            String operation = null;
            int number1 = 0;
            int number2 = 0;
            boolean isNumb1Rim = true;
            boolean isNumb2Rim = true;

//  получение примера

            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите пример: ");
            String str = scanner.nextLine();

//  обработка примера
            str = str.trim();
            String[] strings = str.split(" ");
            if (strings.length < 3) {
                throw new NumberFormatException("Cтрока не является математической операцией");
            }
            if (strings.length > 3) {
                throw new NumberFormatException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор через пробелы");
            }
//  определение введеной системы счисления
            try {
                RimNumber.valueOf(strings[0]);
            } catch (IllegalArgumentException e) {
                isNumb1Rim = false;
            }
            try {
                RimNumber.valueOf(strings[2]);
            } catch (IllegalArgumentException e) {
                isNumb2Rim = false;
            }
//  обработка полученной строки с примером для дальнейшей работы метода calc
             if (isNumb1Rim & isNumb2Rim) {
                RimNumber RimNumber1 = RimNumber.valueOf(strings[0]);
                RimNumber RimNumber2 = RimNumber.valueOf(strings[2]);

                number1 = Integer.parseInt(RimNumber1.getNumb());
                number2 = Integer.parseInt(RimNumber2.getNumb());

                operation = strings[1];

            } else if (isNumb1Rim || isNumb2Rim) {
                throw new NumberFormatException ("Используются одновременно разные системы счисления");
            } else {
                number1 = Integer.parseInt(strings[0]);
                number2 = Integer.parseInt(strings[2]);

                operation = strings[1];

            }
//  получение и вывод результата примера из метода calc
            String result = calc(number1, number2, operation);

            if (isNumb1Rim & isNumb2Rim) {
                if (Integer.parseInt(result) < 0){
                    throw new NumberFormatException ("В римской системе нет отрицательных чисел.");
                }


                RimNumber.stream()
                        .filter(d -> d.getNumb().equals(result))
                        .forEach(System.out::println);
            } else {
                System.out.println("Ответ: " + result);
            }
        }
    }


    public static String calc(int number1, int number2, String operation) {
        int result = 0;
        if ((number1 < 0 || number1 > 10) || (number2 < 0 || number2 > 10)){
            throw new NumberFormatException ("Вы ввели числа не из диапазона 1-10");
        }

        switch (operation) {
            case "+":
                result = (number1 + number2);
                break;
            case "-":
                result = (number1 - number2);
                break;
            case "*":
                result = (number1 * number2);
                break;
            case "/":
                result = (number1 / number2);
                break;
            default:
                throw new IllegalArgumentException("Неверный знак.");
        }
        return Integer.toString(result);
    }
}
