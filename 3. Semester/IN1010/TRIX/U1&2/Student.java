class Student {
    private String navn;
    private int quizScore;
    private int totalAntQuizzer;

    public Student(String n, int qS, int totQ) {
        navn = n;
        quizScore = qS;
        totalAntQuizzer = totQ;
    }
    public String hentNavn() {
      return navn;
    }
    public void leggTilQuizScore(int score) {
      quizScore += score;
      totalAntQuizzer += 1;
    }
    public int hentTotalScore() {
      return quizScore;
    }
    public int hentGjennomsnittligScore() {
      return quizScore/totalAntQuizzer;
    }
}
