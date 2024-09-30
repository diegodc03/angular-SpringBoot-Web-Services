package com.irojas.demojwt.workHours.Model;

public enum WorkingRoles {
    JEFE(30.0),
    MONTADOR(10),
    PROTERO(20.0),
    RIFA(20.0),
    CAMARA(20.0),
    PORTEROMONTAJE(30.0),
    JEFEMONTAJE(40.0);

    private final double salary;

    // Constructor para asignar el salario
    WorkingRoles(double salary) {
        this.salary = salary;
    }

    // Método para obtener el salario de cada rol
    public double getSalary() {
        return this.salary;
    }
    
    
    
}