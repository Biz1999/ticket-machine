package com.aleislan.ticket.machine.core;

import java.util.Iterator;

/**
 *
 * @author Calebe de Paula Bianchini
 */
final class Troco {

    protected PapelMoeda[] papeisMoeda;
    protected int lengthMoeda = 6;
    protected Iterator<PapelMoeda> arrayTroco;

    public Troco(int valor) {
        papeisMoeda = new PapelMoeda[lengthMoeda];
        int count = 0;

        //Como o argumento quantidade de PapelMoeda  é int, será arredondado sempre para baixo a divisão       
        papeisMoeda[5] = new PapelMoeda(100, valor / 100);
        valor = valor - (papeisMoeda[5].getQuantidade() * 100);

        papeisMoeda[4] = new PapelMoeda(50, valor / 50);
        valor = valor - (papeisMoeda[4].getQuantidade() * 50);

        papeisMoeda[3] = new PapelMoeda(20, valor / 20);
        valor = valor - (papeisMoeda[3].getQuantidade() * 20);

        papeisMoeda[2] = new PapelMoeda(10, valor / 10);
        valor = valor - (papeisMoeda[2].getQuantidade() * 10);

        papeisMoeda[1] = new PapelMoeda(5, valor / 5);
        valor = valor - (papeisMoeda[1].getQuantidade() * 5);

        papeisMoeda[0] = new PapelMoeda(2, valor / 2);
 
        arrayTroco = getIterator();

    }

    public Iterator<PapelMoeda> getIterator() {
        return new TrocoIterator(this);
    }

    public int getTroco() {
        
        int sumReturn;
        PapelMoeda indexMoeda;

        sumReturn = 0;
        indexMoeda = arrayTroco.next();
        
        while(indexMoeda != null) {
            sumReturn = sumReturn + (indexMoeda.getValor() * indexMoeda.getQuantidade());
            indexMoeda = arrayTroco.next();
        }

        return sumReturn;

    }

    class TrocoIterator implements Iterator<PapelMoeda> {

        protected Troco troco;
        protected int indexArray;

        public TrocoIterator(Troco troco) {
            this.troco = troco;
            this.indexArray = 0;
        }

        @Override
        public boolean hasNext() {
            return this.indexArray < troco.lengthMoeda;
        }

        @Override
        public PapelMoeda next() {
            
            PapelMoeda ret = null;

            if(this.hasNext()) {
                ret = troco.papeisMoeda[indexArray];
                this.indexArray+=1;
            }

            return ret;
        }

        @Override
        public void remove() {
            next();
        }
    }
}
