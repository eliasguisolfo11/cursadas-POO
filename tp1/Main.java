import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //creo objetos de cada tipo de empleado
        Empleado emp1 = new EmpleadoTiempoCompleto("Juan Pérez", 2500.00);
        Empleado emp2 = new EmpleadoPorHora("María García", 2000.00, 160, 15.50);
        Empleado emp3 = new EmpleadoPorComision("Carlos López", 1200.00, 8000, 0.08);
        Empleado emp4 = new EmpleadoPorProyecto("Ana Martínez", 1800.00, 3, 200.00);


        //guardo los empleados en una lista
        List<Empleado> empleados = new ArrayList<>();
        empleados.add(emp1);
        empleados.add(emp2);
        empleados.add(emp3);
        empleados.add(emp4);

        //muestro la información y el salario de cada empleado
        System.out.println("=== NOMINA DE EMPLEADOS ===\n");
        for (Empleado emp : empleados) {
            emp.mostrarInfo();
            System.out.println("Salario calculado: " + emp.calcularSalario());
            System.out.println();
            System.out.println("-----------------------------\n");
        }
    }
}
