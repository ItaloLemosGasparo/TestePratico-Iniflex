import model.Employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static util.CurrencyFormatter.formatCurrency;

public class TestePraticoApplication {

    public static void main(String[] args) {
        List<Employee> employees = getEmployees();

        //3.2
        employees.removeIf(employee -> employee.getName().equals("João"));

        //3.3
        System.out.println("Lista de Funcionários:");
        employees.forEach(System.out::println);

        //3.4
        employees.forEach(employee ->
                employee.setSalary(employee.getSalary().multiply(BigDecimal.valueOf(1.1)))
        );

        System.out.println("\nLista após aumento salarial:");
        employees.forEach(System.out::println);

        //3.5
        Map<String, List<Employee>> employeesByRole = employees.stream()
                .collect(Collectors.groupingBy(Employee::getRole));

        //3.6
        System.out.println("\nFuncionários agrupados por função:");
        employeesByRole.forEach((role, list) -> {
            System.out.println("Função: " + role);
            list.forEach(System.out::println);
        });

        //3.8
        System.out.println("\nFuncionários que fazem aniversário nos meses 10 e 12:");
        employees.stream()
                .filter(employee -> employee.getBirthDate().getMonthValue() == 10
                        || employee.getBirthDate().getMonthValue() == 12)
                .forEach(System.out::println);

        //3.9
        Employee oldestEmployee = employees.stream()
                .min(Comparator.comparing(Employee::getBirthDate))
                .orElse(null);

        if (oldestEmployee != null) {
            int age = Period.between(oldestEmployee.getBirthDate(), LocalDate.now()).getYears();
            System.out.println("\nFuncionário com a maior idade:");
            System.out.println("Nome: " + oldestEmployee.getName() + ", Idade: " + age + " anos");
        }

        //3.10
        System.out.println("\nFuncionários em ordem alfabética:");
        employees.stream()
                .sorted(Comparator.comparing(Employee::getName))
                .forEach(System.out::println);

        //3.11
        BigDecimal totalSalary = employees.stream()
                .map(Employee::getSalary)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("\nTotal dos salários: " + formatCurrency(totalSalary));

        //3.12
        final BigDecimal minimumWage = BigDecimal.valueOf(1212.00);
        System.out.println("\nQuantidade de salários mínimos por funcionário:");
        employees.forEach(employee -> {
            BigDecimal multiples = employee.getSalary().divide(minimumWage, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(employee.getName() + " ganha " + multiples + " salários mínimos.");
        });
    }

    private static List<Employee> getEmployees() {
        return new ArrayList<>(List.of(
                new Employee("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"),
                new Employee("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"),
                new Employee("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"),
                new Employee("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"),
                new Employee("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"),
                new Employee("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"),
                new Employee("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"),
                new Employee("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"),
                new Employee("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"),
                new Employee("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente")
        ));
    }
}
