package teamE.dashboard.entity;

public enum Age {
    TWENTIES(20), THIRTIES(30), FORTIES(40), FIFTIES(50), SIXTIES(60), OVER_SEVENTIES(70);

    private int ageNum;

    Age(int num) {
        ageNum = num;
    }

    public int getAgeNum() {
        return ageNum;
    }
}
