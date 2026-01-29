/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simpleatmsystem.comparators;

import java.util.Comparator;
import simpleatmsystem.models.TransactionsEntry;

/**
 *
 * @author Lenovo
 */
public class AmountComparator implements Comparator<TransactionsEntry> {

    @Override
    public int compare(TransactionsEntry t1, TransactionsEntry t2) {
        return Double.compare(t1.getAmount(), t2.getAmount());
    }

}
