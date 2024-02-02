import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;

class StudentSystem {

    private HashMap<String,ArrayList<String>> fagOversikt = new HashMap<String,ArrayList<String>>();

    public void lesFraFil(String file) throws FileNotFoundException {

        File fil = new File(file);
        Scanner les = new Scanner(fil);
        String fag = "";


        while (les.hasNextLine()) {
            String linje = les.nextLine();

            if (linje == "") {
                System.out.println("Det er tomme linjer i filen din!");
                break;
            }

            if (linje.charAt(0) == '*') {
                fag = linje;
                fag = fag.substring(1);
                fagOversikt.put(fag, new ArrayList<String>());

            } else {

                fagOversikt.get(fag).add(linje);

            }

        }

        fagOversikt.put("Ingen fag", new ArrayList<String>());

    }

    public String flestStudenter() {
        String storsteFag = "";
        int antallStudenter = 0;

        for (HashMap.Entry<String, ArrayList<String>> list : fagOversikt.entrySet()) {
            int antall = list.getValue().size();

            if (antallStudenter < antall) {
                storsteFag = list.getKey();
                antallStudenter = antall;
            }
        }
        return storsteFag;
    }

    public ArrayList<String> studentMedFlestFag() {

        ArrayList<String> studenter = new ArrayList<String>();
        ArrayList<String> totaleAntStudenter = new ArrayList<String>();
        HashMap <String, Integer> map = new HashMap<String, Integer>();

        for (ArrayList<String> list : fagOversikt.values()) {
            totaleAntStudenter.addAll(list);
        }

        for (String s : totaleAntStudenter) {
            int occurrences = Collections.frequency(totaleAntStudenter, s);
            map.put(s, occurrences);
        }

        int storsteAntall = Collections.max(map.values());

        for (HashMap.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue()==storsteAntall) {
                studenter.add(entry.getKey());
            }

        }
        return studenter;
    }

    public void studenterPerFag(String fag) {

        System.out.println(fagOversikt.get(fag));
    }

    public void fagEnStudentTar(String student) {

        ArrayList<String> fag = new ArrayList<String>();

        for (HashMap.Entry<String, ArrayList<String>> entry : fagOversikt.entrySet()) {
            if (entry.getValue().contains(student)) {
                fag.add(entry.getKey());
            }
        }
        System.out.println(fag);

    }

    public void leggFagTilStudent(String student, String fag) {

        ArrayList<String> liste = fagOversikt.get(fag);
        liste.add(student);
        fagOversikt.put(fag, liste);

    }

    public void fjernFraFag(String student, String fag) {

        ArrayList<String> liste = fagOversikt.get(fag);
        liste.remove(student);
        fagOversikt.put(fag, liste);

    }

    public void leggTilStudent(String student) {

        ArrayList<String> liste = fagOversikt.get("Ingen fag");
        liste.add(student);
        fagOversikt.put("Ingen fag", liste);

    }

    public void leggTilFag(String fag) {

        fagOversikt.put(fag, new ArrayList<String>());

    }


}

class TestStudentSystem {

    public static void main(String[] args) throws FileNotFoundException {
        StudentSystem syst = new StudentSystem();
        syst.lesFraFil("emnestudenter.txt");

        System.out.println(syst.flestStudenter());
        System.out.println(syst.studentMedFlestFag());
        syst.studenterPerFag("MAT1100");
        syst.fagEnStudentTar("Elsie");
    }

}
