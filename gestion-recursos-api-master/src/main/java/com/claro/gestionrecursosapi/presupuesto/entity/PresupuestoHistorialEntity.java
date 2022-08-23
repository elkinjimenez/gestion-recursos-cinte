package com.claro.gestionrecursosapi.presupuesto.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "df_presupuesto_historial")
public class PresupuestoHistorialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqPresupuestoHistorial")
    @SequenceGenerator(name = "seqPresupuestoHistorial", sequenceName = "SEQ_DF_PRESUPUESTO_HISTORIAL", allocationSize = 1, initialValue = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "codpresupuesto")
    private PresupuestoEntity presupuesto;

    private String usuario;

    @Column(name = "elemento_pep")
    private String elementoPEP;

    @Column(name = "presupuesto_ussd_old")
    private BigDecimal presupuestoUSSDOld;

    @Column(name = "saldo_ussd_old")
    private BigDecimal saldoUSSDOld;

    @Column(name = "saldo_ussd_new")
    private BigDecimal saldoUSSDNew;

    @Column(name = "saldo_cop_old")
    private BigDecimal saldoCOPOld;

    @Column(name = "saldo_cop_new")
    private BigDecimal saldoCOPNew;

    @Column(name = "presupuesto_ussd_new")
    private BigDecimal presupuestoUSSDNew;

    @Column(name = "presupuesto_cop_old")
    private BigDecimal presupuestoCOPOld;

    @Column(name = "presupuesto_cop_new")
    private BigDecimal presupuestoCOPNew;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @Column(name = "tipo_registro")
    private String tipoRegistro;

    public PresupuestoHistorialEntity() {

    }

    public PresupuestoHistorialEntity(PresupuestoEntity presupuesto, String usuario, String elementoPEP, BigDecimal saldoUSSDOld, BigDecimal saldoUSSDNew, BigDecimal saldoCOPOld, BigDecimal saldoCOPNew, BigDecimal presupuestoUSSDOld, BigDecimal presupuestoUSSDNew, BigDecimal presupuestoCOPOld, BigDecimal presupuestoCOPNew, String tipoRegistro) {
        this.presupuesto = presupuesto;
        this.usuario = usuario;
        this.elementoPEP = elementoPEP;
        this.saldoUSSDOld = saldoUSSDOld;
        this.saldoUSSDNew = saldoUSSDNew;
        this.saldoCOPOld = saldoCOPOld;
        this.saldoCOPNew = saldoCOPNew;
        this.presupuestoUSSDOld = presupuestoUSSDOld;
        this.presupuestoUSSDNew = presupuestoUSSDNew;
        this.presupuestoCOPOld = presupuestoCOPOld;
        this.presupuestoCOPNew = presupuestoCOPNew;
        this.tipoRegistro = tipoRegistro;
    }

    @PrePersist
    public void prePersist() {
        this.fechaRegistro = new Date();
    }
}
