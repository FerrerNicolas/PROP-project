package model;

import java.util.Vector;

public class Code implements Cloneable {
    private Vector<Integer> code; // CHANGE TO INTEGER! SIMPLIFIES CODE ALL AROUND

    //Getter
    public Vector<Integer> getCode() {
        return code;
    }

    //Setter
    public void setCode(Vector<Integer> code) {
        this.code = code;
    }

    public Code() {
        // Default Constructor, need to make
        // Note: create attribute code to 0000
        for (int i = 0; i < 4; ++i) {
            this.code.add(0);
        }

    }

    public Code(Integer X) {
        // Note: create attribute code to X!
        for (int i = 0; i < 4; ++i) {
            this.code.add(X);
        }
    }

    /*
     Compares two codes and give the result of the solution in position correct colors
     and right color in wrong position.
    */
    public Correction correct(Code code2) {
        int white_pins, black_pins;
        white_pins = black_pins = 0;
        Vector<Integer> originalCode = this.code;
        Vector<Integer> codeToCompare = getCode();
        for (int i = 0; i < originalCode.size(); i++) {
            //Changed == to equals function
            if (originalCode.elementAt(i).equals(codeToCompare.elementAt(i))) {
                ++black_pins;
                originalCode.set(i, -1); //
                codeToCompare.set(i, -2);
            }
        }
        for (int i = 0; i < originalCode.size(); ++i) {
            if (!originalCode.elementAt(i).equals(-1)) {
                for (int j = 0; j < codeToCompare.size(); ++j) {
                    if (originalCode.elementAt(i).equals(codeToCompare.elementAt(j))) {
                        ++white_pins;
                        codeToCompare.set(j, -2); //codeToCompare[j] = -2
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
        try {
            this.code.set(Index, Color);
        } catch (ArrayIndexOutOfBoundsException e) {
            //What to do if exception???
        }

    }

    public Boolean hasRepetitions() {
        //Selfexplanatory, need to change
        for (int i = 0; i < this.code.size(); ++i) {
            int a = this.code.elementAt(i);
            for (int j = i; j < this.code.size(); ++j) {
                if (a == this.code.elementAt(j)) return true;
            }
        }
        return false;
    }

    public Boolean hasBlanks() {
        for (int i = 0; i < this.code.size(); ++i) {
            if (this.code.elementAt(i).equals(0)) return true;
        }
        return false;
    }

    public void setCode(Integer X) {
        for (int i = 0; i < this.code.size(); ++i) {
            this.code.set(i, X);
        }
    }

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
