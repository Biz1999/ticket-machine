package com.aleislan.ticket.machine.core;

/**
 *
 * @author Alessandro / Islan
 */
class Troco {

    private PapelMoeda[] papeisMoeda;

    public Troco(int valor, Integer[] papeisMoeda) {
        this.papeisMoeda = new PapelMoeda[papeisMoeda.length];

        for (int i = papeisMoeda.length - 1; i >= 0; i--){
            this.papeisMoeda[i] = new PapelMoeda(papeisMoeda[i], valor / papeisMoeda[i]);
            valor = valor - (this.papeisMoeda[i].getQuantidade() * papeisMoeda[i]);
        }
    }

    public PapelMoeda[] getPapeisMoeda() {
        return this.papeisMoeda;
    }
}
