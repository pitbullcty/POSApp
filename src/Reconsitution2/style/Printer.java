package Reconsitution2.style;

import Reconsitution2.Payment;
import Reconsitution2.Recept;
import Reconsitution2.Sale;

public interface Printer {
    void print(Sale sale, Payment payment);
}
