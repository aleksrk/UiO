import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;



class Stringtest {

    public static void main(String[] args) {
        String s = "*MAT1100";
        HashMap<String,ArrayList<String>> map = new HashMap<String,ArrayList<String>>();
        map.put(s, new ArrayList<String>());
        ArrayList<String> list = map.get("*MAT1100");
        list.add("hihi");
        list.add("hoho");
        map.put("*MAT1100",list);

    }
}
