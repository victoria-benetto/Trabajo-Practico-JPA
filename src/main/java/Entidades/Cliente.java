package Entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="cliente")

public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "dni", unique = true)
    private int dni;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "fk_domicilio")
    private Domicilio domicilio;

   /* @OneToMany(mappedBy = "cliente")
    private List<Factura> facturas = new ArrayList<Factura>();
    */
}
