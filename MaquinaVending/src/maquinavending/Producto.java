package maquinavending;

/**
 * Representa los productos que aparecerán disponibles en la máquina expendedora y en el men� t�cnico
 * @author gerard
 */
class Producto {
    private String name;
    private double price;
    private int cant;
    
    public Producto(String nombre, double precio, int cantidad) {
        this.name = nombre;
        this.price = precio;
        this.cant = cantidad;
    }
  
    public String getNombre() {
        return name;
    }
  
    public double getPrecio() {
        return price;
    }
  
    public int getCantidad() {
        return cant;
    }
  
    public void setCantidad(int cantidad) {
        this.cant = cantidad;
    }
    
    //a�adir stock
    public void AddStock(int StockAdditional) {
        this.cant += StockAdditional;
    }
    
    //Cambiar precio
    public void ChPrice(double newPrice){
        this.price = newPrice;
    }
    
    //mostrar informaci�n de un producto
    public void ShowInfo(){
        this.getNombre();
        this.getCantidad();
        this.getPrecio();
    }
    
    //mostrar Stock global
    public void TotalStock(){
        this.getCantidad();
    }
}
