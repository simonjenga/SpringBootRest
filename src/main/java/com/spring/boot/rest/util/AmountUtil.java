package com.spring.boot.rest.util;

import java.util.List;

import com.spring.boot.rest.domain.Amount;

/**
 * This is a utility class for the {@link Amount} object.
 * 
 * @author Simon Njenga
 * @version 0.1
 */
public class AmountUtil {

    // Suppresses default constructor, ensuring non-instantiability.
    private AmountUtil() {
    }

    /**
     * Checks that an amount and it's id is not null.
     */
    public static List<Amount> amountNotNull(Amount amount, List<Amount> amounts) {
        if ((amount != null && amount.getId() != null)) {
            amounts.add(amount);
        } else {
            Amount newAmount = new Amount();
            newAmount.setAmount(0D);
            amounts.add(newAmount);
        }
        return amounts;
    }
}
