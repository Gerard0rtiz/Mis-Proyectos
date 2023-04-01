package gestorempleadosfordtrucks;

/**
 * Clase para los objetos de tipo empleado que se encuentren en el archivo .csv
 * que se cargue en el main
 *
 * @author gerard
 */
public class EmpleadosFT {

    public String nombre;
    public String cat_en;
    public String cat_esp;
    public String email;
    public String telefono;

    /*Constructor*/
    public EmpleadosFT(String nombre, String cat_en, String cat_esp, String email, String telefono) {
        this.nombre = nombre;
        this.cat_en = cat_en;
        this.cat_esp = cat_esp;
        this.email = email;
        this.telefono = telefono;
    }

    /*función para convertir en HTML los valores que se introduzcan en los distintos atributos*/
    public String stringHTML() {
        String html = "";
        html += "<table width='505'>";
        html += "<tbody><tr>";
        html += "<td width='110'>";
        html += "<p><img src='https://ftrucks.es/imagenes_firmas_mails/logo_ftrucks_gris.png' width='132' height='162' /></p></td>";
        html += "<td width='30'><center><img src='https://ftrucks.es/imagenes_firmas_mails/linea_vertical_v2.png ' width='5' height='162' /></center></td>";
        html += "<td><span style='line-height: 120%; font-size: 14px;'><strong><span style='color: #fa602d; font-family: Gill Sans, Gill Sans MT; letter-spacing: 0.1px;'>" + nombre + "</span></strong></span><br />";
        html += "<span style='line-height: 120%; color: #fa602d; font-family: Gill Sans, Gill Sans MT; font-size: 14px;'>" + cat_en + "</span><br />";
        html += "<span style='line-height: 120%; color: #fa602d; font-family: Gill Sans, Gill Sans MT; font-size: 14px;'>" + cat_esp + "</span><br />";
        html += "<span style='line-height: 120%; color: #4c4c4c; ; font-family: Gill Sans,Gill Sans MT; font-size: 14px; letter-spacing: 0.1px;'><a href='mailto:" + email + "' target='_blank' rel='noreferrer'>"+ email +"</a></span><br />";
        html += "<span style='line-height: 120%; font-family: Gill Sans, Gill Sans MT; font-size: 14px; letter-spacing: 0px;'>" + telefono + "</span><br /><br />";
        html += "<span style='line-height: 120%; font-family: Gill Sans, Gill Sans MT; font-size: 14px; letter-spacing: 0.1px;'>Ford Trucks Espa&ntilde;a</span><br />";
        html += "<span style='line-height: 120%; font-family: Gill Sans, Gill Sans MT; font-size: 14px; letter-spacing: 0.1px;'>Parque Empresarial Rivas Futura - C/Marie Curie, 7</span><br />";
        html += "<span style='line-height: 120%; font-family: Gill Sans, Gill Sans MT; font-size: 14px; letter-spacing: 0.1px;'>Edificio Beta, 2-7, 28521 - Rivas-Vaciamadrid (Madrid)</span><br /></td></tr>";
        html += "<tr height='10'></tr></tbody></table>";
        html += "<table width='505'><tbody><tr>";
        html += "<td width='100%'><img src='https://ftrucks.es/imagenes_firmas_mails/Ford_BannerTranquilidad_v2.1.png' width='100%' /></td></tr>";
        html += "<tr height=10></tr></tbody></table>";
        html += "<table width=100%><tbody><tr>";
        html += "<td width=100%>";
        html += "<span style='color: #3E3E3E; font-family: Arial Narrow, Arial, sans-serif; font-size: 14px; letter-spacing: 0px;'><strong>AVISO LEGAL:</strong></span>";
        html += "<span style='color: #AAAAAA; font-family: Arial Narrow, Arial, sans-serif; font-size: 14px; letter-spacing: 0px;'>Este mensaje y sus archivos adjuntos van dirigidos ";
        html += "exclusivamente a su destinatario, pudiendo contener informaci&oacute;n confidencial sometida a secreto profesional. No est&aacute; permitida su comunicaci&oacute;n,";
        html += " reproducci&oacute;n o distribuci&oacute;n sin la autorizaci&oacute;n expresa de FORD TRUCKS ESPA&Ntilde;A. Si usted no es el destinatario final, por favor, elim&iacute;nelo e inf&oacute;rmenos por esta v&iacute;a. </span>";
        html += "<span style='color: #3E3E3E; font-family: Arial Narrow, Arial, sans-serif; font-size: 14px; letter-spacing: 0px;'><strong>PROTECCI&Oacute;N DE DATOS: </strong></span>";
        html += "<span style='color: #AAAAAA; font-family: Arial Narrow, Arial, sans-serif; font-size: 14px; letter-spacing: 0px;'>De conformidad con lo dispuesto en el Reglamento (UE) 2016/679, de 27 de abril (GDPR), y la Ley Org&aacute;nica 3/2018, de 5 de diciembre (LOPDGDD), ";
        html += "le informamos de que los datos personales y la direcci&oacute;n de correo electr&oacute;nico del interesado, se tratar&aacute;n bajo la responsabilidad de FORD TRUCKS ESPA&Ntilde;A por un inter&eacute;s ";
        html += "leg&iacute;timo y para el env&iacute;o de comunicaciones sobre nuestros productos y servicios, y se conservar&aacute;n mientras ninguna de las partes se oponga a ello. Los datos no se ";
        html += "comunicar&aacute;n a terceros, salvo obligaci&oacute;n legal Le informamos de que puede ejercer los derechos de acceso, rectificaci&oacute;n, portabilidad y supresi&oacute;n de sus datos y los ";
        html += "de limitaci&oacute;n y oposici&oacute;n a su tratamiento dirigi&eacute;ndose a Parque Empresarial Rivas Futura. Edificio Beta. C/ Marie Curie, 5-7 Planta 2 Oficina 7 - 28521 Rivas-Vaciamadrid ";
        html += "(Madrid). E-mail: <a href='mailto:lopd@fordtrucks.es' target='_blank' rel='noreferrer'>lopd@fordtrucks.es</a>. Si considera que el tratamiento no se ajusta a la normativa vigente, podr&aacute; presentar una reclamaci&oacute;n ante la autoridad de control en ";
        html += "www. <a href='https://www.google.com/url?q=http://aepd.es&amp;source=gmail-html&amp;ust=1678787674377000&amp;usg=AOvVaw26YC_Qs0OMa-wy6vjV3qTY'x target='_blank' rel='noreferrer'>aepd.es</a>. Datos de contacto del delegado de protecci&oacute;n de datos: ANTOVICROS, S.L. (AVIDATA). C/ La Caza, 11. Planta 6.50019<a href='mailto:Zaragoza-juridico@avidata.es' target='_blank' rel='noreferrer'>Zaragoza-juridico@avidata.es</a>";
        html += "</span><br /></td></tr></tbody></table>";
        return html;
    }
}
