public class Tab{
    public String tabToSpace(String s){
        int n = 0;
        StringBuilder finalString = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                n++
            } else if (c == '\t') {
                int spaces = 4 - (n % 4);
                for (int i = 0; i < spaces; i++) {
                    finalString.append(' ');
                }
                n += spaces;
            } else {
                finalString.append(c);
            }
        }
        
        return finalString.toString();
    }
}