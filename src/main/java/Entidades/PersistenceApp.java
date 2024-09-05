package Entidades;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;



public class PersistenceApp {
    public static void main(String[] args){
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("Entidades");
        EntityManager em= emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Factura factura1 = new Factura();
            factura1.setNumero(12);
            factura1.setFecha("10/08/2020");

           Cliente cliente = Cliente.builder()
                    .nombre("victoria")
                    .apellido("benetto")
                    .dni(44122509)
                    .build();

           Domicilio domicilio = Domicilio.builder()
                    .nombreCalle("Mariano Moreno")
                    .numero(773)
                    .build();

           cliente.setDomicilio(domicilio);
           domicilio.setCliente(cliente);

           factura1.setCliente(cliente);

           Categoria perecederos = Categoria.builder()
                   .denominacion("perecederos")
                   .build();
           Categoria lacteos = Categoria.builder()
                   .denominacion("lacteos")
                   .build();
           Categoria limpieza = Categoria.builder()
                   .denominacion("limpieza")
                   .build();


           Articulo art1 = Articulo.builder()
                   .cantidad(200)
                   .denominacion("Yogurt Ser sabor frutilla")
                   .precio(20)
                   .build();
           Articulo art2 = Articulo.builder()
                   .cantidad(300)
                   .denominacion("Detergente Magistral")
                   .precio(80)
                   .build();

           art1.getCategorias().add(perecederos);
           art1.getCategorias().add(lacteos);
           lacteos.getArticulos().add(art1);
           perecederos.getArticulos().add(art1);

           art2.getCategorias().add(limpieza);
           limpieza.getArticulos().add(art2);


           DetalleFactura det1 = new DetalleFactura();
           det1.setArticulo(art1);
           det1.setCantidad(2);
           det1.setSubtotal(40);


           art1.getDetalleFacturas().add(det1);
           factura1.getDetalles().add(det1);
           det1.setFactura(factura1);


           DetalleFactura det2 = new DetalleFactura();
           det2.setArticulo(art2);
           det2.setCantidad(1);
           det2.setSubtotal(80);


           art2.getDetalleFacturas().add(det2);
           factura1.getDetalles().add(det2);
           det2.setFactura(factura1);

           factura1.setTotal(120);

           em.persist(factura1);

           em.flush();

           em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        em.close();
        emf.close();
    }
}
