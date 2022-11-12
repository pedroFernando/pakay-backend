package ec.com.pakay.util;

import ec.com.pakay.domain.Amortizacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TablaAmortizacion {
	
	public List<Amortizacion> crearTablaAmortizacion(String clase, String tipo, Double monto, Double interes, Integer plazo, LocalDate fecha) {
        List<Amortizacion> listaAmortizacion = new ArrayList<>();
		if (clase.equals("Francesa")) {
            listaAmortizacion = amortizacionFrancesa(tipo, monto, plazo, interes, fecha);
        } else {
            listaAmortizacion = amortizacionAlemana(tipo, monto, plazo, interes, fecha);
        }
		return listaAmortizacion;
    }
	
	public List<Amortizacion> amortizacionFrancesa(String periodicidad, Double monto, int plazo, Double interes, LocalDate fecha) {
        List<Amortizacion> amortizaciones = new ArrayList<>();
        try {
        	double tasaPeriodica = 0.0;
            double sumaCapital = 0.0;
            double sumaInteres = 0.0;
            double sumaCuotas = 0.0;
            switch (periodicidad) {
                case "Días":
                    tasaPeriodica = (interes / 100) / 365;
                    break;
                case "Quincenas":
                    tasaPeriodica = (interes / 100) / 24;
                    break;
                case "Meses":
                    tasaPeriodica = (interes / 100) / 12;
                    break;
            }
            double capital = monto / plazo;
            Amortizacion amortizacion = new Amortizacion();
            amortizacion.setFecha(fecha);
            amortizacion.setSaldo(monto);
            amortizacion.setNumero(0);
            amortizaciones.add(amortizacion);
            double interesA;
            double amortizacionA;
            for (int i = 0; i < plazo; i++) {
                amortizacion = new Amortizacion();
                interesA = (amortizaciones.get(i).getSaldo() * (tasaPeriodica));
                amortizacionA = (amortizaciones.get(i).getSaldo() * tasaPeriodica)
                        / (1 - (Math.pow((1 + tasaPeriodica), plazo - i))) * -1;
                switch (periodicidad) {
                    case "Días":
                        amortizacion.setFecha(amortizaciones.get(i).getFecha().plusDays(1));
                        break;
                    case "Quincenas":
                        amortizacion.setFecha(amortizaciones.get(i).getFecha().plusDays(15));
                        break;
                    case "Meses":
                        amortizacion.setFecha(amortizaciones.get(i).getFecha().plusDays(31));
                        break;
                }
                amortizacion.setCapital(amortizacionA);
                amortizacion.setInteres(interesA);
                amortizacion.setCuota(amortizacionA + interesA);
                amortizacion.setSaldo(amortizaciones.get(i).getSaldo() - amortizacionA);
                amortizacion.setEstado("D");
                amortizacion.setNumero(i + 1);
                amortizaciones.add(amortizacion);
                sumaCapital += capital;
                sumaInteres += interesA;
                sumaCuotas += (capital + interesA);
            }
            amortizacion = new Amortizacion();
            amortizacion.setCapital(sumaCapital);
            amortizacion.setInteres(sumaInteres);
            amortizacion.setCuota(sumaCuotas);
            amortizaciones.add(amortizacion);
        }catch(Exception e) {
        	System.out.println("Error amortizacion: " + e.getMessage());
        }
        return amortizaciones;
    }
	
	public List<Amortizacion> amortizacionAlemana(String periodicidad, Double monto, int plazo, Double interes, LocalDate fecha) {
        switch (periodicidad) {
            case "Días":
                return amortizacionDiasA(monto, plazo, interes, fecha);
            case "Quincenas":
                return amortizacionQuincenasA(monto, plazo, interes, fecha);
            case "Meses":
                return amortizacionMesesA(monto, plazo, interes, fecha);
            default : return amortizacionMesesA(monto, plazo, interes, fecha);
        }
    }
	
	private List<Amortizacion> amortizacionDiasA(Double monto, int plazo, Double interes, LocalDate fecha) {
        List<Amortizacion> amortizaciones = new ArrayList<>();
        Amortizacion amortizacion = new Amortizacion();
        amortizacion.setFecha(fecha);
        amortizacion.setCapital(monto);
        amortizacion.setInteres(((monto * interes) / 365) * plazo);
        amortizacion.setCuota(amortizacion.getCapital() + amortizacion.getInteres());
        amortizacion.setSaldo(monto - amortizacion.getCapital());
        amortizacion.setEstado("D");
        amortizacion.setNumero(1);
        amortizaciones.add(amortizacion);
        return amortizaciones;
    }

    private List<Amortizacion> amortizacionQuincenasA(Double monto, int plazo, Double interes, LocalDate fecha) {
        List<Amortizacion> amortizaciones = new ArrayList<>();
        double sumaCapital = 0.0;
        double sumaInteres = 0.0;
        double sumaCuotas = 0.0;
        double capital = monto / plazo;
        Amortizacion amortizacion = new Amortizacion();
        amortizacion.setFecha(fecha);
        amortizacion.setSaldo(monto);
        amortizacion.setNumero(0);
        amortizaciones.add(amortizacion);
        double interesA;
        for (int i = 0; i < plazo; i++) {
            amortizacion = new Amortizacion();
            interesA = amortizaciones.get(i).getSaldo() * (((interes / 100) / 365) * 16);
            amortizacion.setFecha(amortizaciones.get(i).getFecha().plusDays(15));
            amortizacion.setCapital(capital);
            amortizacion.setInteres(interesA);
            amortizacion.setCuota(capital + interesA);
            amortizacion.setSaldo(amortizaciones.get(i).getSaldo() - capital);
            amortizacion.setEstado("D");
            amortizacion.setNumero(i + 1);
            amortizaciones.add(amortizacion);
            sumaCapital += capital;
            sumaInteres += interesA;
            sumaCuotas += (capital + interesA);
        }
        amortizacion = new Amortizacion();
        amortizacion.setCapital(sumaCapital);
        amortizacion.setInteres(sumaInteres);
        amortizacion.setCuota(sumaCuotas);
        amortizaciones.add(amortizacion);
        return amortizaciones;
    }

    private List<Amortizacion> amortizacionMesesA(Double monto, int plazo, Double interes, LocalDate fecha) {
        List<Amortizacion> amortizaciones = new ArrayList<>();
        double sumaCapital = 0.0;
        double sumaInteres = 0.0;
        double sumaCuotas = 0.0;
        double capital = monto / plazo;
        Amortizacion amortizacion = new Amortizacion();
        amortizacion.setFecha(fecha);
        amortizacion.setSaldo(monto);
        amortizacion.setNumero(0);
        amortizaciones.add(amortizacion);
        double interesA;
        for (int i = 0; i < plazo; i++) {
            amortizacion = new Amortizacion();
            interesA = (amortizaciones.get(i).getSaldo() * (interes / 100) / 12);
            amortizacion.setFecha(amortizaciones.get(i).getFecha().plusDays(31));
            amortizacion.setCapital(capital);
            amortizacion.setInteres(interesA);
            amortizacion.setCuota(capital + interesA);
            amortizacion.setSaldo(amortizaciones.get(i).getSaldo() - capital);
            amortizacion.setEstado("D");
            amortizacion.setNumero(i + 1);
            amortizaciones.add(amortizacion);
            sumaCapital += capital;
            sumaInteres += interesA;
            sumaCuotas += (capital + interesA);
        }
        amortizacion = new Amortizacion();
        amortizacion.setCapital(sumaCapital);
        amortizacion.setInteres(sumaInteres);
        amortizacion.setCuota(sumaCuotas);
        amortizaciones.add(amortizacion);
        return amortizaciones;
    }
    
}
