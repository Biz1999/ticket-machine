package com.aleislan.ticket.machine.exception;

/**
 *
 * @author Calebe de Paula Bianchini
 */
public class SaldoInsuficienteException extends Exception {

    public SaldoInsuficienteException(int saldo, int precoDoBilhete) {
        super(String.format("Saldo $%d insuficiente para bilhete de $%d", saldo, precoDoBilhete));
    }
}
