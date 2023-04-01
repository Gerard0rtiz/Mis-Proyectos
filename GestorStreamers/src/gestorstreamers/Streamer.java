package gestorstreamers;

/**
 * Clase de objetos tipo streamer para el Gestor de Streamers
 * @author gerard
 */
public class Streamer {
    public String nickname;
    public String URLimg;
    public int horasEmitidas;
    public int numSeguidores;
    public String plataforma;
    
    /*constructor*/
    public Streamer(String nickname, String URLimg, int horasEmitidas, int numSeguidores, String plataforma) {
        this.nickname = nickname;
        this.URLimg = URLimg;
        this.horasEmitidas = horasEmitidas;
        this.numSeguidores = numSeguidores;
        this.plataforma = plataforma;
    }
    
    public String printInfoStreamer(){
        return nickname + ";" + URLimg + ";" + horasEmitidas + ";" + numSeguidores + ";" + plataforma;
    }
    
    public String toStringHTML(){
        String resultat = "";
        resultat += "<table border = '1'>";
        resultat += "<tr><td>Nickname</td><td>Imagen</td><td>Horas Emitidas</td><td>Cantidad de Seguidores</td><td>Plataforma de Emisión</td><td>Facturación</td></tr>";
        resultat += "<tr> <td>" + nickname + "</td>";
        resultat += "<td><img height = '150' width = '200' src= '" + URLimg + "'></td>";
        resultat += "<td>" + horasEmitidas + "</td>";
        resultat += "<td>" + numSeguidores + "</td>";        
        resultat += "<td>" + plataforma + "</td>";
        resultat += "<td>" + facturacion() + "</td></tr>";
        resultat += "</table>";
        return resultat;
    }
    
    /*método para crear la facturación*/
    public double facturacion(){
        double resultado = 0;
        if(plataforma.equalsIgnoreCase("youtube")){
            if(0<= numSeguidores&& numSeguidores< 5000){
                resultado = 0;
            } else if(5000<= numSeguidores&& numSeguidores< 25000){
                resultado = horasEmitidas*0.01;
            } else if(25000<= numSeguidores&& numSeguidores< 100000){
                resultado = horasEmitidas*0.02;
            } else if(100000<= numSeguidores&& numSeguidores< 750000){
                resultado = horasEmitidas*0.04;
            } else if(750000>= numSeguidores){
                resultado = horasEmitidas*0.06;
            }
        } else if(plataforma.equalsIgnoreCase("twitch")){
            if(0<= numSeguidores&& numSeguidores< 7500){
                resultado = 0;
            } else if(7500<= numSeguidores&& numSeguidores< 50000){
                resultado = horasEmitidas*0.01;
            } else if(50000<= numSeguidores&& numSeguidores< 300000){
                resultado = horasEmitidas*0.2;
            } else if(300000>= numSeguidores){
                resultado = horasEmitidas*0.5;
            }
        } else if(plataforma.equalsIgnoreCase("default")){
            if(0<= numSeguidores&& numSeguidores< 10000){
                resultado = 0;
            } else if(10000<= numSeguidores&& numSeguidores< 100000){
                resultado = horasEmitidas*0.2;
            } else if(100000 >= numSeguidores){
                resultado = horasEmitidas*0.35;
            }
        }
        return resultado;
    }
}
