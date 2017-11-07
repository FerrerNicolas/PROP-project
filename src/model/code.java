package model;

import java.util.Vector;

public class code {
    private Vector Code ;

    //Getter
    public Vector getCode() {
        return Code;
    }

    //Setter
    public void setCode(Vector code) {
        Code = code;
    }
    
    /*
     Compares two codes and give the result of the solution in position correct colors
     and right color in wrong position.
    */
    public void correct(code code2) {
        int white_pins, black_pins;
        white_pins = black_pins = 0;
        Vector OriginalCode = this.Code;
        Vector codeToCompare = getCode();
        for (int i = 0; i < OriginalCode.size(); i++) {
            if (OriginalCode[i] == codeToCompare[i]) {
                ++black_pins;
                OriginalCode[i] = -1;
                codeToCompare[i] = -2;
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

    }

}
