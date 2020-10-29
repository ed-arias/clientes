package com.seleccion.nuvu.clientes.domain.entities.enums;

public enum RolCliente {

    ROL_USER("ROL_USER"), 
    ROL_ADMIN("ROL_ADMIN");
    
    private String rolAsignado;
    
    private RolCliente(String rolAsignado) {
        this.rolAsignado = rolAsignado;
    }
   
    @Override
    public String toString(){
        return rolAsignado;
    }
}
