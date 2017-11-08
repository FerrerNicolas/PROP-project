package model;

import java.util.Vector;


public class Code implements Cloneable {
    private Integer code; // CHANGE TO INTEGER! SIMPLIFIES CODE ALL AROUND

    //Getter
    public Integer getCode() {
        return code;
    }

    //Setter
    public void setCode(Integer code) {
        this.code = code;
    }

    public Code() {
        // Default Constructor, need to make
        // Note: create attribute code to 0000
        this.code = 0000;

    }

    public Code(Integer X) {
        // Note: create attribute code to X!
        this.code = X;
    }

    /*
     Compares two codes and give the result of the solution in position correct colors
     and right color in wrong position.
    */
    public Correction correct(Code code2) {
        int white_pins, black_pins;
        white_pins = black_pins = 0;
        Integer originalCode = this.code;
        Integer codeToCompare = code2.getCode();
        int[] originalCodeNumbers = {0,0,0,0};
        int[] codeToCompareNumbers = {0,0,0,0};
        for (int i = 0; i < 4; i++) {
                if (originalCode%10 == codeToCompare%10) {
                    ++black_pins;
                    originalCodeNumbers[i] = -1;
                    codeToCompareNumbers[i] = -2;
            }
            originalCodeNumbers[i] = originalCode%10;
            codeToCompareNumbers[i] = codeToCompare%10;
            originalCode = originalCode /10;
            codeToCompare = codeToCompare / 10;
        }
        for (int i = 0; i < 4; ++i) {
            if (originalCodeNumbers[i] != -1) {
                for (int j = 0; j < 4; ++j) {
                    if (originalCodeNumbers[i] == codeToCompareNumbers[j]) {
                        codeToCompareNumbers[j] = -2;
                        ++black_pins;
                        break;
                    }
                }
            }
        }
        return new Correction(white_pins, black_pins);
    }

    public Boolean isCompatible(Code c2, Correction correction) {
        Correction correctionC2 = this.correct(c2);
        return correctionC2.equals(correction);
    }

    //EXTRA
    // Individual color setter
    public void setColorAt(int Index, int Color) {
        //This function will put the color Color at position Index between 1 and 4
        //Color must be a digit between 0 and 6 included, else exception
        if (Color < 0 || Color > 6) {
            //throw new WrongColorException();
        }
        if (Index < 0 || Index > 4) throw new ArrayIndexOutOfBoundsException();
            //What to do if exception???
        int divider = (int)Math.pow(10, Index); // divisor = 10 ^ Index;
        int code_copy = this.code % divider;
        divider = divider /10;
        int colorAtIndex = code_copy / divider;
        int diff = Color - colorAtIndex;
        diff *= divider;
        this.code += diff;

    }

    public Boolean hasRepetitions() {
        //Selfexplanatory, need to change
        int codeCopy = this.code;
         Vector<Integer> numbers = new Vector<Integer>(0);
        for (int i = 0; i < 4; ++i) {
            int tmp = codeCopy%10;
            if (!numbers.isEmpty() && numbers.contains(tmp)) return true;
            numbers.add(tmp);
            codeCopy /= 10;
        }
        return false;
    }

    public Boolean hasBlanks() {
        int codeCopy = this.code;
        for (int i = 0; i < 4; ++i) {
            if (codeCopy %10 == 0) return true;
            codeCopy = codeCopy/10;
        }
        return false;
    }

    /*
    Do we still need this set code??

    public void setCode(Integer X) {
        this.code = X;
    }
    */
    public Code clone() { //NEED TO TEST FOR SURE
        Code code = null;
        try {
            code = (Code) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return code;
    }
}
