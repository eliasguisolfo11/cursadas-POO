class EmpleadoPorHora extends Empleado {

    private double horasTrabajadas;
    private double tarifaPorHora;

    public EmpleadoPorHora(String nombre, double salarioBase, double horasTrabajadas, double tarifaPorHora) {
        super(nombre, salarioBase);
        this.horasTrabajadas = horasTrabajadas;
        this.tarifaPorHora = tarifaPorHora;
    }

    @Override
    public double calcularSalario() {
        return horasTrabajadas * tarifaPorHora; // El salario de un empleado por hora se calcula multiplicando las horas trabajadas
        //  por la tarifa por hora
    }
}