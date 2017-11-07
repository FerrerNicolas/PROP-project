package mastermind;

public class code {
    private int[] Code;

    public int[] getCode() {
        return Code;
    }

    public void setCode(int[] code) {
        Code = code;
    }

    public void correct(code code2) {
        int white_pins, black_pins;
        white_pins = black_pins = 0;
        int[] OriginalCode = this.Code;
        int[] codeToCompare = getCode();
        for (int i = 0; i < OriginalCode.length; i++) {
            if (OriginalCode[i] == codeToCompare[i]) {
                ++black_pins;
                OriginalCode[i] = -1;
                codeToCompare[i] = -2;
            }
        }
        for (int i = 0; i < OriginalCode.length; ++i) {
            if (OriginalCode[i] != -1) {
                for (int j = 0; j < codeToCompare.length; ++j) {
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
