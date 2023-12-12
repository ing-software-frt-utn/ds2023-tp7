package dstp7.model;

public class Regla2 implements IRegla {
    private static final double porcentajeMenor = 0.03d;
    private static final double porcentajeIntermedio = 0.05d;
    private static final double porcentajeMayor = 0.065d;

    @Override
    public double calcularDescuento(Venta venta) {
        double total = venta.calcularTotal();

        if (total <= 5000) return 0d;

        if (total <= 10000)
            return total * porcentajeMenor;

        if (total <= 25000)
            return total * porcentajeIntermedio;

        return total * porcentajeMayor;
    }
}
