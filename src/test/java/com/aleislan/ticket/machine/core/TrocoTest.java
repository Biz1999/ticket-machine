package com.aleislan.ticket.machine.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrocoTest {

    @Test
    void Should_Return_Two_100_Bills_And_One_50_Bill() {
        int valor = 250;
        Integer[] papelMoeda = {2, 5, 10, 20, 50, 100};

        Troco troco = new Troco(valor, papelMoeda);

        PapelMoeda[] papeisMoeda = troco.getPapeisMoeda();

        assertInstanceOf(PapelMoeda[].class, papeisMoeda);
        assertEquals(2, papeisMoeda[5].getQuantidade());
        assertEquals(1, papeisMoeda[4].getQuantidade());
        assertEquals(0, papeisMoeda[0].getQuantidade());
        assertNotEquals(1, papeisMoeda[3].getQuantidade());
        assertNotEquals(3, papeisMoeda[2].getQuantidade());
        assertNotEquals(2, papeisMoeda[1].getQuantidade());
    }
}
