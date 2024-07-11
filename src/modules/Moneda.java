package modules;

public class Moneda {
    String nombreCodigo;
    Double ratio;
    String pais;
    String lastUpdate;


    public Moneda(String nombreCodigo, String pais) {
        this.nombreCodigo = nombreCodigo;
        this.pais = pais;
        this.lastUpdate = new String();
    }

    public void setRatio(MonedaExchange miMonedaEx) {
        this.ratio = miMonedaEx.conversion_rate();
    }

    public Double getRatio(){
        return this.ratio;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(MonedaExchange miMonedaEx) {
        this.lastUpdate = miMonedaEx.time_last_update_utc();
    }

    public String getNombreCodigo() {
        return nombreCodigo;
    }

    public String getPais() {
        return pais;
    }

    public Double calcularConversion(Double valor) {
        return valor * this.ratio;
    }
}
