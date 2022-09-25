package com.aleislan.ticket.machine.exception;

/**
 *
 * @author Calebe de Paula Bianchini
 */
public class PapelMoedaInvalidaException extends Exception {
    public PapelMoedaInvalidaException(int papelMoeda) {
        super(String.format("O papel moeda %d não é valido.", papelMoeda));
    }
}
