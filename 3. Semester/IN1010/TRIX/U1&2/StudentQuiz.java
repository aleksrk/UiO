class StudentQuiz {

    public static void main (String[] args) {

      Student Joakim = new Student("Joakim", 180, 2);
      Student Kristin = new Student("Kristin", 160, 2);
      Student Dag = new Student("Dag", 140, 2);

      Joakim.leggTilQuizScore(35);
      Joakim.leggTilQuizScore(45);
      Kristin.leggTilQuizScore(95);
      Kristin.leggTilQuizScore(50);
      Dag.leggTilQuizScore(100);
      Dag.leggTilQuizScore(99);

      System.out.println("Dag: " + Dag.hentTotalScore() +
      " " + Dag.hentGjennomsnittligScore());
      System.out.println("Kristin: " + Kristin.hentTotalScore() +
      " " + Kristin.hentGjennomsnittligScore());
      System.out.println("Joakim: " + Joakim.hentTotalScore() +
      " " + Joakim.hentGjennomsnittligScore());
    }
}
