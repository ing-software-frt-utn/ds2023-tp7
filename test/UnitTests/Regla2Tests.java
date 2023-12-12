package UnitTests;

import dstp7.model.Regla2;
import dstp7.model.Venta;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Regla2Tests {
    /*/*Martes y miércoles: en compras superiores a $5000 y hasta $10000(inclusive)
     se aplica un 3% de descuento al total de la venta.
    En compras superiores a $10000 y hasta $250000 (inclusive), se aplica un 5%.
    */
    public Regla2Tests() {
        Repositorio.iniciar();
    }
    @Test
    public void llevandoMenosDe5000ElDescuentoEsCero(){
        //Datos
        Regla2 regla2 = new Regla2();
        Venta venta = new Venta();
        venta.agregarDetalle(Repositorio.getProductos()[0],2);
        //Ejecución
        double descuento = regla2.calcularDescuento(venta);
        //Comprobación
        assertEquals (0, descuento, 0);

    }

    @Test
    public void valorDeCompraSuperiorA5000YMenorOIgualA10000ElDescuentoEsTresPorCiento(){
        //Datos o definiciones
        Regla2 regla = new Regla2();
        Venta venta = new Venta();
        venta.agregarDetalle(Repositorio.getProductos()[0], 120);

        //Ejecución
        double descuento = regla.calcularDescuento(venta);

        //Comprobación
        assertEquals(180, descuento, 0);
    }

    @Test
    public void valorDeCompraSuperiorA10000YMenorOIgualA25000ElDescuentoEsCincoPorCiento(){
        //Datos o definiciones
        Regla2 regla = new Regla2();
        Venta venta = new Venta();
        venta.agregarDetalle(Repositorio.getProductos()[0], 300);

        //Ejecución
        double descuento = regla.calcularDescuento(venta);

        //Comprobación
        assertEquals(750, descuento, 0);
    }
    @Test
    public void valorDeCompraSuperiorA25000ElDescuentoEsSeisComaCincoPorCiento(){
        //Datos o definiciones
        Regla2 regla = new Regla2();
        Venta venta = new Venta();
        venta.agregarDetalle(Repositorio.getProductos()[0], 1000);

        //Ejecución
        double descuento = regla.calcularDescuento(venta);

        //Comprobación
        assertEquals(3250, descuento, 0);
    }
}
