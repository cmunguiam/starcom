package pe.com.starcom.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * @author Hever Pumallihua
 */
public class Fecha {

	// Metodo usado para obtener la fecha actual 
	// @return Retorna un <b>STRING</b> con la fecha actual formato "dd-MM-yyyy"
	public static String getFechaActual() {
		Date ahora = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
		return formateador.format(ahora);
	}

	// Metodo usado para obtener la hora actual del sistema
	// @return Retorna un <b>STRING</b> con la hora actual formato "hh:mm:ss"
	public static String getHoraActual() {
		Date ahora = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("hh:mm:ss");
		return formateador.format(ahora);
	}

	// Sumarle dias a una fecha determinada
	// @param fch La fecha para sumarle los dias
	// @param dias Numero de dias a agregar
	// @return La fecha agregando los dias
	public static java.sql.Date sumarFechasDias(java.sql.Date fch, int dias) {
		Calendar cal = new GregorianCalendar();
		cal.setTimeInMillis(fch.getTime());
		cal.add(Calendar.DATE, dias);
		return new java.sql.Date(cal.getTimeInMillis());
	}

	public static Date sumarDias(Date fecha, int dias) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(fecha.getTime());
		cal.add(Calendar.DAY_OF_YEAR, dias);
		return cal.getTime();
	}

	// Restarle dias a una fecha determinada
	// @param fch La fecha
	// @param dias Dias a restar
	// @return La fecha restando los dias
	public static synchronized java.sql.Date restarFechasDias(
			java.sql.Date fch, int dias) {
		Calendar cal = new GregorianCalendar();
		cal.setTimeInMillis(fch.getTime());
		cal.add(Calendar.DATE, -dias);
		return new java.sql.Date(cal.getTimeInMillis());
	}

	// Diferencias entre dos fechas
	// @param fechaInicial La fecha de inicio
	// @param fechaFinal La fecha de fin
	// @return Retorna el numero de dias entre dos fechas
	public static synchronized int diferenciasDeFechas(Date fechaInicial,
			Date fechaFinal) {

		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		String fechaInicioString = df.format(fechaInicial);
		try {
			fechaInicial = df.parse(fechaInicioString);
		} catch (ParseException ex) {
		}

		String fechaFinalString = df.format(fechaFinal);
		try {
			fechaFinal = df.parse(fechaFinalString);
		} catch (ParseException ex) {
		}

		long fechaInicialMs = fechaInicial.getTime();
		long fechaFinalMs = fechaFinal.getTime();
		long diferencia = fechaFinalMs - fechaInicialMs;
		double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
		return ((int) dias);
	}

	// Devuele un java.util.Date desde un String en formato dd-MM-yyyy
	// @param La fecha a convertir a formato date
	// @return Retorna la fecha en formato Date
	public static synchronized java.util.Date deStringToDate(String fecha) {
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
		Date fechaEnviar = null;
		try {
			fechaEnviar = formatoDelTexto.parse(fecha);
			return fechaEnviar;
		} catch (ParseException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public static synchronized Calendar dateToCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
	
	public static synchronized int diasAtencionSbdAndDom(Date fechaInicio, Date fechaFina) {

		int diasAtencion = 0;
		Calendar fechaInicial = dateToCalendar(fechaInicio);
		Calendar fechaFinal = dateToCalendar(fechaFina);

		// mientras la fecha inicial sea menor o igual que la fecha final se
		// cuentan los dias
		while (fechaInicial.before(fechaFinal)
				|| fechaInicial.equals(fechaFinal)) {

			// si el dia de la semana de la fecha minima es diferente de sabado
			// o domingo
			if (fechaFinal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY
					&& fechaFinal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
				// se aumentan los dias de diferencia entre min y max
				diasAtencion++;
			}
			// se suma 1 dia para hacer la validacion del siguiente dia.
			fechaInicial.add(Calendar.DATE, 1);
		}

		return diasAtencion;
	}

	/*public static void main(String args[]) throws Exception {

		//Date fechaActual = new Date(Calendar.getInstance().getTime().getTime());
		//System.out.println("fechaActual="+fechaActual);
		
		//Date fechaSgte = sumarDias(fechaActual, 3);
		
		//System.out.println("fechaSgte="+fechaSgte);

		double promedio = ((double) 3 + 4 + 3) / 3;
		Long sd = Math.round(promedio);
		double porcentaje = ((double) sd.intValue() / 5) * 100;
		
		System.out.println(promedio);
		System.out.println(sd.intValue());
		System.out.println(porcentaje);   

	}*/
	

}
