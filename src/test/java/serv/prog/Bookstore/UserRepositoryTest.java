package serv.prog.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import serv.prog.Bookstore.domain.User;
import serv.prog.Bookstore.domain.UserRepository;



@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void findByUsernameShouldReturnUser() {
        List<User> users = repository.findByUsername("admin");
        
        assertThat(users).hasSize(1);
        assertThat(users.get(0).getId()).isNotNull();
    }
    
    @Test
    public void createNewUser() {
    	assertThat((List<User>) repository.findAll()).hasSize(2);
    	User sysadmin = new User("sysadmin", "$2a$10$KtOoz491q3niRGIqGrZ4KeQI4bCOrix8CFuRSmdfslhKitoSF5wam", "ADMIN");
    	repository.save(sysadmin);
    	assertThat(sysadmin.getId()).isNotNull();
    	assertThat((List<User>) repository.findAll()).hasSize(3);
    }
    
    @Test
    public void deleteUser() {
    	assertThat(repository.findByUsername("user").get(0).getId()).isNotNull();
    	assertThat((List<User>) repository.findAll()).hasSize(2);
    	User user = repository.findByUsername("user").get(0);
    	repository.deleteById(user.getId());
    	assertThat((List<User>) repository.findAll()).hasSize(1);
    }

}