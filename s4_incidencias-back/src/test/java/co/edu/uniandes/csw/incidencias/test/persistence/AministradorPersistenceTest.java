/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.test.persistence;

import co.edu.uniandes.csw.incidencias.entities.AdministradorEntity;
import co.edu.uniandes.csw.incidencias.persistence.AdministradorPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Juan Pablo Correa Puerta
 */
@RunWith(Arquillian.class)
public class AministradorPersistenceTest
{
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<AdministradorEntity> data = new ArrayList<AdministradorEntity>();
    
    @Inject
    private AdministradorPersistence persistencia;
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AdministradorEntity.class.getPackage())
                .addPackage(AdministradorPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }    

    
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    private void clearData() {
        em.createQuery("DELETE FROM AdministradorEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            AdministradorEntity entity = factory.manufacturePojo(AdministradorEntity.class);
            em.persist(entity);
            data.add(entity);
        }        
    }

    @Test
    public void createAdministradorTest() {
        PodamFactory factory = new PodamFactoryImpl();
        AdministradorEntity newEntity = factory.manufacturePojo(AdministradorEntity.class);
        AdministradorEntity entidad = (AdministradorEntity) persistencia.create(newEntity);
        Assert.assertNotNull(entidad);
        AdministradorEntity entity = em.find(AdministradorEntity.class, entidad.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

/*    
    @Test
    public void findTecnicoTest() {
        TecnicoEntity entity = data.get(0);
        TecnicoEntity newEntity = tp.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());  
    }
    @Test
    public void findAllTecnicoTest() {
        List<TecnicoEntity> results = tp.findAll();
        Assert.assertEquals(data.size(), results.size());
        for (TecnicoEntity ent : results) {
            boolean found = false;
            for (TecnicoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }   
    } 
    
    @Test
    public void deleteTecnicoTest() {
        TecnicoEntity entity = data.get(0);
        tp.delete(entity.getId());
        TecnicoEntity deleted = em.find(TecnicoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    @Test
    public void updateTecnicoTest() {
        TecnicoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TecnicoEntity newEntity = factory.manufacturePojo(TecnicoEntity.class);
        newEntity.setId(entity.getId());
        tp.update(newEntity);
        TecnicoEntity resp = em.find(TecnicoEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
 */   
}
