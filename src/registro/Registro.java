package registro;


public class Registro {
    String consultaDate;
    Double valor;
    String moneda_base;
    String moneda_objetivo;
    Double ratio;
    Double valorConvertido;
    String lastUpdate;

    public Registro(String consultaDate, Double valor, String moneda_base, String moneda_objetivo, Double ratio, Double valorConvertido,String lastUpdate) {
        this.consultaDate = consultaDate;
        this.valor = valor;
        this.moneda_base = moneda_base;
        this.moneda_objetivo = moneda_objetivo;
        this.ratio = ratio;
        this.valorConvertido = (double)Math.round(valorConvertido * 100d) / 100d;
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "Fecha consulta = " + consultaDate + "\n" +
                "Importe = " + valor + " " + moneda_base +"\n" +
                "Ratio conversión = " + ratio + "\n" +
                "Última act. API = " + lastUpdate + "\n" +
                "Importe convertido = " + valorConvertido + " " + moneda_objetivo + "\n";

    }
}
