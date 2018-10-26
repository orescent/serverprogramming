package serv.prog.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import serv.prog.Bookstore.domain.Category;
import serv.prog.Bookstore.domain.CategoryRepository;



@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository repository;

    @Test
    public void findByNameShouldReturnCategory() {
        List<Category> categories = repository.findByName("Fantasy");
        
        assertThat(categories).hasSize(1);
        assertThat(categories.get(0).getId()).isNotNull();
    }
    
    @Test
    public void createNewCategory() {
    	assertThat((List<Category>) repository.findAll()).hasSize(3);
    	Category category = new Category("Crime");
    	repository.save(category);
    	assertThat(category.getId()).isNotNull();
    	assertThat((List<Category>) repository.findAll()).hasSize(4);
    }
    
    @Test
    public void deleteCategory() {
    	assertThat(repository.findByName("Thriller").get(0).getId()).isNotNull();
    	assertThat((List<Category>) repository.findAll()).hasSize(3);
    	Category category = repository.findByName("Thriller").get(0);
    	repository.deleteById(category.getId());
    	assertThat((List<Category>) repository.findAll()).hasSize(2);
    }

}