package sport.application;




import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertThat;
import sport.application.entities.Diet;
import sport.application.entities.Training;
import sport.application.entities.User;
import sport.application.repositories.TrainingRepository;
import sport.application.services.DietService;

import sport.application.services.TrainingService;
import sport.application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SportAssistentApplicationTests {

    @Autowired
    private UserService userService;
    @Autowired
    private DietService dietService;
    @Autowired
    private TrainingService trainingService;
    @Autowired
    private TrainingRepository trainingRepository;

    @Before
    public void init() {
        {
            User newUser = new User("testUser@mail.com", "Michał", null, "123456", "Kowalski", "123456", false, false, null);
            userService.createUser(newUser);
        }
    }

    @Test
    public void testUser() {
        User user = userService.findOne("testUser@mail.com");
        assertNotNull(user);
        assertEquals(user.getEmail(), "testUser@mail.com");
    }

    @Test
    public void testDiet() {
        Diet firstdiet = dietService.findOneDiet(4L, 625L);
        Diet seconddiet = dietService.findOneDiet(4L, 3600L);
        Diet thirddiet = dietService.findOneDiet(5L, 4200L);
        assertEquals(firstdiet.getId(), Long.valueOf(1));
        assertEquals(seconddiet.getId(), Long.valueOf(4));
        assertEquals(thirddiet.getId(), Long.valueOf(6));
    }

    @Test
    public void testTraining() {
        List<Training> listAllTraining = trainingRepository.findAll();
        Long training = trainingService.findOneTraining(listAllTraining,"rzeźba", "początkujący", "90min", "5");
        assertNotNull(training);
        assertThat(training, anyOf(is(1L), is(3L)));
    }


//      @Test
//      public void testTask() {
//    	  User user = userService.findOne("testUser@mail.com");
//    	  List<Task> task = taskService.findUserTask(user);
//    	  assertNotNull(task);
//
//      }
//


}
