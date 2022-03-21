package com.example.proiect_v3;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.proiect_v3.product.Product;
import com.example.proiect_v3.product.ProductRepository;
import com.example.proiect_v3.user.User;
import com.example.proiect_v3.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repo;

    @Autowired
    private ProductRepository p_repo;

    // test methods go below
    @Test
    public void testCreateProduct() {
        Product product = new Product();
        product.setBarcode("100");
        product.setName("Paine");
        product.setDescription("de secara");
        product.setPrice(5);

        Product savedProduct = p_repo.save(product);

        Product existProduct = entityManager.find(Product.class, savedProduct.getId());

        assertThat(existProduct.getBarcode()).isEqualTo(existProduct.getBarcode());
    }


    @Test
    public void testCreateUser() {
            User user = new User();
            user.setEmail("raul@gmail.com");
            user.setPassword("raul2022");
            user.setFirstName("Raul");
            user.setLastName("Tanase");

            User savedUser = repo.save(user);

            User existUser = entityManager.find(User.class, savedUser.getId());

            assertThat(existUser.getEmail()).isEqualTo(existUser.getEmail());
    }


}
