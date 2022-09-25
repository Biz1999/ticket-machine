package com.aleislan.ticket.machine.core;

import com.aleislan.ticket.machine.exception.PapelMoedaInvalidaException;
import com.aleislan.ticket.machine.exception.SaldoInsuficienteException;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class TicketMachineTest {

    private int precoDoBilhete = 15;

    private TicketMachine ticketMachine = new TicketMachine(precoDoBilhete);


    @Test
    void Should_Insert_A_Valid_Bill_And_Print_Ticket_Successfully() throws PapelMoedaInvalidaException, SaldoInsuficienteException {
        int quantia = 20;

        ticketMachine.inserir(quantia);
        String ticket = ticketMachine.imprimir();

        assertEquals(String.format(
                """
                *****************
                **** R$%d,00 ****
                *****************
                """, precoDoBilhete), ticket);
        assertEquals(quantia - precoDoBilhete, ticketMachine.getSaldo());
    }

    @Test
    void Should_Return_Change_And_0_Balance_After_Print_And_Get_Change() throws PapelMoedaInvalidaException, SaldoInsuficienteException{
        int quantia1 = 50;
        int quantia2 = 20;
        int quantia3 = 20;
        int quantia4 = 10;

        ticketMachine.inserir(quantia1);
        ticketMachine.inserir(quantia2);
        ticketMachine.inserir(quantia3);
        ticketMachine.inserir(quantia4);

        ticketMachine.imprimir();

        int saldoAntesDoTroco = ticketMachine.getSaldo();
        Iterator<PapelMoeda> troco = ticketMachine.getTroco();


        assertEquals(quantia1 + quantia2 + quantia3 + quantia4, saldoAntesDoTroco + precoDoBilhete);
        assertNotEquals(1, troco.next().quantidade);
        assertEquals(1, troco.next().quantidade);
        assertEquals(1, troco.next().quantidade);
        assertEquals(1, troco.next().quantidade);
        assertEquals(1, troco.next().quantidade);
        assertEquals(0, troco.next().quantidade);
    }

    @Test
    void Should_Return_Null_When_Trying_To_Get_Change_Without_Printing() throws PapelMoedaInvalidaException {
        int quantia1 = 50;
        int quantia2 = 20;
        int quantia3 = 20;
        int quantia4 = 10;

        ticketMachine.inserir(quantia1);
        ticketMachine.inserir(quantia2);
        ticketMachine.inserir(quantia3);
        ticketMachine.inserir(quantia4);

        int saldoAtualSemImprimir = ticketMachine.getSaldo();
        Iterator<PapelMoeda> troco = ticketMachine.getTroco();

        assertEquals(quantia1 + quantia2 + quantia3 + quantia4, saldoAtualSemImprimir);
        assertNull(troco);
    }

    @Test
    void Should_Throw_An_Exception_Of_Invalid_Bill() {
        int quantia = 30;

        PapelMoedaInvalidaException exception = assertThrows(
                PapelMoedaInvalidaException.class,
                () -> ticketMachine.inserir(quantia)
        );

        assertEquals(String.format("O papel moeda %d não é valido.", quantia), exception.getMessage());
    }

    @Test
    void Should_Throw_An_Exception_Of_Insufficient_Balance() throws PapelMoedaInvalidaException {
        int quantia = 5;
        int quantia2 = 5;

        ticketMachine.inserir(quantia);
        ticketMachine.inserir(quantia2);

        SaldoInsuficienteException exception = assertThrows(
                SaldoInsuficienteException.class,
                () -> ticketMachine.imprimir()
        );

        assertEquals(
                String.format("Saldo $%d insuficiente para bilhete de $%d", quantia+quantia2, precoDoBilhete),
                exception.getMessage()
        );
    }
}
