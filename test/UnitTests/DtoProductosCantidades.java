package UnitTests;

import dstp7.model.Producto;

public class DtoProductosCantidades {
    private int indiceProducto;
    private int cantidad;

    public DtoProductosCantidades(int indiceProducto, int cantidad) {
        this.indiceProducto = indiceProducto;
        this.cantidad = cantidad;
    }

    public int getIndiceProducto() {
        return indiceProducto;
    }

    public int getCantidad() {
        return cantidad;
    }
}
