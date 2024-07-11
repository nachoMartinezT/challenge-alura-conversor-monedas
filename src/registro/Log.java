package registro;

import java.util.ArrayList;

public class Log {
    ArrayList<Registro> misRegistros = new ArrayList<Registro>();

    public void agregarRegistro(Registro reg){
        this.misRegistros.add(reg);
    }

    @Override
    public String toString() {
        String ret = new String();
        ret = "\n\n-------- RESUMEN DE CONSULTAS --------\n\n";
        for (int i = 0; i < misRegistros.size(); i++) {
            int j = i+1;
            ret += i+1 + ") ---- Consulta nº " + j + " ----" + "\n" + misRegistros.get(i) + "\n\n";
        }

        return ret;
    }

    public ArrayList<Registro> getRegistros() {
        return misRegistros;
    }
}
