package ar.com.ada.creditos.managers;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import ar.com.ada.creditos.entities.reportes.ReporteCantClientePrestamos;

public class ReporteManager {
    protected SessionFactory sessionFactory;
    public void setup() {

        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure() // configures settings
                // from
                // hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception ex) {
            StandardServiceRegistryBuilder.destroy(registry);
            throw ex;
        }

    }

    public void exit() {
        sessionFactory.close();
    }

    public ReporteCantClientePrestamos getReporte() {

        Session session = sessionFactory.openSession();

        Query query = session.createNativeQuery(
                "select (select count(*) from cliente) as cant_cliente, (select count(*) from prestamo) as cant_prestamos",
                ReporteCantClientePrestamos.class);

        List<ReporteCantClientePrestamos> reportes = query.getResultList();
        return reportes.get(0);

    }
    
}
