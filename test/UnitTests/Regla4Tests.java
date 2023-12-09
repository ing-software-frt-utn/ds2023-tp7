package UnitTests;

import dstp7.model.Regla4;
import dstp7.model.Venta;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Regla4Tests {
    private static final int PRODUCTO_PRECIO_3751 = 7;
    private static final int PRODUCTO_PRECIO_5001 = 8;
    private static final int PRODUCTO_PRECIO_3750 = 9;
    private static final int PRODUCTO_PRECIO_5000 = 10;
    private static final int PRODUCTO_PRECIO_1875 = 11;
    private static final int PRODUCTO_PRECIO_2500 = 12;

    public Regla4Tests() {
        Repositorio.iniciar();
    }

    private static Stream<Arguments> proveedorCasoDePrueba2() {
        return Stream.of(
                arguments(new DtoProductosCantidades(PRODUCTO_PRECIO_5000, 3)),
                arguments(new DtoProductosCantidades(0, 3)),
                arguments(new DtoProductosCantidades(PRODUCTO_PRECIO_5001, 3)),
                arguments(new DtoProductosCantidades(PRODUCTO_PRECIO_3750, 4)),
                arguments(new DtoProductosCantidades(0, 3))
        );
    }

    private static Stream<Arguments> proveedorCasoDePrueba3() {
        return Stream.of(
                arguments(
                        Arrays.asList(
                                new DtoProductosCantidades(PRODUCTO_PRECIO_3750, 4),
                                new DtoProductosCantidades(0, 3)
                        )
                ),
                arguments(
                        Arrays.asList(
                                new DtoProductosCantidades(0, 3),
                                new DtoProductosCantidades(PRODUCTO_PRECIO_3750, 4)
                        )
                )
        );
    }

    private static Stream<Arguments> proveedorCasoDePrueba4() {
        return Stream.of(
                arguments(
                        Arrays.asList(
                                new DtoProductosCantidades(PRODUCTO_PRECIO_3750, 4),
                                new DtoProductosCantidades(PRODUCTO_PRECIO_5000, 4)
                        )
                )
        );
    }

    private static Stream<Arguments> proveedorCasoDePrueba5() {
        return Stream.of(
                arguments(
                        Arrays.asList(
                                new DtoProductosCantidades(PRODUCTO_PRECIO_1875, 4),
                                new DtoProductosCantidades(PRODUCTO_PRECIO_1875, 4)
                        )
                ),
                arguments(
                        Arrays.asList(
                                new DtoProductosCantidades(0, 4),
                                new DtoProductosCantidades(1, 4)
                        )
                ),
                arguments(
                        Arrays.asList(
                                new DtoProductosCantidades(PRODUCTO_PRECIO_2500, 3),
                                new DtoProductosCantidades(PRODUCTO_PRECIO_2500, 3)
                        )
                ),
                arguments(
                        Arrays.asList(
                                new DtoProductosCantidades(PRODUCTO_PRECIO_3750, 3),
                                new DtoProductosCantidades(PRODUCTO_PRECIO_5000, 3)
                        )
                ),
                arguments(
                        Arrays.asList(
                                new DtoProductosCantidades(0, 3),
                                new DtoProductosCantidades(1, 3)
                        )
                )
        );
    }

    @Test
    public void siDescuentoEsAplicableAUnaLineaDeVentaElDescuentoEs10Porciento() {
        Regla4 regla = new Regla4();
        Venta venta = new Venta();
        venta.agregarDetalle(
                Repositorio.getProductos()[PRODUCTO_PRECIO_3751], 4);

        double descuento = regla.calcularDescuento(venta);

        assertEquals(1500.4, descuento, 0);
    }

    @ParameterizedTest
    @MethodSource("proveedorCasoDePrueba2")
    public void SiDescuentoNoEsAplicableAUnaLineaDeVentaElDescuentoEs0(DtoProductosCantidades dto) {
        Regla4 regla = new Regla4();
        Venta venta = new Venta();
        venta.agregarDetalle(Repositorio.getProductos()[dto.getIndiceProducto()], dto.getCantidad());

        double descuento = regla.calcularDescuento(venta);

        assertEquals(0, descuento, 0);
    }

    @ParameterizedTest
    @MethodSource("proveedorCasoDePrueba3")
    public void SiDescuentoEsAplicableAAlgunasLineasDeVentaElDescuentoEs10Porciento(List<DtoProductosCantidades> productosCantidades) {
        Regla4 regla = new Regla4();
        Venta venta = new Venta();
        for (DtoProductosCantidades dto : productosCantidades) {
            venta.agregarDetalle(Repositorio.getProductos()[dto.getIndiceProducto()], dto.getCantidad());
        }

        double descuento = regla.calcularDescuento(venta);

        assertEquals(1500, descuento, 0);
    }

    @ParameterizedTest
    @MethodSource("proveedorCasoDePrueba4")
    public void SiDescuentoEsAplicableATodasLineasDeVentaElDescuentoEs10Porciento(List<DtoProductosCantidades> productosCantidades) {
        Regla4 regla = new Regla4();
        Venta venta = new Venta();
        for (DtoProductosCantidades dto : productosCantidades) {
            venta.agregarDetalle(Repositorio.getProductos()[dto.getIndiceProducto()], dto.getCantidad());
        }

        double descuento = regla.calcularDescuento(venta);

        assertEquals(3500, descuento, 0);
    }

    @ParameterizedTest
    @MethodSource("proveedorCasoDePrueba5")
    public void SiDescuentoNoEsAplicableANingunaLineaDeVentaElDescuentoEs0(List<DtoProductosCantidades> productosCantidades) {
        Regla4 regla = new Regla4();
        Venta venta = new Venta();
        for (DtoProductosCantidades dto : productosCantidades) {
            venta.agregarDetalle(Repositorio.getProductos()[dto.getIndiceProducto()], dto.getCantidad());
        }

        double descuento = regla.calcularDescuento(venta);

        assertEquals(0, descuento, 0);
    }
}
