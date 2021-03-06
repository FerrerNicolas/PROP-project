package domini.Board;

import domini.Correction.Correction;
import exceptions.BadlyFormedCode;
import exceptions.InvalidNumberOfPins;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

//import domini.*;

// Javi
public class Code implements Cloneable {
    private Vector<Integer> code; // CHANGE TO INTEGER! SIMPLIFIES CODE ALL AROUND

    //Getter
    public Integer getCode() {
        int codeNum = 0;
        for (int i = 0; i < code.size(); ++i) {
            codeNum = (codeNum * 10) + this.code.get(i);
        }
        return codeNum;
    }

    public Integer getCodeSize() {
        return code.size();
    }

    public Vector<Integer> getCodeArray() {
        return this.code;
    }

    //We populate the code recursively so the last element of the array is the least valuable number of the given code
    //if there is an error the function returns -1, if everything worked returns a 0
    private int populateCode(Integer code) {
        int previousError = 0;
        if (code/10 != 0) previousError = populateCode(code/10);
        if (code%10 < 0 || code%10 > 6) return -1;
        else this.code.add(code%10);
        return previousError;
    }
    //Populates the code of a given size, if the code is invalid returns -1, else returns a 0
    private int populateSizedCode(Integer code, int size) {
        if (size == 1) {
            if (code < 0 || code > 6) return -1;
            this.code.add(code);
            return 0;
        }
        else {
            int previousError = populateSizedCode(code/10, size-1);
            if (code%10 < 0 && code%10 > 6) return -1;
            this.code.add(code%10);
            return previousError;
        }
    }

    //Setter
    public void setCode(Integer code) throws BadlyFormedCode { // CHANGED FOR EXCEPTIONS
        //if(code > 7000 || code < 0) throw new BadlyFormedCode();
        //if(code%1000 >= 700 || code%100 >= 70 || code%10 >= 7) throw new BadlyFormedCode();
        Vector<Integer> previousCode = (Vector<Integer>)this.code.clone();
        this.code = new Vector<>();
        int errorCode = populateCode(code);
        if (errorCode < 0) {
            this.code = previousCode;
            throw new BadlyFormedCode();
        }
    }

    //Populates the code of a given size, must be used when a code starts with blanks.
    public void setCode(Integer code, int size) throws BadlyFormedCode {
        Vector<Integer> previousCode = (Vector<Integer>)this.code.clone();
        this.code = new Vector<>();
        int errorCode = populateSizedCode(code, size);
        if (errorCode < 0) {
            this.code = previousCode;
            throw new BadlyFormedCode();
        }
    }

    public void setCode(Vector<Integer> code) throws BadlyFormedCode {
        for (int i = 0; i < code.size(); ++i) {
            if (code.get(i) < 0 || code.get(i) > 6) throw new BadlyFormedCode();
        }
        this.code = code;

    }

    public Code() {
        // Default Constructor, need to make
        // Note: create attribute code to 0000
        this.code = new Vector<Integer>(Arrays.asList(0,0,0,0));

    }

    public Code(Integer code) throws BadlyFormedCode {
        // Note: create attribute code to X!
        this.code = new Vector<Integer>();
        populateCode(code);
    }

    public Code(Integer code, int size) throws BadlyFormedCode {
        this.code = new Vector<Integer>();
        int errorCode = populateSizedCode(code, size);
        if (errorCode < 0) throw new BadlyFormedCode();
    }

    /*
     Compares two codes and give the result of the solution in position correct colors
     and right color in wrong position.
    */
    public Correction correct(Code code2) {
        int white_pins, black_pins;
        white_pins = black_pins = 0;
        Vector<Integer> OriginalCode = (Vector<Integer>) this.code.clone();
        Vector<Integer> codeToCompare = (Vector<Integer>) code2.getCodeArray().clone();
        for (int i = 0; i < OriginalCode.size(); i++) {
            if (OriginalCode.elementAt(i).equals(codeToCompare.elementAt(i))) {
                ++black_pins;
                OriginalCode.set(i, -1);
                codeToCompare.set(i, -2);
            }
        }
        for (int i = 0; i < OriginalCode.size(); ++i) {
            if (OriginalCode.elementAt(i) != -1) {
                for (int j = 0; j < codeToCompare.size(); ++j) {
                    if (OriginalCode.elementAt(i).equals(codeToCompare.elementAt(j))) {
                        ++white_pins;
                        codeToCompare.set(j, -2);
                        break;
                    }
                }
            }
        }
        Correction corr = new Correction();
        try {
            corr = new Correction(white_pins, black_pins);
        } catch (InvalidNumberOfPins e) {
            System.out.println("This shouldn't happen but the Code.correct created an invalid Correction");
        }
        return corr;
    }

    public boolean equals(Object object) {
        boolean same = false;
        if (object != null && object instanceof Code) {
            same = this.code.equals(((Code)object).getCodeArray());
        }
        return same;
    }

    public Boolean isCompatible(Code c2, Correction correction) {
        Correction correctionC2 = this.correct(c2);
        return correctionC2.equals(correction);
    }

    //EXTRA
    // Individual color setter
    public void setColorAt(int Index, int Color) throws BadlyFormedCode, ArrayIndexOutOfBoundsException { // CHANGED FOR EXCEPTIONS
        //This function will put the color Color at position Index between 1 and 4
        //Color must be a digit between 0 and 6 included, else exception
        if (Color < 0 || Color > 6) {
            //throw new WrongColorException();
            throw new BadlyFormedCode();
        }
        if (Index < 1 || Index > this.code.size()) throw new ArrayIndexOutOfBoundsException();

        this.code.set(Index-1, Color);
    }

    public Boolean hasRepetitions() {
        //Selfexplanatory, need to change
        ArrayList<Integer> codeNumbers = new ArrayList<>();
        codeNumbers.add(this.code.get(0));
        for (int i = 1; i < this.code.size(); ++i) {
            if (codeNumbers.contains(this.code.get(i))) return true;
            else codeNumbers.add(this.code.get(i));
        }
        return false;
    }

    public Boolean hasBlanks() {
        for (int i = 0; i < this.code.size(); ++i) {
            if (this.code.get(i) == 0) return true;
        }
        return false;
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

    //VICTOR:
    public String toString() {
        String s = "";
        for (int i = 0; i < this.code.size(); i++) {
            s += this.code.elementAt(i);
        }
        return s;
    }

}