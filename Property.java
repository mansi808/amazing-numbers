package numbers;

//buzz number ends with 7 or divisible by 7
//duck numbers contains zero (leading zeroes are meaningless)
//palindromics are same from lest to right
//gapful numbers are three digits or more numbers whose concatination of first and last digit is divisible by the orignal number
//spy numbers have equal sum of all digits and product of all digits
//square numbers are perfect squares eg 1 4 9 16
//sunny numbers are numbers whose n+1 = perfect square, where 'n' is the square number
//happy numbers  reaches 1 after a sequence during which the number is replaced by the sum of each digit squares.
//eg. 13 is a happy number, as sum of square of 1 and 3 --> 1 + 9 = 10 which leads to sum of square of 1 and 0 --> 12 + 02 = 1. eg 4 is not a happy number reaches 4 so the process goes on in an infinite cycle.
//sad numbers are not happy

import java.util.ArrayList;

public enum Property {
   BUZZ {
       @Override
       public boolean check(long naturalN) {
           return endsWith7(naturalN) || isDivisibleBy7(naturalN);
       }

       private boolean endsWith7(long naturalN) {
           return (String.valueOf(naturalN).endsWith("7"));
       }

       private boolean isDivisibleBy7(long naturalN) {
           if (naturalN > 70) {
               String numStr = String.valueOf(naturalN);
               long testNum = Long.valueOf(numStr.substring(0, numStr.length() - 1)) - (naturalN % 10 * 2);
               isDivisibleBy7(testNum);
           }
           return naturalN % 7 == 0;
       }

   },
    DUCK {
       @Override
        public boolean check(long naturalN) {
            String strN = String.valueOf(naturalN);
            return strN.replaceAll("^0+","").contains("0");  //replacing all leading zeroes`
        }
    },
    PALINDROMIC {
        @Override
        public boolean check(long naturalN) {
            String strN = String.valueOf(naturalN);
            String reverStrN = "";
            for (int i= strN.length()-1; i>=0 ; i--) {
                reverStrN += strN.charAt(i);
            }
            return strN.equals(reverStrN);
        }
    },
    GAPFUL {
        @Override
        public boolean check(long naturalN) {
            if (naturalN>99) { //three digit number
                String strN = String.valueOf(naturalN);
                String divideBy = strN.charAt(0)+""+strN.charAt(strN.length()-1); //this gave me sum if i removed "" and also an int
                return naturalN  % Integer.parseInt(divideBy)  ==0;
            } return false;
        }
    },
    SPY {
        @Override
        public boolean check(long naturalN) {
            int sum= 0;
            int product= 1;
            String[] strN =  String.valueOf(naturalN).split("");

            for (int i=0; i<strN.length; i++) {
                sum +=  Integer.parseInt(strN[i]);
                product  *= Integer.parseInt(strN[i]);
            }
            return sum==product;
        }

    },
    SQUARE {
        //isSquare() method also used for Sunny numbers
        @Override
        public boolean check(long naturalN) {
            double sqrt = Math.sqrt(naturalN); //this can give us non-zero decimal points
            long noDecimalSqrt = (long) sqrt; //this removes those points
            //this check if number with no decimal square root is equal to the orignal naturalNber +1
            return noDecimalSqrt*noDecimalSqrt == naturalN;
        }
    },
    SUNNY {
        @Override
        public boolean check(long naturalN) {
            return SQUARE.check(naturalN+1);
        }
    },
    EVEN {
        @Override
        public boolean check(long naturalN) {
            return naturalN % 2 == 0;
        }
    },
    ODD {
        @Override
        public boolean check(long naturalN) {
            return EVEN.check(naturalN+1);
        }
    },
    JUMPING {
        @Override
        public boolean check(long naturalN) {
            String[] strN = String.valueOf(naturalN).split("");
            int i=0;

            while (i <= strN.length-2) {
                if (Math.abs( Integer.parseInt(strN[i]) - Integer.parseInt(strN[i+1] ))!=1) return false;
                i++;
            } return true;
        }
    },
    HAPPY {
        @Override
        public boolean check(long naturalN) {
            String[] strN = String.valueOf(naturalN).split("");
            long N=0;
            for (int i=0; i<strN.length; i++) {
                N += Math.pow(Long.parseLong(strN[i]), 2);
            }
            HAPPY.checkedHappyNums.add(naturalN);
            if (N==1) {
                HAPPY.checkedHappyNums.clear();
                return true;
            } else if (HAPPY.checkedHappyNums.contains(N)) {
                HAPPY.checkedHappyNums.clear();
                return false;
            } return check(N);
        }
    },
    SAD {
        @Override
        public boolean check(long naturalN) {
            return !HAPPY.check(naturalN);
        }
    };

   public abstract boolean check(long naturalN);

   //contains all parameters for check() method of HAPPY enum
    //applies to happy enums only
   private ArrayList<Long> checkedHappyNums = new ArrayList<>();


}
