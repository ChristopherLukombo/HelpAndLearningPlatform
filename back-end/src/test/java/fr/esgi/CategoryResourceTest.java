//package fr.esgi;
//
//import fr.esgi.domain.Category;
//import fr.esgi.web.rest.CategoryResource;
//import org.junit.Before;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import javax.persistence.EntityManager;
//import javax.transaction.Transactional;
//
//import static org.hamcrest.core.IsCollectionContaining.hasItem;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = BackEndApplication.class)
//public class CategoryResourceTest {
//
//    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
//
//    @InjectMocks
//    private CategoryResource categoryResource;
//
//    private MockMvc restCategoryMockMvc;
//
//    private Category category;
//
//    @Before
//    public void setup(){
//        MockitoAnnotations.initMocks(this);
//        this.restCategoryMockMvc = MockMvcBuilders.standaloneSetup(categoryResource)
//                .build();
//    }
//
//
//    /**
//     * Create an entity for this test.
//     *
//     * This is a static method, as tests for other entities might also need it,
//     * if they test an entity which requires the current entity.
//     */
//    public static Category createEntity(EntityManager em) {
//        Category category = new Category();
//        category.setWording(DEFAULT_MESSAGE);
//        return category;
//    }
//
//    @Transactional
//    public void shouldFindCategoriesByWording() throws Exception {
//
//
//        // Get the categories
//        restCategoryMockMvc.perform(get("/api/categories"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.[*].id").value(hasItem(category.getId().intValue())))
//                .andExpect(jsonPath("$.[*].wording").value(hasItem(DEFAULT_MESSAGE)));
//    }
//}
