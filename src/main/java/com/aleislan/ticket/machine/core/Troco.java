package com.aleislan.ticket.machine.core;

import java.util.Iterator;

/**
 *
 * @author Calebe de Paula Bianchini
 */
class Troco {

    protected PapelMoeda[] papeisMoeda;
    protected int lengthMoeda = 6;
    protected Iterator<PapelMoeda> arrayTroco;

    public Troco(int valor) {
        papeisMoeda = new PapelMoeda[lengthMoeda];
        int count = 0;

        //Como o argumento quantidade de PapelMoeda  é int, será arredondado sempre para baixo a divisão       
        papeisMoeda[5] = PapelMoeda(100, valor / 100);
        valor = valor - (papeisMoeda[5].getQuantidade() * 100);

        papeisMoeda[4] = PapelMoeda(50, valor / 50);
        valor = valor - (papeisMoeda[4].getQuantidade() * 50);

        papeisMoeda[3] = PapelMoeda(20, valor / 20);
        valor = valor - (papeisMoeda[3].getQuantidade() * 20);

        papeisMoeda[2] = PapelMoeda(10, valor / 10);
        valor = valor - (papeisMoeda[2].getQuantidade() * 10);

        papeisMoeda[1] = PapelMoeda(5, valor / 5);
        valor = valor - (papeisMoeda[1].getQuantidade() * 5);

        papeisMoeda[0] = PapelMoeda(2, valor / 2);
        valor = valor - (papeisMoeda[0].getQuantidade() * 2);

        arrayTroco = getIterator();

    }

        // while (valor % 100 != 0) {
        //     valor = valor - 100;
        //     count++;
        // }
        // papeisMoeda[5] = new PapelMoeda(100, count);

        // count = 0;
        // while (valor % 50 != 0) {
        //     valor = valor - 50;
        //     count++;
        // }
        // papeisMoeda[4] = new PapelMoeda(50, count);

        // count = 0;
        // while (valor % 20 != 0) {
        //     valor = valor - 20;
        //     count++;
        // }
        // papeisMoeda[3] = new PapelMoeda(20, count);

        // count = 0;
        // while (valor % 10 != 0) {
        //     valor = valor - 10;
        //     count++;
        // }
        // papeisMoeda[2] = new PapelMoeda(10, count);

        // count = 0;
        // while (valor % 5 != 0) {
        //     valor = valor - 5;
        //     count++;
        // }
        // papeisMoeda[1] = new PapelMoeda(5, count);

        // count = 0;
        // while (valor % 2 != 0) {
        //     valor = valor - 2;
        //     count++;
        // }
        // papeisMoeda[0] = new PapelMoeda(2, count);

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
            if(this.indexArray < troco.lengthMoeda) {
                return true;
            } else {
                return false;
            }
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
