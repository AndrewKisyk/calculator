package com.example.a123.alculator;
public class Engine {
    private char operator = ' ';
    String str = new String();

    Engine(String s) {
        str = s;
    }

    /*
     * checking the tape for a sequence of execution, replacing operations with
     * the main priority for the finished result
     */
    private String compoutfirstpriority(String s) {
        int start = 0;
        StringBuffer buf = new StringBuffer(str);
        String substring = s;

        int end = substring.length();
        boolean tokennotfound = true;
        for (int j = 0; j < s.length() - 1; j++) {
            if (s.charAt(j) == '(') {
                start = j;
            }
        }
        for (int i = start; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                end = i;
                tokennotfound = false;
                break;
            } else
                tokennotfound = true;
        }

        substring = s.substring(start + 1, end);
        if (tokennotfound == false) {
            String temp = substring;
            substring = Float.toString(compoutill(substring));
            s = s.replace(s.charAt(start) + temp + s.charAt(end), substring).toString();
            return compoutfirstpriority(s);
        } else
            return s;
    }

    private String compoutsecondpriority(String str) throws Exception {
        String first = new String();
        String second = new String();
        int start = 0; // start of substring
        int end = 0; // end of substring
        int sign = 1;// plus value
        double res = 0;
        for (int j = 0; j < str.length(); j++) {
            // Eiler's number
            str = str.replaceAll("e", "2.71828");
            // Pi
            str = str.replaceAll("п", "3.1415926");
            // %
            str = str.replaceAll("%", "/100");
            // sin
            if (str.charAt(j) == 's' && str.charAt(j + 1) == 'i') {
                start = j;
                for (int i = j + 3; i != str.length() && (str.charAt(i) == '-' || isNumber(str.charAt(i))); i++) {
                    if (str.charAt(j + 3) == '-' && i == j + 3) {
                        sign = -1;
                        continue;
                    } else if (str.charAt(i) == '-') {
                        end = i - 1;
                        break;
                    }
                    second += str.charAt(i);
                    end = i;
                }
                // cos
            } else if (str.charAt(j) == 'c' && str.charAt(j + 1) == 'o' && str.charAt(j + 2) == 's') {
                start = j;
                for (int i = j + 3; i != str.length() && (str.charAt(i) == '-' || isNumber(str.charAt(i))); i++) {
                    if (str.charAt(j + 3) == '-' && i == j + 3) {
                        sign = -1;
                        continue;
                    } else if (str.charAt(i) == '-') {
                        end = i - 1;
                        break;
                    }
                    second += str.charAt(i);
                    end = i;
                }
                // tan
            } else if (str.charAt(j) == 't') {
                start = j;
                for (int i = j + 3; i != str.length() && (str.charAt(i) == '-' || isNumber(str.charAt(i))); i++) {
                    if (str.charAt(j + 3) == '-' && i == j + 3) {
                        sign = -1;
                        continue;
                    } else if (str.charAt(i) == '-') {
                        end = i - 1;
                        break;
                    }
                    second += str.charAt(i);
                    end = i;
                }
                // natural logarifm
            } else if (str.charAt(j) == 'l') {
                start = j;
                for (int i = j + 2; i != str.length() && (str.charAt(i) == '-' || isNumber(str.charAt(i))); i++) {
                    if (str.charAt(j + 2) == '-' && i == j + 2) {
                        sign = -1;
                        continue;
                    } else if (str.charAt(i) == '-') {
                        end = i - 1;
                        break;
                    }
                    second += str.charAt(i);
                    end = i;
                }
            }
            // square root
            else if (str.charAt(j) == '√') {
                start = j;
                for (int i = j + 1; i != str.length() && (str.charAt(i) == '-' || isNumber(str.charAt(i))); i++) {
                    if (str.charAt(j + 1) == '-' && i == j + 1) {
                        sign = -1;
                        continue;
                    } else if (str.charAt(i) == '-') {
                        end = i - 1;
                        break;
                    }
                    end = i;
                }
            }

            if (str.charAt(j) == 's' || str.charAt(j) == 'c' || str.charAt(j) == 't' || str.charAt(j) == 'l' || str.charAt(j) == '√') {
                for (int y = j + 1; y != str.length() && (isNumber(str.charAt(y)) || str.charAt(y) == '-'); y++) {
                    if (str.charAt(j + 1) == '-' && y == j + 3) {
                        sign = -1;
                        continue;
                    } else if (str.charAt(y) == '-') {
                        end = y - 1;
                        break;
                    }
                    second += str.charAt(y);
                    end = y;
                }

                StringBuffer buff = new StringBuffer(str);
                if (str.charAt(j) == 's' & str.charAt(j + 1) == 'i') {
                    res = sign * Math.sin(Double.parseDouble(second));
                } else if (str.charAt(j) == 'c') {
                    res = sign * Math.cos(Double.parseDouble(second));
                } else if (str.charAt(j) == 't') {
                    res = sign * Math.tan(Double.parseDouble(second));
                } else if (str.charAt(j) == 'l') {
                    if (sign * Double.parseDouble(second) <= 0) {
                        second = "1";
                        throw new ArithmeticException();
                    } else if (Double.parseDouble(second) == 2.71828) {
                        res = 1;
                    } else
                        res = Math.log(Double.parseDouble(second));
                    // System.out.println("Помилка");
                } else if (str.charAt(j) == '√') {
                    res = Math.sqrt(Double.parseDouble(second));
                }
                second = "";
                buff.replace(start, end + 1, Double.toString(res));
                sign = 1;
                str = buff.toString();
                System.out.println(str);
            }
        }
        for (int j = 0; j < str.length(); j++) {
            if (str.charAt(j) == 'x' || str.charAt(j) == '/' || str.charAt(j) == '^') {
                // record first multiplier
                for (int q = j - 1; q >= 0 && (isNumber(str.charAt(q)) || str.charAt(q) == '-'); q--) {
                    try {
                        if (str.charAt(q) == '-' && !isNumber(str.charAt(q - 1))) {
                            sign = -1;
                            start = q;
                            break;
                        } else if (str.charAt(q) == '-' && isNumber(str.charAt(q - 1))) {
                            start = q + 1;
                            break;
                        }
                    } catch (StringIndexOutOfBoundsException e) {
                    }
                    first += str.charAt(q);
                    start = q;
                }
                // reverce first number
                StringBuffer temp = new StringBuffer(first);
                temp.reverse();
                first = temp.toString();
                float f = Float.parseFloat(first) * sign;
                first = "";
                sign = 1;
                // record second multiplier
                for (int y = j + 1; y != str.length() && (isNumber(str.charAt(y)) || str.charAt(y) == '-'); y++) {
                    if (str.charAt(j + 1) == '-' && y == j + 1) {
                        sign = -1;
                        continue;
                    } else if (str.charAt(y) == '-') {
                        end = y - 1;
                        break;
                    }
                    second += str.charAt(y);
                    end = y;
                }
                float s = Float.parseFloat(second) * sign;
                second = "";
                StringBuffer buff = new StringBuffer(str);
                if (str.charAt(j) == 'x') {
                    res = f * s;
                    buff.replace(start, end + 1, Double.toString(res));
                } else if (str.charAt(j) == '/') {
                    if (s == 0) {
                        s = 1;
                        throw new ArithmeticException();
                    }
                    res = f / s;
                    buff.replace(start, end + 1, Double.toString(res));
                } else if (str.charAt(j) == '^') {
                    res = Math.pow(f, s);
                    buff.replace(start, end + 1, Double.toString(res));
                }
                str = buff.toString();
            }
        }
        return str;
    }

    public String compout(String str) {
        str = compoutfirstpriority(str);
        try {
            str = compoutsecondpriority(str);
            char operator = ' ';
            float num = 0;
            float result = 0;
            int sign = 1;
            String inDigit = "0";
            // record the previous number
            for (int i = 0; i < str.length(); i++) {
                if (isNumber(str.charAt(i))) {
                    inDigit += str.charAt(i);
                } else if (str.charAt(i) == '-' && i == 0) {
                    sign = -1;
                } else {
                    operator = str.charAt(i);
                    num += (sign * Float.parseFloat(inDigit));
                    inDigit = "0";
                }
                // compout
                if (operator == '+') {
                    result = num + Float.parseFloat(inDigit);
                } else if (operator == '-') {
                    sign = -1;
                    result = num + (sign * Float.parseFloat(inDigit));
                } else {
                    result = num + (sign * Float.parseFloat(inDigit));
                }
            }
            return Float.toString(result);
        } catch (Exception e) {
            return "Помилка";
        }
    }

    private float compoutill(String str) {
        try {
            str = compoutsecondpriority(str);
        } catch (Exception e) {
            System.out.println("error");
        }
        char operator = ' ';
        float num = 0;
        float result = 0;
        int sign = 1;
        String inDigit = "0";
        // record the previous number
        for (int i = 0; i < str.length(); i++) {
            if (isNumber(str.charAt(i))) {
                inDigit += str.charAt(i);
            } else if (str.charAt(i) == '-' && i == 0) {
                sign = -1;
            } else {
                operator = str.charAt(i);
                num += (sign * Float.parseFloat(inDigit));
                inDigit = "0";
            }
            // compout
            if (operator == '+') {
                result = num + Float.parseFloat(inDigit);
            } else if (operator == '-') {
                sign = -1;
                result = num + (sign * Float.parseFloat(inDigit));
            } else if (operator == 'x') {
                result = num * Float.parseFloat(inDigit);
            } else if (operator == '/') {
                result = num / Float.parseFloat(inDigit);
            } else {
                result = num + (sign * Float.parseFloat(inDigit));
            }
        }
        return result;
    }

    private static boolean isNumber(char c) {
        boolean trust = false;
        switch (c) {
            case '0':
                trust = true;
                break;
            case '1':
                trust = true;
                break;
            case '2':
                trust = true;
                break;
            case '3':
                trust = true;
                break;
            case '4':
                trust = true;
                break;
            case '5':
                trust = true;
                break;
            case '6':
                trust = true;
                break;
            case '7':
                trust = true;
                break;
            case '8':
                trust = true;
                break;
            case '9':
                trust = true;
                break;
            case '.':
                trust = true;
                break;
        }
        return trust;
    }

}
