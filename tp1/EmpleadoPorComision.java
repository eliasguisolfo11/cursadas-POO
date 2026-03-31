public class EmpleadoPorComision extends Empleado {

    private double ventasTotales;
    private double tasaComision;

    public EmpleadoPorComision(String nombre, double salarioBase, double ventasTotales, double tasaComision) {
        super(nombre, salarioBase);
        this.ventasTotales = ventasTotales;
        this.tasaComision = tasaComision;
    }

    @Override
    public double calcularSalario() {
        return salarioBase + (ventasTotales * tasaComision); // El salario de un empleado por comisión se calcula sumando su salario base
        //  con el producto de las ventas totales y la tasa de comisión
    }
    
}
