package poc.spring.boot.domain.repository;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import poc.spring.boot.configuration.RepositoryConfiguration;
import poc.spring.boot.domain.model.Order;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {RepositoryConfiguration.class})
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;
    @Before
    public void setUp() throws Exception {
    }
 
    @Test
    public void testUpdateData(){
        /*Test data retrieval*/
        Order orderA = orderRepository.findByProductName("Dell Monitor L2401");
        assertNotNull(orderA);
        assertEquals(1, orderA.getQty().intValue());
        
        orderA.setProductName("Dell Monitor");
        
        orderRepository.save(orderA);
    }

    @Test
    public void testFetchData(){
        /*Test data retrieval*/
        Order orderA = orderRepository.findByProductName("Dell Monitor");
        assertNotNull(orderA);
        assertEquals(1, orderA.getQty().intValue());
        /*Get all products, list should only have two*/
        Iterable<Order> orders = orderRepository.findAll();
        int count = 0;
        for(Order o : orders){
        	System.out.println("productName: " + o.getProductName());
            count++;
        }
        assertEquals(count, 4);
    }
}
