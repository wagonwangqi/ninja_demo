package mag.gaia.common.enums;

public enum Collects {
    NEWCOLLECT(1), OLDCOLLECT(2), HASCOLLECT(3);
    private int value;

    Collects(int num) {
        this.value = num;
    }
    public int toValue(){
        return  value;
    }
}
