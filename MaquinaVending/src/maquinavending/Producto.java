package maquinavending;

/**
 * Representa los productos que aparecerÃ¡n disponibles en la mÃ¡quina expendedora y en el menú técnico
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
    
    //añadir stock
    public void AddStock(int StockAdditional) {
        this.cant += StockAdditional;
    }
    
    //Cambiar precio
    public void ChPrice(double newPrice){
        this.price = newPrice;
    }
    
    //mostrar información de un producto
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
