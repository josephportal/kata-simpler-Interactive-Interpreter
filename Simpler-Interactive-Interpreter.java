package Simpler-Interactive-Interpreter;

import static org.junit.Assert.assertEquals;

public class Interpreter {

    public int input(String input) {
        
        String cadena = input.replace(" ", "");
        String[] parte = cadena.split("=>|[-+*/%=\\(\\)]|[A-Za-z_][A-Za-z0-9_]*|[0-9]*(\\\\.?[0-9]+)");
        int opr = 0, opr1 = 0, opr2 = 0;

        if (parte[0].equals("")) {
            opr = operadorPrecedence(cadena.substring(2, 3));
            parte[0] = String.valueOf(operacionArit(opr, parte, 1, 2));
            int a = operadorPrecedence(cadena.substring(5, 6));
            return operacionArit(a, parte, 0, 4);
        }

        if (parte.length <= 2) {
            opr = operadorPrecedence(cadena.substring(1, 2));
            return operacionArit(opr, parte, 0, 1);

        } else {
            opr1 = operadorPrecedence(cadena.substring(1, 2));
            opr2 = operadorPrecedence(cadena.substring(3, 4));

            if (opr1 % 2 == 0 & opr2 % 2 == 0) {
                parte[1] = String.valueOf(operacionArit(opr1, parte, 0, 1));
                return operacionArit(opr2, parte, 1, 2);
            }

            if (opr1 % 2 != 0 & opr2 % 2 != 0) {
                parte[1] = String.valueOf(operacionArit(opr1, parte, 0, 1));
                return operacionArit(opr2, parte, 1, 2);
            }

            if ((opr1 % 2 == 0 & opr2 % 2 == 0) == false) {
                if (opr1 > opr2) {
                    parte[1] = String.valueOf(operacionArit(opr2, parte, 1, 2));
                    return operacionArit(opr1, parte, 0, 1);
                } else {
                    parte[1] = String.valueOf(operacionArit(opr1, parte, 0, 1));
                    return operacionArit(opr2, parte, 1, 2);
                }
            }
        }

        return 0;
    }

    public int operacionArit(int op, String[] num, int i, int j) {
        switch (op) {
            case 1:
                return Integer.parseInt(num[i]) * Integer.parseInt(num[j]);
            case 2:
                return Integer.parseInt(num[i]) + Integer.parseInt(num[j]);
            case 3:
                return Integer.parseInt(num[i]) / Integer.parseInt(num[j]);
            case 4:
                return Integer.parseInt(num[i]) - Integer.parseInt(num[j]);
            case 5:
                return Integer.parseInt(num[i]) % Integer.parseInt(num[j]);
        }
        return 0;
    }

    public int operadorPrecedence(String op) {
        switch (op) {
            case "*":
                return 1;
            case "+":
                return 2;
            case "/":
                return 3;
            case "-":
                return 4;
            case "%":
                return 5;
        }
        return 0;
    }

    public static void main(String[] args) {
        Interpreter interpreter = new Interpreter();
        // Basic arithmetic
        assertEquals(7, interpreter.input("1 + 2 * 3"));
        assertEquals(11, interpreter.input("4 * 2 + 3"));
        assertEquals(1, interpreter.input("2 - 1"));
        assertEquals(6, interpreter.input("2 * 3"));
        assertEquals(2, interpreter.input("8 / 4"));
        assertEquals(3, interpreter.input("7 % 4"));
    }

}
