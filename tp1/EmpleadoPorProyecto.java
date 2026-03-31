class EmpleadoPorProyecto extends Empleado {
    private int proyectosCompletados;
    private double bonoPorProyecto;

    public EmpleadoPorProyecto(String nombre, double salarioBase, int proyectosCompletados, double bonoPorProyecto) {
        super(nombre, salarioBase);
        this.proyectosCompletados = proyectosCompletados;
        this.bonoPorProyecto = bonoPorProyecto;
    }

    @Override
    public double calcularSalario() {
        return salarioBase + (proyectosCompletados * bonoPorProyecto);
    }
}