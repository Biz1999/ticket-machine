package com.aleislan.ticket.machine.core;

import com.aleislan.ticket.machine.exception.PapelMoedaInvalidaException;
import com.aleislan.ticket.machine.exception.SaldoInsuficienteException;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Calebe de Paula Bianchini
 */
public class TicketMachine {

    private final int precoDoBilhete;
    private int saldo = 0;
    private final Integer[] papelMoeda = {2, 5, 10, 20, 50, 100};

    private Troco troco;

    private boolean imprimirFoiChamado = false;

    public TicketMachine(int precoDoBilhete) {
        this.precoDoBilhete = precoDoBilhete;
    }

    public void inserir(int quantia) throws PapelMoedaInvalidaException {
        boolean papelMoedaValida = Set.of(papelMoeda).contains(quantia);

        if (!papelMoedaValida)
            throw new PapelMoedaInvalidaException(quantia);

        this.saldo += quantia;
    }

    public int getSaldo() {
        return saldo;
    }

    public Iterator<PapelMoeda> getTroco() {
        if (!this.imprimirFoiChamado)
            return null;

        imprimirFoiChamado = false;

        this.saldo = 0;

        return Arrays.stream(troco.getPapeisMoeda()).iterator();
    }

    public String imprimir() throws SaldoInsuficienteException {
        if (saldo < precoDoBilhete)
            throw new SaldoInsuficienteException(this.saldo, this.precoDoBilhete);

        imprimirFoiChamado = saldo != precoDoBilhete;

        this.troco = new Troco(saldo - precoDoBilhete, papelMoeda);
        this.saldo = saldo - precoDoBilhete;

        return String.format(
                """
                *****************
                **** R$%d,00 ****
                *****************
                """, precoDoBilhete);
    }

    public Integer[] getPapelMoeda() {
        return papelMoeda;
    }
}
