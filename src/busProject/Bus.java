package busProject;

import java.io.Serializable;

public class Bus implements Serializable {

    private int number; //버스번호
    private int time; //초단위

    public Bus(int number, int time) {
        this.number = number;
        this.time = time;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
