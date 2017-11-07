package model;

import java.util.Vector;

public class Code {
    private Vector<Integer> code;

    //Getter
    public Vector<Integer> getCode() {
        return code;
    }

    //Setter
    public void setCode(Vector<Integer> code) {
    	this.code = code;
    }
    
    /*
     Compares two codes and give the result of the solution in position correct colors
     and right color in wrong position.
    */
    public Correction correct(Code code2) {
        int white_pins, black_pins;
        white_pins = black_pins = 0;
        Vector<Integer> OriginalCode = this.code;
        Vector<Integer> codeToCompare = getCode();
        for (int i = 0; i < OriginalCode.size(); i++) {
            if (OriginalCode.elementAt(i) == codeToCompare.elementAt(i)) {
                ++black_pins;
                OriginalCode.elementAt(i) = -1;
                codeToCompare.elementAt(i) = -2;
            }
        }
        for (int i = 0; i < OriginalCode.size(); ++i) {
            if (OriginalCode[i] != -1) {
                for (int j = 0; j < codeToCompare.size(); ++j) {
                    if (OriginalCode[i] == codeToCompare[j]) {
                        ++white_pins;
                        codeToCompare[j] = -2;
                        break;
                    }
                }
            }
        }
        // Needs to return the white pins and black pins, in which order and how(??)
        // Do we need a correction class, if not we need to define the way in which
        // corrections are given. 
        // VICTORS RESPONSE: WE DO HAVE A CORRECTION CLASS, NOW UP AND RUNNING :)

    }

}
