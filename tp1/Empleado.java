
abstract class Empleado {

    protected String nombre;
    protected double salarioBase;

    public Empleado(String nombre, double salarioBase) {
        this.nombre = nombre;
        this.salarioBase= salarioBase;
    }

    public void mostrarInfo() {
        System.out.println("Empleado: " + nombre);
        System.out.println("Salario Base: " + salarioBase);
    }

    public abstract double calcularSalario();
}