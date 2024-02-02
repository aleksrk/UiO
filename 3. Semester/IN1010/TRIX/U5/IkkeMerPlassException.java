class IkkeMerPlassException extends Exception {

    public IkkeMerPlassException(String id) {
        super("Det var ikke plass til aa sette inn " + id);
    }
}

class DuplikatException extends Exception {

    public DuplikatException (String id) {
        super("Duplikat " + id);
    }
}
