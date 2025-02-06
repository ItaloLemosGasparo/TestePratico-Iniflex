package model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.Locale;

import static util.CurrencyFormatter.formatCurrency;

@Getter
@Setter
public class Employee extends Person {

    private BigDecimal salary;

    private String role;

    public Employee(String name, LocalDate birthDate, BigDecimal salary, String role) {
        super(name, birthDate);
        this.salary = salary;
        this.role = role;
    }

    @Override
    public String toString() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        DecimalFormat formatter = new DecimalFormat("#,##0.00", symbols);

        return super.toString().substring(0, super.toString().length() - 1) +
                ", salary=" + formatCurrency(salary) +
                ", role='" + role + '\'' +
                '}';
    }

}

