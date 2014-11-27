package ua.ukrdev.deal.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eugene on 21.09.2014.
 */
public class Errs {
   private Map<Integer, String> errs;

    public Errs() {
        errs = new HashMap<Integer, String>();
        errs.put(0, "Successful Operation");
        errs.put(101, "Missing mandatory value");
        errs.put(102, "Illegal value");
        errs.put(201, "Dealer account is locked");
        errs.put(202, "Dealer doesn't have enough balance");
        errs.put(203, "Dealer exceeded his daily limit");
        errs.put(205, "Dealer exceeded his weekly limit");
        errs.put(206, "Dealer exceeded his monthly limit");
        errs.put(207, "Dealer exceeded his periodically limit");
        errs.put(201, "Recharge failed by the prepaid system");
        errs.put(401, "Recharge transaction processing is uncertain");
        errs.put(501, "Dealer not registered in system");
        errs.put(1020, "Top-up amount below minimum limit OR Top-up amount above maximum limit");
        errs.put(1021, "Time between transaction restriction");
        errs.put(1025, "The given dealer is not allowed for recharge operation");
        errs.put(1094, "Communication error with the prepaid system");
        errs.put(1108, "Destination subscriber invalid");
        errs.put(7120, "Transaction time is below the beginning of the time frame restriction");
        errs.put(7121, "Transaction time is exceed the time frame restriction");
        errs.put(909, "System failure");
    }

    public String getErrDescr(int code) {
        return errs.get(code);
    }
}
