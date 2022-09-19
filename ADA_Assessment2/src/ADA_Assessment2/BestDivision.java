/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADA_Assessment2;

import java.util.ArrayList;

/**
 *
 * @author Callum
 */
public class BestDivision {

        protected int price;
        protected ArrayList<Land> list = new ArrayList<>();

        // Constructor used for 1 piece of land
        public BestDivision(int price, Land cell) {
            this.price = price;
            this.list.add(cell);
        }

        // Constructor used for combining two best divisions
        public BestDivision(BestDivision aSide, BestDivision bSide) {
            this.price = aSide.price + bSide.price;
            this.list.addAll(aSide.list);
            this.list.addAll(bSide.list);
        }
    }
