class EmpleadoTiempoCompleto extends Empleado {

    public EmpleadoTiempoCompleto(String nombre, double salarioBase) {
        super(nombre, salarioBase);
    }

    @Override
    public double calcularSalario() {
        return salarioBase; // El salario de un empleado a tiempo completo es su salario base
    }
    
}
