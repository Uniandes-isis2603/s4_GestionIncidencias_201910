/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.incidencias.test.persistence;

import co.edu.uniandes.csw.incidencias.entities.SlaEntity;
import co.edu.uniandes.csw.incidencias.persistence.SlaPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;

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
 * @author Daniel Santamaría
 */


@RunWith(Arquillian.class)
public class SlaPersistenceTest {
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<SlaEntity> data = new ArrayList<SlaEntity>();
    
     @Inject
    private SlaPersistence ep;
     
     @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(SlaEntity.class.getPackage())
                .addPackage(SlaPersistence.class.getPackage())
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
        em.createQuery("delete from SlaEntity").executeUpdate();
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            SlaEntity entity = factory.manufacturePojo(SlaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    @Test
    public void createSlaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        SlaEntity newEntity = factory.manufacturePojo(SlaEntity.class);
        SlaEntity te = ep.create(newEntity);
        Assert.assertNotNull(te);
        SlaEntity entity = em.find(SlaEntity.class, te.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    
    @Test
    public void findSlaTest() {
        SlaEntity entity = data.get(0);
        SlaEntity newEntity = ep.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());  
    }
    @Test
    public void findAlSlaTest() {
        List<SlaEntity> results = ep.findAll();
        Assert.assertEquals(data.size(), results.size());
        for (SlaEntity ent : results) {
            boolean found = false;
            for (SlaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }   
    } 
    
    @Test
    public void deleteSlaTest() {
        SlaEntity entity = data.get(0);
        ep.delete(entity.getId());
        SlaEntity deleted = em.find(SlaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    @Test
    public void updateSlaTest() {
        SlaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        SlaEntity newEntity = factory.manufacturePojo(SlaEntity.class);
        newEntity.setId(entity.getId());
        ep.update(newEntity);
        SlaEntity resp = em.find(SlaEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
    
}
