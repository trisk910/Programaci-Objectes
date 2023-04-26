public class Taula {
    private String ID;
    private int numCadires;
    private boolean isTerrasa;
    private int numPersones = 0;
    private boolean isReservat = false;

    public Taula(String ID, int numCadires, boolean isTerrasa) {
        this.ID = ID;
        this.numCadires = numCadires;
        this.isTerrasa = isTerrasa;
    }

    public static Taula parseTaula(String reserva) {
        String id = null;
        String cadires = "";
        String[] parts = reserva.split(":");
        id = parts[0];
        String cadT = parts[1];
        boolean isTerrasa = false;
        int cadiresT = 0;
        if (reserva.contains("-T")) {
            isTerrasa = true;
            String[] parts2 = cadT.split("-");
            cadiresT = Integer.parseInt(parts2[0]);
        }else{
            cadiresT = Integer.parseInt(parts[1]);
        }
        return new Taula(id, cadiresT, isTerrasa);
    }

    public boolean isTerrasa() {
        return isTerrasa;
    }

    public String getID() {
        return ID;
    }

    public int getNumCadires() {
        return numCadires;
    }

    public int getNumPersones() {
        return numPersones;
    }

    public void setNumPersones(int numPersones) {
        this.numPersones = numPersones;
    }

    public boolean isReservat() {
        return isReservat;
    }

    public void setReservat(boolean reservat) {
        isReservat = reservat;
    }
}
